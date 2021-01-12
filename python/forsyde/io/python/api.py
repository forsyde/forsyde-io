import json
import re
import sqlite3
import importlib.resources as res
from contextlib import contextmanager
from typing import Callable
from typing import List
from typing import Iterable
from typing import Dict
from typing import Any
from typing import Optional

from lxml import etree
import networkx as nx
import networkx.drawing.nx_pydot as nx_pydot

from forsyde.io.python.core import ModelType
from forsyde.io.python.core import Vertex
from forsyde.io.python.core import Edge
from forsyde.io.python.core import Port
from forsyde.io.python.types import TypesFactory


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

    def query_view(self, view_name: str) -> Iterable[Dict[str, Any]]:
        with self.connect_db() as db:
            for row in db.execute(f"SELECT * FROM {view_name};"):
                yield dict(row)


class ForSyDeModel(nx.MultiDiGraph, QueryableMixin):
    """The main graph holder element representing a ForSyDe Model

    A subclass of MultiDiGraph from the networkX library, this class
    holds the model (a graph model therefore) which can be used for
    any purpose in the ForSyDe design flow. In addition to all standard
    graph algorithms and facilities given by networkX, this subclass
    also provides parsing and dumping from the canonical ForSyDe IO
    disk persistent file and other output formats that can be used for
    visualization, such as GraphML.

    It also provides additional methods building on top of standard graph
    methods to make development easier, such as directly iterating vertexes
    by their associated types.
    """

    def __init__(self, standard_views=['create_tables.sql', 'types.sql', 'create_views.sql'], *args, **kwargs):
        """TODO: to be defined. """
        nx.MultiDiGraph.__init__(self, *args, **kwargs)
        QueryableMixin.__init__(self, standard_views)

    def _rectify_model(self):
        for v in self.nodes:
            for (k, val) in v.vertex_type.get_required_properties():
                if k not in v.properties:
                    v.properties[k] = val
            for (name, port) in v.vertex_type.get_required_ports():
                if name not in (p.identifier for p in v.ports):
                    v.ports.add(Port(identifier=name, port_type=port))

    def write(self, sink: str) -> None:
        self._rectify_model()
        if '.pro' in sink or '.pl' in sink:
            self.write_prolog(sink)
        elif '.db' in sink:
            self.write_db(sink)
        elif '.gexf' in sink:
            nx.write_gexf(self.stringified(), sink)
        elif '.graphml' in sink:
            nx.write_graphml(self.stringified(), sink)
        elif '.dot' in sink:
            nx_pydot.write_dot(self.stringified(), sink)
        elif '.xml' in sink:
            self.write_xml(sink)
        else:
            raise NotImplementedError

    def read(self, source: str) -> None:
        if '.pro' in source or '.pl' in source:
            self.read_prolog(source)
        elif '.db' in source:
            self.read_db(source)
        elif '.xml' in source:
            self.read_xml(source)
        else:
            raise NotImplementedError
        self._rectify_model()

    def stringified(self) -> nx.MultiDiGraph:
        strg = nx.MultiDiGraph()
        for v in self.nodes:
            strg.add_node(f"{v.identifier}\\n{v.vertex_type.get_type_name()}")
        for (s, t, e) in self.edges.data("object"):
            sp = e.source_vertex_port
            tp = e.target_vertex_port
            strg.add_edge(f"{s.identifier}\\n{s.vertex_type.get_type_name()}",
                          f"{t.identifier}\\n{t.vertex_type.get_type_name()}",
                          label=f"{e.edge_type.get_type_name()}\\n" +
                          (f"{s.identifier}.{sp.identifier}" if sp else f"{s.identifier}") + "\\n" +
                          (f"{t.identifier}.{tp.identifier}" if tp else f"{t.identifier}"))
        return strg

    def write_prolog(self, sink: str) -> None:
        with open(sink, 'w') as sink_file:
            for (vid, vertex) in self.nodes("data"):
                sink_file.write("vertex('{0}', '{1}').\n".format(vertex.identifier, vertex.vertex_type.get_type_name()))
            # these loops need to be separated so that prolog consider the exchange
            # file a valid prolog objectbse.
            for (vid, vertex) in self.nodes("data"):
                for port in vertex.ports:
                    sink_file.write("port('{0}', '{1}', '{2}').\n".format(port.identifier, vertex.identifier,
                                                                          port.port_type.get_type_name()))
            for (sid, tid, edge) in self.edges.data("object"):
                sink_file.write("edge('{0}', '{1}', '{2}', '{3}', '{4}').\n".format(edge.source_vertex_id,
                                                                                    edge.target_vertex_id,
                                                                                    edge.source_vertex_port_id or '',
                                                                                    edge.target_vertex_port_id or '',
                                                                                    edge.edge_type.get_type_name()))

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
                    vertex_type = TypesFactory.build_type(vertex_type_name)
                    vertex_dict[vertex_id] = Vertex(identifier=vertex_id, vertex_type=vertex_type)
                    self.add_node(vertex_id, object=vertex_dict[vertex_id])
                elif line.startswith('port'):
                    # the variables name are shortened to save space
                    # but they follow the same logic as the ones for
                    # vertexes.
                    [(p_vid, p_id, p_tname)] = port_pattern.findall(line)
                    port_type = TypesFactory.build_type(p_tname)
                    port = Port(identifier=p_id, port_type=port_type)
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
                    edge_type = TypesFactory.build_type(e_tname)
                    edge = Edge(source_vertex_id=e_sid, target_vertex_id=e_tid, edge_type=edge_type)
                    if e_spid:
                        edge.source_vertex_port_id = e_spid
                    if e_tpid:
                        edge.target_vertex_port_id = e_tpid
                    self.add_edge(e_sid, e_tid, object=edge)
                else:
                    raise NotImplementedError("Syntax error in the ForSyDe-IO Model at line {0}:\n {1}".format(
                        linenum, line))

    def get_vertexes(self,
                     v_type: Optional[ModelType] = None,
                     filters: List[Callable[[Vertex], bool]] = []
                     ) -> Iterable[Vertex]:
        for v in self.nodes:
            if v_type and v.is_type(v_type):
                if all(f(v) for f in filters):
                    yield v
            elif all(f(v) for f in filters):
                yield v

    def neighs(self, v: Vertex) -> Iterable[Vertex]:
        yield from self.nodes.adj[v]

    def neighs_rev(self, v: Vertex) -> Iterable[Vertex]:
        yield from nx.reverse_view(self).adj[v]

    def query_vertexes(self, view_name: str, id_name: str = 'vertex_id') -> Iterable[Vertex]:
        # TODO: optimize this later
        for row in self.query_view(view_name):
            for v in self.nodes:
                if v.identifier == row[id_name]:
                    yield v

    def get_vertex(self, label: str, label_name: str = 'label') -> Vertex:
        for (v, d) in self.nodes.data():
            if d[label_name] == label:
                return v

    def write_db(self, sink: str) -> None:
        self.setup_model_db(sink)
        with self.connect_db() as con:
            insert_vertex_sql = res.read_text('forsyde.io.python.sql', 'insert_vertex.sql')
            insert_edge_sql = res.read_text('forsyde.io.python.sql', 'insert_edge.sql')
            insert_prop_sql = res.read_text('forsyde.io.python.sql', 'insert_property.sql')
            insert_port_sql = res.read_text('forsyde.io.python.sql', 'insert_port.sql')
            vertexes = ((v.identifier, v.vertex_type.get_type_name()) for v in self.nodes)
            con.executemany(insert_vertex_sql, vertexes)
            ports = ((p.identifier, v.identifier, p.port_type.get_type_name()) for v in self.nodes for p in v.ports)
            con.executemany(insert_port_sql, ports)
            props = ((pkey, v.identifier, json.dumps(pval)) for v in self.nodes
                     for (pkey, pval) in v.properties.items())
            con.executemany(insert_prop_sql, props)
            edges = (e.ids_tuple() for (_, _, e) in self.edges.data("object"))
            con.executemany(insert_edge_sql, edges)

    def read_db(self, source: str) -> None:
        self.setup_model_db(source)
        for row in self.query_view('vertexes'):
            vertex = Vertex(identifier=row["vertex_id"], vertex_type=TypesFactory.build_type(row["type_name"]))
            self.add_node(vertex, label=vertex.identifier)
        for row in self.query_view('ports'):
            p_vid = row['vertex_id']
            p_id = row['port_id']
            p_type = TypesFactory.build_type(row['type_name'])
            port = Port(identifier=p_id, port_type=p_type)
            self.get_vertex(p_vid).ports.add(port)
        for row in self.query_view('properties'):
            p_vid = row['vertex_id']
            p_id = row['prop_id']
            p_val = row['prop_value']
            self.get_vertex(p_vid).properties[p_id] = p_val
        for row in self.query_view('edges'):
            source_vertex = self.get_vertex(row['source_vertex_id'])
            target_vertex = self.get_vertex(row['target_vertex_id'])
            source_vertex_port = next((p for p in source_vertex.ports if p.identifier == row['source_vertex_port_id']),
                                      None)
            target_vertex_port = next((p for p in source_vertex.ports if p.identifier == row['target_vertex_port_id']),
                                      None)
            edge = Edge(source_vertex=source_vertex,
                        target_vertex=target_vertex,
                        source_vertex_port=source_vertex_port,
                        target_vertex_port=target_vertex_port,
                        edge_type=TypesFactory.build_type(row['type_name']))
            self.add_edge(edge.source_vertex, edge.target_vertex, object=edge)

    def property_to_xml(self, parent: etree.Element, prop: Any) -> None:
        '''Transform an object into the expected XML element layout
        
        The library 'dicttoxml' was considered but dropped for the moment
        since there seemed to be no easy way to control the tag generation
        as this function does by always creating "Property".
        '''
        if isinstance(prop, dict):
            for (k, v) in prop.items():
                child = etree.SubElement(parent, 'Property')
                child.set('name', k)
                self.property_to_xml(child, v)
        elif isinstance(prop, list):
            for (i, v) in enumerate(prop):
                child = etree.SubElement(parent, 'Property')
                child.set('index', i)
                self.property_to_xml(child, v)
        elif isinstance(prop, set):
            for v in prop:
                child = etree.SubElement(parent, 'Property')
                self.property_to_xml(child, v)
        else:
            if isinstance(prop, int):
                parent.set('type', 'Integer')
            elif isinstance(prop, float):
                parent.set('type', 'Float')
            parent.text = str(prop)

    def xml_to_property(self, elem: etree.Element) -> Any:
        '''Collect children of the element to expected property layout

        It collects the children recursively of 'elem' to the expected
        formats and types.
        '''
        if len(elem.xpath("Property[@index]")) > 0:
            elems = elem.xpath("Property[@index]")
            l = [None for i in range(len(elems))]
            for e in elems:
                l[int(e.get('index'))] = self.xml_to_property(e)
            return l
        elif len(elem.xpath("Property[@name]")) > 0:
            return {e.get("name"): self.xml_to_property(e) for e in elem.xpath("Property[@name]")}
        elif len(elem.xpath("Property")) > 0:
            return set(self.xml_to_property(e) for e in elem.xpath("Property"))
        elif elem.text and elem.text.strip():
            if elem.xpath('Property[@type="Integer"]'):
                return int(elem.text.strip())
            elif elem.xpath('Property[@type="Float"]'):
                return float(elem.text.strip())
            else:
                return elem.text.strip()
        else:
            return dict()

    def write_xml(self, sink: str) -> None:
        tree = etree.Element('ForSyDe')
        for v in self.nodes:
            node_elem = etree.SubElement(tree, 'Vertex')
            node_elem.set('id', v.identifier)
            node_elem.set('type', v.vertex_type.get_type_name())
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, 'Port')
                port_elem.set('id', port.identifier)
                port_elem.set('type', port.port_type.get_type_name())
            for (prop, val) in v.properties.items():
                prop_elem = etree.SubElement(node_elem, 'Property')
                prop_elem.set('name', prop)
                # prop_elem.text = json.dumps(val)
                self.property_to_xml(prop_elem, val)
        for (s, t, edge) in self.edges.data("object"):
            edge_elem = etree.SubElement(tree, 'Edge')
            edge_elem.set('source_id', s.identifier)
            edge_elem.set('target_id', t.identifier)
            edge_elem.set('type', edge.edge_type.get_type_name())
            if edge.source_vertex_port:
                edge_elem.set('source_port_id', edge.source_vertex_port.identifier)
            if edge.target_vertex_port:
                edge_elem.set('target_port_id', edge.target_vertex_port.identifier)
        with open(sink, 'w') as sinkstream:
            sinkstream.write(etree.tostring(tree, pretty_print=True, encoding='unicode'))

    def read_xml(self, source: str) -> None:
        with open(source, 'r') as instream:
            tree = etree.parse(instream)
            for vnode in tree.xpath('/ForSyDe/Vertex'):
                vertex_id = vnode.get('id')
                vertex_type = TypesFactory.build_type(vnode.get('type'))
                vertex = Vertex(identifier=vertex_id, vertex_type=vertex_type)
                self.add_node(vertex, label=vertex_id)
                for portnode in vnode.xpath("Port"):
                    port_id = portnode.get('id')
                    port_type = TypesFactory.build_type(portnode.get('type'))
                    vertex.ports.add(Port(identifier=port_id, port_type=port_type))
                vertex.properties = self.xml_to_property(vnode)
                # for propnode in vnode.xpath('Property'):
                #     prop_name = propnode.get('name')
                #     prop_val = self.xml_to_property(
                #         propnode)  # json.loads(propnode.text)
                #     vertex.properties[prop_name] = prop_val
            for vedge in tree.xpath('/ForSyDe/Edge'):
                edge_type = TypesFactory.build_type(vedge.get('type'))
                source_vertex = next(n for (n, nid) in self.nodes.data('label') if nid == vedge.get('source_id'))
                target_vertex = next(n for (n, nid) in self.nodes.data('label') if nid == vedge.get('target_id'))
                source_vertex_port = next(
                    (p for p in source_vertex.ports if p.identifier == vedge.get('source_port_id')), None)
                target_vertex_port = next(
                    (p for p in target_vertex.ports if p.identifier == vedge.get('target_port_id')), None)
                edge = Edge(source_vertex=source_vertex,
                            target_vertex=target_vertex,
                            source_vertex_port=source_vertex_port,
                            target_vertex_port=target_vertex_port,
                            edge_type=edge_type)
                self.add_edge(source_vertex, target_vertex, object=edge)


def load_model(source: str) -> ForSyDeModel:
    """TODO: Docstring for read.

    :source: TODO
    :returns: TODO

    """
    new_instance = ForSyDeModel()
    new_instance.read(source)
    return new_instance
