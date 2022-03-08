from typing import Set
from typing import Dict
from typing import List
from typing import Optional

from dataclasses import dataclass
from dataclasses import field


@dataclass
class TraitSpec:

    name: str
    refinedTraits: Set["TraitSpec"] = field(default_factory=set)
    absoluteRefinedTraitsNames: Set[str] = field(default_factory=set)
    relativeRefinedTraitsNames: Set[str] = field(default_factory=set)

    def refines(self, other: "TraitSpec"):
        return other.name == self.name or any(
            r.refines(other) for r in self.refinedTraits
        )

    @classmethod
    def from_dict(cls, data):
        return TraitSpec(name=data["name"], absoluteRefinedTraitsNames=data["refinedTraits"])


@dataclass
class PropertyTypeSpec:

    typeName: str
    valueType: Optional["PropertyTypeSpec"] = None

    @classmethod
    def from_dict(cls, data):
        return PropertyTypeSpec(
            typeName=data["name"],
            valueType=PropertyTypeSpec.from_dict(data["valueType"])
            if "valueType" in data
            else None,
        )

    def meta_to_py(self) -> str:
        if (
            self.typeName == "strMap"
            or self.typeName == "strmap"
            or self.typeName == "stringMap"
            or self.typeName == "stringmap"
        ):
            return "Mapping[str, {0}]".format(
                self.valueType.meta_to_py() if self.valueType else "Any"
            )
        elif (
            self.typeName == "intMap"
            or self.typeName == "intmap"
            or self.typeName == "integerMap"
            or self.typeName == "integermap"
        ):
            return "Mapping[int, {0}]".format(
                self.valueType.meta_to_py() if self.valueType else "Any"
            )
        elif self.typeName == "array":
            return "Sequence[{0}]".format(
                self.valueType.meta_to_py() if self.valueType else "Any"
            )
        elif (
            self.typeName == "int"
            or self.typeName == "integer"
            or self.typeName == "long"
        ):
            return "int"
        elif self.typeName == "double" or self.typeName == "float":
            return "float"
        elif self.typeName == "boolean" or self.typeName == "bool":
            return "bool"
        else:
            return "str"


@dataclass
class PropertySpec:

    name: str
    propertyType: PropertyTypeSpec

    @classmethod
    def from_dict(cls, data):
        return PropertySpec(
            name=data["name"], propertyType=PropertyTypeSpec.from_dict(data["type"])
        )

    def __hash__(self):
        return hash(self.name)


@dataclass
class PortSpec:

    name: str
    vertexTraitName: str
    ordered: bool = False
    multiple: bool = True
    vertexTrait: Optional["VertexTraitSpec"] = None

    @classmethod
    def from_dict(cls, data):
        return PortSpec(
            name=data["name"],
            vertexTraitName=data["vertexTrait"],
            ordered=data["ordered"] if "ordered" in data else False,
            multiple=data["multiple"] if "multiple" in data else True,
        )

    def __hash__(self):
        return hash(self.name)


@dataclass
class VertexTraitSpec(TraitSpec):

    name: str
    required_properties: Set[PropertySpec] = field(default_factory=set)
    required_ports: Set[PortSpec] = field(default_factory=set)

    @classmethod
    def from_dict(cls, data):
        return VertexTraitSpec(
            name=data["name"],
            absoluteRefinedTraitsNames=data.get("refinedTraits", []),
            required_properties={
                PropertySpec.from_dict(dProp)
                for dProp in data.get("required_properties", [])
            }
            | {
                PropertySpec.from_dict(dProp)
                for dProp in data.get("requiredProperties", [])
            },
            required_ports={
                PortSpec.from_dict(dPort) for dPort in data.get("required_ports", [])
            }
            | {PortSpec.from_dict(dPort) for dPort in data.get("requiredPorts", [])},
        )

    def __hash__(self):
        return hash(self.name)

    def to_viewer_code(self):
        code = ""
        # class def
        if self.refinedTraits:
            code += "class {0}({1}):\n".format(
                self.name, ", ".join(self.absoluteRefinedTraitsNames)
            )
        else:
            code += "class {0}(core.VertexViewer):\n".format(self.name)
        # conforms method
        code += 4 * " " + "@classmethod\n"
        code += 4 * " " + "def conforms(cls, vertex):\n"
        code += (
            8 * " "
            + "return any(t.refines(VertexTrait.{0}) for t in vertex.vertex_traits)\n\n".format(
                self.name
            )
        )
        # safe cast method
        # code += 4*" " + "@classmethod\n"
        # code += 4*" " + "def safe_cast(cls, vertex):\n"
        # code += 8*" " + "return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None\n\n"
        # identity override
        code += 4 * " " + "@property\n"
        code += 4 * " " + "def identifier(self) -> str:\n"
        code += 8 * " " + 'return "{0}" + self.viewed_vertex.identifier\n\n'.format(
            self.name
        )
        # property getter
        for p in self.required_properties:
            code += 4 * " " + "@property\n"
            code += 4 * " " + "def {0}(self) -> {1}:\n".format(
                p.name, p.propertyType.meta_to_py()
            )
            code += 8 * " " + 'return self.viewed_vertex.properties["{0}"]\n\n'.format(
                p.name
            )
        # port getter
        for p in self.required_ports:
            if p.multiple:
                code += (
                    4 * " "
                    + 'def get_{0}(self, model: core.ForSyDeModel) -> Sequence["{1}"]:\n'.format(
                        p.name, p.vertexTraitName
                    )
                )
                if p.ordered:
                    code += 8 * " " + "return sorted(\n"
                    code += (
                        12 * " "
                        + "[{0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)],\n".format(
                            p.vertexTraitName
                        )
                    )
                    code += (
                        12 * " "
                        + 'key = lambda v: int(self.viewed_vertex.properties["__{0}_ordering__"][v.viewed_vertex.identifier])\n'.format(
                            p.name
                        )
                    )
                    code += 8 * " " + ")\n\n"
                else:
                    code += (
                        8 * " "
                        + "return [{0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)]\n\n".format(
                            p.vertexTraitName
                        )
                    )
            else:
                code += (
                    4 * " "
                    + 'def get_{0}(self, model: core.ForSyDeModel) -> Optional["{1}"]:\n'.format(
                        p.name, p.vertexTraitName
                    )
                )
                code += (
                    8 * " "
                    + "return next(({0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)), None)\n\n".format(
                        p.vertexTraitName
                    )
                )
        return code


@dataclass
class EdgeTraitSpec(TraitSpec):
    @classmethod
    def from_dict(cls, data):
        return EdgeTraitSpec(
            name=data["name"], absoluteRefinedTraitsNames=data.get("refinedTraits", [])
        )

    def to_viewer_code(self):
        code = ""
        # class def
        code += "class {0}(core.EdgeViewer):\n".format(self.name)
        # conforms method
        code += 4 * " " + "@classmethod\n"
        code += 4 * " " + "def conforms(cls, edge):\n"
        code += (
            8 * " "
            + "return any(t.refines(EdgeTrait.{0}) for t in edge.edge_traits)\n\n".format(
                self.name
            )
        )
        # safe cast method
        code += 4 * " " + "@classmethod\n"
        code += 4 * " " + "def safe_cast(cls, edge):\n"
        code += (
            8 * " " + "return cls(viewed_edge=edge) if cls.conforms(edge) else None\n\n"
        )
        return code


@dataclass
class TraitHierarchy:

    vertexes: List[VertexTraitSpec] = field(default_factory=list)
    edges: List[EdgeTraitSpec] = field(default_factory=list)
