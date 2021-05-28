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


@dataclass
class Port(object):
    """Port of a vertex.

    This class is intended to help synthesis of components and also
    to keep things semantically sane when dealing with the model, for instance,
    to denote which slot of a time-division a piece of code is executed or
    to denote which input argument of a function is to be used.
    """

    identifier: str = field(default_factory=_generate_port_id, hash=True)

    # port_type: Optional["Vertex"] = field(default=None,
    #                                     compare=False,
    #                                     hash=False)

    def __hash__(self):
        return hash(self.identifier)

    def get_type_tag(self) -> str:
        return "Port"

    def serialize(self) -> dict:
        return {}

    # def is_type(self, t: ModelType) -> bool:
    #     return self.port_type and self.port_type.is_refinement(t)


@dataclass
class Vertex(object):
    """Class holding data regarding Vertexes.

    Every vertex representes a main element in a ForSyDe model.
    Every vertex contains a number of ports (which are repeated in the
    vertexed to increase reliability in the model, since putting
    them in edges would have been sufficient).
    Also, every vertex contains "Properties" which are arbitrary slef-contained
    associated data, such as the size of bits in a Signal or the time slots in
    a Time Division Multiplexer.
    """

    identifier: str = field(default_factory=_generate_vertex_id, hash=True)
    ports: Set[Port] = field(default_factory=set, compare=False, hash=False)
    properties: Dict[str, Any] = field(default_factory=dict, compare=False, hash=False)
    vertex_traits: Set[Trait] = field(default_factory=set, compare=False, hash=False)

    # vertex_type: ModelType = field(default=ModelType(),
    #                                compare=False,
    #                                hash=False)

    def __eq__(self, other):
        return self.identifier == other.identifier

    def __hash__(self):
        return hash(self.identifier)

    def get_type_tag(self) -> str:
        return self.__class__.__name__

    def get_port(self, name: str) -> Port:
        try:
            return next(p for p in self.ports if p.identifier == name)
        except StopIteration:
            raise AttributeError(
                f"Required port {name} of {self.identifier} does not exist."
            )

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


@dataclass
class Edge(object):
    """Class containing all information for an Edge.

    The edge contains references to the source and target 'Vertex'es
    as well as the 'Port's being connect on both ends, in case
    they exist. The edges also have types associated with them
    so that extra deductions can be made along the EDA flow.
    """

    source: Vertex = field(default=Vertex())
    target: Vertex = field(default=Vertex())
    source_port: Optional[Port] = field(default=None)
    target_port: Optional[Port] = field(default=None)
    edge_traits: Set[Trait] = field(default_factory=set)

    # edge_type: ModelType = field(default=ModelType(), compare=False)
    def __hash__(self):
        return hash((self.source_vertex, self.target_vertex))

    def get_type_tag(self) -> str:
        return self.__class__.__name__

    def has_trait(self, o: Trait) -> bool:
        return any(t.refines(o) for t in self.edge_traits)

    def has_trait_strict(self, o: Trait) -> bool:
        return any(t is o for t in self.edge_traits)

    def refines(self, other: "Edge") -> bool:
        if self.source.identifier != other.source.identifier:
            return False
        if self.target.identifier != other.target.identifier:
            return False
        if self.source_port != other.source_port:
            return False
        if self.target_port != other.target_port:
            return False
        for to in other.edge_traits:
            if not any(t.refines(to) for t in self.edge_traits):
                return False
        return True

    # def ids_tuple(self):
    #     return (self.source_vertex.identifier, self.target_vertex.identifier,
    #             self.source_vertex_port.identifier if self.source_vertex_port
    #             else None, self.target_vertex_port.identifier
    #             if self.target_vertex_port else None,
    #             self.edge_type.get_type_tag())

    # def is_type(self, tsource: ModelType, ttarget: ModelType) -> bool:
    #     return self.source_vertex.is_type(
    #         tsource) and self.target_vertex.is_type(ttarget)


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
        self,
        standard_views=["create_tables.sql", "types.sql", "create_views.sql"],
        *args,
        **kwargs,
    ):
        nx.MultiDiGraph.__init__(self, *args, **kwargs)

    def get_vertex(self, label: str, label_name: str = "label") -> Optional[Vertex]:
        for (v, d) in self.nodes.data():
            if d[label_name] == label:
                return v
        return None
