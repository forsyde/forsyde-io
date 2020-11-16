import re
import sqlite3
import importlib.resources as res
from contextlib import contextmanager
from typing import Iterable, Dict 

import networkx as nx

import forsyde.io.python.core as core
import forsyde.io.python.types as types

class QueryableMixin(object):

    def __init__(self, standard_views):
        self.conn = None
        self.db = None
        self.standard_views = standard_views

    def __del__(self):
        if self.conn:
            self.conn.close()

    def setup_model_db(self, db=":memory:"):
        if db != ":memory:":
            self.db = db
        else:
            self.conn = sqlite3.connect(":memory:")
        self._install_standard_views()

    @contextmanager
    def connect_db(self):
        try:
            conn = None
            if self.db:
                conn = sqlite3.connect(self.db)
            else:
                if not self.conn:
                    self.conn = self.setup_model_db()
                conn = self.conn
            conn.row_factory = sqlite3.Row
            yield conn
        finally:
            conn.commit()
            if self.db:
                conn.close()

    def _install_standard_views(self):
        with self.connect_db() as db:
            for view_name in self.standard_views:
                sql_command = res.read_text('forsyde.io.python.sql', view_name)
                db.executescript(sql_command)

    def query_view(self, view_name: str) -> Iterable[Dict[str, str]]:
        with self.connect_db() as db:
            for row in db.execute(f"SELECT * FROM {view_name};"):
                yield dict(row)


