from typing import Optional, Set, Any

class Type:

    """This class represents any type defined in the 'type' hierarchy."""

    def __init__(self):
        """TODO: to be defined.
        """
        pass

    def get_type_name(self):
        raise NotImplementedError("Interface `Type`.`get_type_name` invoked directly. It must be overridden.")

class Port(object):

    """Docstring for Port. """

    def __init__(self, 
                 identifier: str, 
                 port_type = Type()
                 ):
        """TODO: to be defined.

        :identifier: TODO
        :port_type: TODO

        """
        self.identifier = identifier
        self.port_type = port_type

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __hash__(self):
        return hash(self.identifier)
        

class Vertex(object):

    """Docstring for Vertex. """

    def __init__(self, 
                 identifier, 
                 ports: Optional[Set[Port]] = None,
                 properties: Optional[Set[Any]] = None,
                 vertex_type=Type()
                 ):
        """TODO: to be defined.

        :identifier: TODO
        :vertex_type: TODO

        """
        self.identifier = identifier
        if not ports:
            ports = set()
        self.ports = ports
        if not properties:
            properties = set()
        self.properties = properties
        self.vertex_type = vertex_type

    def __hash__(self):
        return hash(self.identifier)

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __repr__(self):
        return "<V id {0}, type {1}, {2} ports, {3} props>".format(
            self.identifier,
            str(self.vertex_type),
            len(self.ports),
            len(self.properties)
        )

class Edge(object):

    """Docstring for Edge. """

    def __init__(self,
                 source_vertex_id: str,
                 target_vertex_id: str,
                 source_vertex_port_id: Optional[str] = '',
                 target_vertex_port_id: Optional[str] = '',
                 edge_type=Type()
                 ):
        """TODO: to be defined.

        :source_vertex: TODO
        :target_vertex: TODO
        :source_vertex_port: TODO
        :target_vertex_port: TODO
        :edge_type: TODO

        """
        self.source_vertex_id = source_vertex_id
        self.target_vertex_id = target_vertex_id
        self.source_vertex_port_id = source_vertex_port_id
        self.target_vertex_port_id = target_vertex_port_id
        self.edge_type = edge_type

    def __eq__(self, other):
        return (self.source_vertex == other.source_vertex and
                self.target_vertex == other.target_vertex and
                (self.source_vertex_port and other.source_vertex_port or
                 self.source_vertex_port == other.source_vertex_port) and
                (self.target_vertex_port and other.target_vertex_port or 
                 self.target_vertex_port == other.target_vertex_port) and
                self.edge_type == other.edge_type)

    def __repr__(self):
        return "<E {0}:{2} -> {1}:{3}, type {4}".format(
            self.source_vertex_id,
            self.target_vertex_id,
            self.source_vertex_port_id,
            self.target_vertex_port_id,
            str(self.edge_type)
        )
