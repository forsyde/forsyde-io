import abc
from typing import Optional, Set, Any, Dict


class Type(abc.ABC):

    """This class represents any type defined in the 'type' hierarchy."""

    def __init__(self):
        """TODO: to be defined.
        """
        pass

    @abc.abstractmethod
    def get_type_name(self) -> str:
        return ""

    @abc.abstractmethod
    def get_required_ports(self) -> Set[str]:
        return set()

    @abc.abstractmethod
    def get_required_properties(self) -> Set[str]:
        return set()


class Port(object):

    """Docstring for Port. """

    _id_gen_counter: int = 0

    def __init__(self,
                 identifier: Optional[str] = None,
                 port_type: Type = Type()
                 ):
        """TODO: to be defined.

        :identifier: TODO
        :port_type: TODO

        """
        if not identifier:
            self._id_gen_counter += 1
            identifier = "port_" + self._id_gen_counter
        self.identifier = identifier
        self.port_type = port_type

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __hash__(self):
        return hash(self.identifier)

    def __repr__(self):
        return f"<Port {self.port_type} {self.identifier}>"


class Vertex(object):

    """Docstring for Vertex. """

    _id_gen_counter: int = 0

    def __init__(self,
                 identifier: Optional[str] = None,
                 ports: Set[Port] = set(),
                 properties: Dict[str, Any] = dict(),
                 vertex_type: Type = Type()
                 ):
        """TODO: to be defined.

        :identifier: TODO
        :vertex_type: TODO

        """
        if not identifier:
            self._id_gen_counter += 1
            identifier = "vertex_" + self._id_gen_counter
        self.identifier = identifier
        # due to the class initialization in python, recreating the
        # set is necessary to make it instance specific and not
        # a class global
        if len(ports) == 0:
            ports = set()
        self.ports = ports
        # see above.
        if len(properties) == 0:
            properties = dict()
        self.properties = properties
        self.vertex_type = vertex_type

    def __hash__(self):
        return hash(self.identifier)

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __repr__(self):
        return "<Vertex {1} {0}, {2} ports, {3} props>".format(
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
        return "<Edge {4} {0}:{2} -> {1}:{3}>".format(
            self.source_vertex_id,
            self.target_vertex_id,
            self.source_vertex_port_id,
            self.target_vertex_port_id,
            str(self.edge_type)
        )
