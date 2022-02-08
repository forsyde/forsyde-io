from dataclasses import dataclass
from dataclasses import field
from typing import Any
from typing import Dict
from typing import Union

from forsyde.io.python.core import Port
from forsyde.io.python.core import Vertex


@dataclass
class Integer(Port):
    """Represents an Integer port"""

    bits: int = 8

    def get_type_tag(self) -> str:
        return "Integer"

    def serialize(self) -> Dict[str, int]:
        return {"bits": self.bits}


@dataclass
class Float(Port):
    """Represents a Floating number port"""

    bits: int = 0

    def get_type_tag(self) -> str:
        return "Float"

    def serialize(self) -> Dict[str, int]:
        return {"bits": self.bits}


@dataclass
class Array(Port):
    """Represents a fixed size array port"""

    size: int = 1
    contained: Port = Integer(size=1)

    def get_type_tag(self) -> str:
        return "Array"

    def serialize(self) -> Dict[str, Union[int, Port]]:
        return {"size": self.size, "contained": self.contained.serialize()}


@dataclass
class Record(Port):
    """Represents a record port"""

    elems: Dict[str, Port] = field(default_factory=dict)

    def get_type_tag(self) -> str:
        return "Record"

    def serialize(self) -> Dict[str, Port]:
        return {k: v.serialize() for (k, v) in self.elems.items()}


@dataclass
class VertexPort(Port):
    """Represents a port to another vertex directly. AKA higher order structures."""

    vertex_type: type = Vertex

    def get_type_tag(self) -> str:
        return "VertexPort"

    def serialize(self) -> str:
        return str(self.vertex_type)


def port_from_name(name: str, *args, **kwargs) -> Port:
    classes = {
        "Integer": Integer,
        "Float": Float,
        "Array": Array,
        "Record": Record,
        "VertexPort": VertexPort
    }
    return classes[name](*args, **kwargs)