class ForSyDeModel(nx.MultiDiGraph, QueryableMixin):

    """Docstring for ForSyDeModel. """

    def __init__(self,
                 standard_views=[
                     'create_tables.sql',
                     'types.sql',
                     'create_types_views.sql',
                     'create_sdf_views.sql'
                 ],
                 *args, **kwargs):
        """TODO: to be defined. """
        nx.MultiDiGraph.__init__(self, *args, **kwargs)
        QueryableMixin.__init__(self, standard_views)

    def write(self, sink: str) -> None:
        if '.pro' in sink or '.pl' in sink:
            self.write_prolog(sink)
        elif '.db' in sink:
            self.write_db(sink)
        else:
            raise NotImplementedError

    def read(self, source: str) -> None:
        if '.pro' in source or '.pl' in source:
            self.read_prolog(source)
        elif '.db' in source:
            self.read_db(source)
        else:
            raise NotImplementedError

    def write_prolog(self, sink: str) -> None:
        with open(sink, 'w') as sink_file:
            for (vid, vertex) in self.nodes("data"):
                sink_file.write("vertex('{0}', '{1}').\n".format(
                    vertex.identifier, 
                    vertex.vertex_type.get_type_name()
                ))
            # these loops need to be separated so that prolog consider the exchange
            # file a valid prolog databse.
            for (vid, vertex) in self.nodes("data"):
                for port in vertex.ports:
                    sink_file.write("port('{0}', '{1}', '{2}').\n".format(
                        port.identifier,
                        vertex.identifier, 
                        port.port_type.get_type_name()
                    ))
            for (sid, tid, edge) in self.edges.data("data"):
                sink_file.write("edge('{0}', '{1}', '{2}', '{3}', '{4}').\n".format(
                    edge.source_vertex_id,
                    edge.target_vertex_id,
                    edge.source_vertex_port_id or '',
                    edge.target_vertex_port_id or '',
                    edge.edge_type.get_type_name()
                ))


    def read_prolog(self, source: str) -> None:
        vertex_pattern = re.compile("vertex\('(\S+)', '(\S+)'\)\.")
        port_pattern = re.compile("port\('(\S+)', '(\S+)', '(\S+)'\)\.")
        prop_pattern = re.compile("prop\('(\S+)', '(\S+)', '(.+)'\)\.")
        edge_pattern = re.compile("edge\('(\S+)', '(\S+)', '(\S+)', '(\S+)', '(\S+)'\)\.")
        with open(source, 'r') as source_file:
            vertex_dict = dict()
            for linenum, line in enumerate(source_file):
                if line.startswith("%") or line == "\n":
                    pass
                elif line.startswith('vertex'):
                    [(vertex_id, vertex_type_name)] = vertex_pattern.findall(line)
                    vertex_type = types.TypesFactory.build_type(vertex_type_name)
                    vertex_dict[vertex_id] = core.Vertex(
                        identifier = vertex_id,
                        vertex_type = vertex_type
                    )
                    self.add_node(vertex_id, data = vertex_dict[vertex_id])
                elif line.startswith('port'):
                    # the variables name are shortened to save space
                    # but they follow the same logic as the ones for
                    # vertexes.
                    [(p_vid, p_id, p_tname)] = port_pattern.findall(line)
                    port_type = types.TypesFactory.build_type(p_tname)
                    port = core.Port(
                        identifier = p_id,
                        port_type = port_type
                    )
                    vertex_dict[p_vid].ports.add(port)
                elif line.startswith('prop'):
                    # the variables name are shortened to save space
                    # but they follow the same logic as the ones for
                    # vertexes.
                    [(p_vid, p_id, p_val)] = prop_pattern.findall(line)
                    vertex_dict[p_vid].properties[p_id] = p_val
                elif line.startswith('edge'):
                    # the variables name are shortened to save space
                    # but they follow the same logic as the ones for
                    # vertexes.
                    [(e_sid, e_tid, e_spid, e_tpid, e_tname)] = edge_pattern.findall(line)
                    edge_type = types.TypesFactory.build_type(e_tname)
                    edge = core.Edge(
                        source_vertex_id = e_sid,
                        target_vertex_id = e_tid,
                        edge_type = edge_type
                    )
                    if e_spid:
                        edge.source_vertex_port_id = e_spid
                    if e_tpid:
                        edge.target_vertex_port_id = e_tpid
                    self.add_edge(e_sid, e_tid, data=edge)
                else:
                    raise NotImplementedError(
                        "Syntax error in the ForSyDe-IO Model at line {0}:\n {1}".format(
                            linenum,
                            line
                        )
                    )

    def write_db(self, sink: str) -> None: 
        self.setup_model_db(sink)
        with self.connect_db() as con:
            insert_vertex_sql = res.read_text('forsyde.io.python.sql', 'insert_vertex.sql')
            insert_edge_sql = res.read_text('forsyde.io.python.sql', 'insert_edge.sql')
            insert_prop_sql = res.read_text('forsyde.io.python.sql', 'insert_property.sql')
            insert_port_sql = res.read_text('forsyde.io.python.sql', 'insert_port.sql')
            vertexes = (
                (vid, v.vertex_type.get_type_name())
                for (vid, v) in self.nodes("data")
            )
            con.executemany(insert_vertex_sql, vertexes)
            ports = (
                (p.identifier, vid, p.port_type.get_type_name())
                for (vid, v) in self.nodes("data")
                for p in v.ports
            )
            con.executemany(insert_port_sql, ports)
            props = (
                (pkey, vid, str(pval))
                for (vid, v) in self.nodes("data")
                for (pkey, pval) in v.properties.items()
            )
            con.executemany(insert_prop_sql, props)
            edges = (
                (sid, tid, e.source_vertex_port_id, e.target_vertex_port_id, e.edge_type.get_type_name())
                for (sid, tid, e) in self.edges.data("data")
            )
            con.executemany(insert_edge_sql, edges)

    def read_db(self, source: str) -> None:
        self.setup_model_db(source)
        with self.connect_db() as con:
            vertex_dict = dict()
            for row in con.execute('SELECT * FROM vertexes;'):
                vertex_id = row["vertex_id"]
                vertex_type = types.TypesFactory.build_type(row["type_name"])
                vertex_dict[vertex_id] = core.Vertex(
                    identifier = vertex_id,
                    vertex_type = vertex_type
                )
                self.add_node(vertex_id, data = vertex_dict[vertex_id])
            for row in con.execute('SELECT * FROM ports;'):
                p_vid = row['vertex_id']
                p_id = row['port_id']
                p_tname = row['type_name']
                port_type = types.TypesFactory.build_type(p_tname)
                port = core.Port(
                    identifier = p_id,
                    port_type = port_type
                )
                vertex_dict[p_vid].ports.add(port)
            for row in con.execute('SELECT * FROM properties;'):
                p_vid = row['vertex_id']
                p_id = row['prop_id']
                p_val = row['prop_value']
                vertex_dict[p_vid].properties[p_id] = p_val
            for row in con.execute('SELECT * FROm edges;'):
                e_sid = row['source_vertex_id']
                e_tid = row['target_vertex_id']
                e_spid = row['source_vertex_port_id']
                e_tpid = row['target_vertex_port_id']
                e_tname = row['type_name']
                edge_type = types.TypesFactory.build_type(e_tname)
                edge = core.Edge(
                    source_vertex_id = e_sid,
                    target_vertex_id = e_tid,
                    edge_type = edge_type
                )
                if e_spid:
                    edge.source_vertex_port_id = e_spid
                if e_tpid:
                    edge.target_vertex_port_id = e_tpid
                self.add_edge(e_sid, e_tid, data=edge)

    @classmethod
    def from_file(cls, source: str) -> "ForSyDeModel":
        """TODO: Docstring for read.

        :source: TODO
        :returns: TODO

        """
        new_instance = ForSyDeModel()
        new_instance.read(source)
        return new_instance
