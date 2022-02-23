from dataclasses import dataclass
from dataclasses import field
from typing import Any
from typing import Dict
from typing import List
from typing import Iterable
from typing import Optional
from typing import Set
from typing import Tuple
from typing import Sequence
from typing import Type
from typing import NamedTuple
from enum import Enum
import importlib.resources as res
import itertools
import json

import networkx as nx  # type: ignore

_port_id_counter = 0
_vertex_id_counter = 0


def _generate_vertex_id() -> str:
    global _vertex_id_counter
    _vertex_id_counter += 1
    return "vertex" + str(_vertex_id_counter)


def _generate_port_id() -> str:
    global _port_id_counter
    _port_id_counter += 1
    return "port" + str(_port_id_counter)


class Trait:
    """Trait associated with a vertex or an edge.

    Though Python already keeps many runtime amenities that would make this
    explicit runtime representation of Types unnecesary, having it explicitly can
    ease porting to other langauges and also usability for users that are not
    familiar with Python 'meta' built-in facilities.

    This clas is meant to be used more of a interface than a concrete class.
    """

    def refines(self, o):
        return False

    def __lshift__(self, o):
        return self.refines(o)


class OpaqueTrait(Trait):

    traitName: str

    def __init__(self, traitName: str) -> None:
        super().__init__()

    def __hash__(self) -> int:
        return hash(self.traitName)

    def __eq__(self, __o: object) -> bool:
        if isinstance(__o, OpaqueTrait):
            return __o.traitName == self.traitName
        else:
            return False


class Vertex(NamedTuple):
    """Class holding data regarding Vertexes.

    Every vertex representes a main element in a ForSyDe model.
    Every vertex contains a number of ports (which are repeated in the
    vertexed to increase reliability in the model, since putting
    them in edges would have been sufficient).
    Also, every vertex contains "Properties" which are arbitrary slef-contained
    associated data, such as the size of bits in a Signal or the time slots in
    a Time Division Multiplexer.
    """

    identifier: str = str(_generate_vertex_id())
    ports: Set[str] = set()
    properties: Dict[str, Any] = dict()
    vertex_traits: Set[Trait] = set()

    # vertex_type: ModelType = field(default=ModelType(),
    #                                compare=False,
    #                                hash=False)

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __hash__(self):
        return hash(self.identifier)

    def get_type_tag(self) -> str:
        return self.__class__.__name__

    def has_trait(self, trait: Trait) -> bool:
        return any(t.refines(trait) for t in self.vertex_traits)

    def has_trait_strict(self, trait: Trait) -> bool:
        return any(t is trait for t in self.vertex_traits)

    def refines(self, other: "Vertex") -> bool:
        """Check if 'self' refines 'other'

        One vertex refines other if every trait of other is
        a refineable to at least one trait of one.
        """
        if self.identifier != other.identifier:
            return False
        for to in other.vertex_traits:
            if not any(t.refines(to) for t in self.vertex_traits):
                return False
        return True


class EdgeInfo(NamedTuple):
    """Class containing all information for an EdgeInfo.

    The edge contains references to the source and target 'Vertex'es
    as well as the 'Port's being connect on both ends, in case
    they exist. The edges also have types associated with them
    so that extra deductions can be made along the EDA flow.
    """

    source: str
    target: str
    source_port: Optional[str] = None
    target_port: Optional[str] = None
    edge_traits: Set[Trait] = set()

    # edge_type: ModelType = field(default=ModelType(), compare=False)
    def __hash__(self):
        return hash((self.source, self.target, self.source_port, self.target_port))

    def get_type_tag(self) -> str:
        return self.__class__.__name__

    def has_trait(self, o: Trait) -> bool:
        return any(t.refines(o) for t in self.edge_traits)

    def refines(self, other: "EdgeInfo") -> bool:
        if self.source != other.source:
            return False
        if self.target != other.target:
            return False
        if self.source_port != other.source_port:
            return False
        if self.target_port != other.target_port:
            return False
        for to in other.edge_traits:
            if not any(t.refines(to) for t in self.edge_traits):
                return False
        return True

    @property
    def _str_key(self) -> str:
        return self.source + str(self.source_port) + str(self.target_port) + self.target

    # def ids_tuple(self):
    #     return (self.source_vertex.identifier, self.target_vertex.identifier,
    #             self.source_vertex_port.identifier if self.source_vertex_port
    #             else None, self.target_vertex_port.identifier
    #             if self.target_vertex_port else None,
    #             self.edge_type.get_type_tag())

    # def is_type(self, tsource: ModelType, ttarget: ModelType) -> bool:
    #     return self.source_vertex.is_type(
    #         tsource) and self.target_vertex.is_type(ttarget)


@dataclass
class VertexViewer:

    viewed_vertex: Vertex

    @classmethod
    def conforms(cls, vertex: Vertex) -> bool:
        return False

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    @property
    def identifier(self) -> str:
        return self.viewed_vertex.identifier


@dataclass
class EdgeViewer:

    viewed_edge: EdgeInfo

    @classmethod
    def conforms(cls, edge: EdgeInfo) -> bool:
        return False

    @classmethod
    def safe_cast(cls, edge: EdgeInfo) -> Optional["EdgeViewer"]:
        return None


class ForSyDeModel(nx.MultiDiGraph):
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

    def __init__(
        self, *args, **kwargs,
    ):
        nx.MultiDiGraph.__init__(self, *args, **kwargs)
