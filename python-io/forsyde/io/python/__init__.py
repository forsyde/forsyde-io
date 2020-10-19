import networkx as nx

class Type:

    """This class represents any type defined in the 'type' hierarchy."""

    def __init__(self, identifier=None):
        """TODO: to be defined.

        :identifier: TODO

        """
        self.identifier = identifier

    def get_type_name(self):
        raise NotImplemented("Interface `Type`.`get_type_name` invoked directly. It must be overridden.")


class Vertex(object):

    """Docstring for Vertex. """

    def __init__(self, 
                 identifier, 
                 ports = set(),
                 properties = dict(),
                 vertex_type=Type()
                 ):
        """TODO: to be defined.

        :identifier: TODO
        :vertex_type: TODO

        """
        self.identifier = identifier
        self.ports = ports
        self.properties = properties
        self.vertex_type = vertex_type

    def __hash__(self):
        return hash(self.identifier)

    def __eq__(self, other):
        return self.identifier == other.identifier

class Edge(object):

    """Docstring for Edge. """

    def __init__(self,
                 source_vertex,
                 target_vertex,
                 source_vertex_port,
                 target_vertex_port,
                 edge_type=Type()
                 ):
        """TODO: to be defined.

        :source_vertex: TODO
        :target_vertex: TODO
        :source_vertex_port: TODO
        :target_vertex_port: TODO
        :edge_type: TODO

        """
        self.source_vertex = source_vertex
        self.target_vertex = target_vertex
        self.source_vertex_port = source_vertex_port
        self.target_vertex_port = target_vertex_port
        self.edge_type = edge_type

    def __eq__(self, other):
        return (self.source_vertex == other.source_vertex and
                self.target_vertex == other.target_vertex and
                (self.source_vertex_port and other.source_vertex_port or
                 self.source_vertex_port == other.source_vertex_port) and
                (self.target_vertex_port and other.target_vertex_port or 
                 self.target_vertex_port == other.target_vertex_port) and
                self.edge_type == other.edge_type)

class ForSyDeModel(nx.MultiDiGraph):

    """Docstring for ForSyDeModel. """

    def __init__(self):
        """TODO: to be defined. """
        nx.MultiDiGraph.__init__(self)

