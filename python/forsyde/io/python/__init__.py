import re

import networkx as nx

import forsyde.io.python.core as core
import forsyde.io.python.types as types

class ForSyDeModel(nx.MultiDiGraph):

    """Docstring for ForSyDeModel. """

    def __init__(self, *args, **kwargs):
        """TODO: to be defined. """
        nx.MultiDiGraph.__init__(self, *args, **kwargs)

    def write(self, sink: str) -> None:
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


    def read(self, source: str) -> None:
        vertex_pattern = re.compile("vertex\('(\S+)', '(\S+)'\)\.")
        port_pattern = re.compile("port\('(\S+)', '(\S+)', '(\S+)'\)\.")
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
                    [(p_id, p_vid, p_tname)] = port_pattern.findall(line)
                    port_type = types.TypesFactory.build_type(p_tname)
                    port = core.Port(
                                identifier = p_id,
                                port_type = port_type
                            )
                    vertex_dict[p_vid].ports.add(port)
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

    @classmethod
    def from_file(cls, source: str) -> "ForSyDeModel":
        """TODO: Docstring for read.

        :source: TODO
        :returns: TODO

        """
        new_instance = ForSyDeModel()
        new_instance.read(source)
        return new_instance
