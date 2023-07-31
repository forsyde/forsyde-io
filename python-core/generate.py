# import networkx as nx
import json
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
    refinedTraitsNames: Set[str] = field(default_factory=set)

    def refines(self, other: "TraitSpec"):
        return other.name == self.name or any(r.refines(other) for r in self.refinedTraits) 

    @classmethod
    def from_dict(cls, data):
        return TraitSpec(
            name = data["name"],
            refinedTraitsNames = data["refinedTraits"]
        )

@dataclass
class PropertyTypeSpec:

    typeName: str
    valueType: Optional["PropertyTypeSpec"] = None

    @classmethod
    def from_dict(cls, data):
        return PropertyTypeSpec(
            typeName = data["name"],
            valueType = PropertyTypeSpec.from_dict(data["valueType"]) if "valueType" in data else None
        )

    def meta_to_py(self) -> str:
        if self.typeName == "strMap" or self.typeName == "strmap" or self.typeName == "stringMap" or self.typeName == "stringmap":
            return "Mapping[str, {0}]".format(self.valueType.meta_to_py() if self.valueType else "Any")
        elif self.typeName == "intMap" or self.typeName == "intmap" or self.typeName == "integerMap" or self.typeName == "integermap":
            return "Mapping[int, {0}]".format(self.valueType.meta_to_py() if self.valueType else "Any")
        elif self.typeName == "array":
            return "Sequence[{0}]".format(self.valueType.meta_to_py() if self.valueType else "Any")
        elif self.typeName == "int" or self.typeName == "integer" or self.typeName == "long":
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
            name = data["name"],
            propertyType = PropertyTypeSpec.from_dict(data["type"])
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
            name = data["name"],
            vertexTraitName = data["vertexTrait"],
            ordered = data["ordered"] if "ordered" in data  else False,
            multiple = data["multiple"] if "multiple" in data else True
        )

    def __hash__(self):
        return hash(self.name)

@dataclass
class VertexTraitSpec(TraitSpec):

    required_properties: Set[PropertySpec] = field(default_factory=set)
    required_ports: Set[PortSpec] = field(default_factory=set)

    @classmethod
    def from_dict(cls, data):
        return VertexTraitSpec(
            name = data["name"],
            refinedTraitsNames = data.get("refinedTraits", []),
            required_properties = {
                PropertySpec.from_dict(dProp) for dProp in data.get("required_properties", [])
            } | {
                PropertySpec.from_dict(dProp) for dProp in data.get("requiredProperties", [])
            },
            required_ports = {
                PortSpec.from_dict(dPort) for dPort in data.get("required_ports", [])
            } | {
                PortSpec.from_dict(dPort) for dPort in data.get("requiredPorts", [])
            }
        )

    def __hash__(self):
        return hash(self.name)

    def to_viewer_code(self):
        code = ""
        # class def
        if self.refinedTraits:
            code += "class {0}({1}):\n".format(self.name, ", ".join(self.refinedTraitsNames))
        else:
            code += "class {0}(core.VertexViewer):\n".format(self.name)
        # conforms method
        code += 4*" " + "@classmethod\n"
        code += 4*" " + "def conforms(cls, vertex):\n"
        code += 8*" " + "return any(t.refines(VertexTrait.{0}) for t in vertex.vertex_traits)\n\n".format(
            self.name
        )
        # safe cast method
        # code += 4*" " + "@classmethod\n"
        # code += 4*" " + "def safe_cast(cls, vertex):\n"
        # code += 8*" " + "return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None\n\n"
        # identity override
        code += 4*" " + "@property\n"
        code += 4*" " + "def identifier(self) -> str:\n"
        code += 8*" " + 'return "{0}" + self.viewed_vertex.identifier\n\n'.format(self.name)
        # property getter
        for p in self.required_properties:
            code += 4*" " + "@property\n"
            code += 4*" " + "def {0}(self) -> {1}:\n".format(p.name, p.propertyType.meta_to_py())
            code += 8*" " + 'return self.viewed_vertex.properties["{0}"]\n\n'.format(p.name)
        # port getter
        for p in self.required_ports:
            if p.multiple:
                code += 4*" " + 'def get_{0}(self, model: core.ForSyDeModel) -> Sequence["{1}"]:\n'.format(p.name, p.vertexTraitName)
                if p.ordered:
                    code += 8*" " + 'return sorted(\n'
                    code += 12*" " + '[{0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)],\n'.format(p.vertexTraitName)
                    code += 12*" " + 'key = lambda v: int(self.viewed_vertex.properties["__{0}_ordering__"][v.viewed_vertex.identifier])\n'.format(p.name)
                    code += 8*" " + ')\n\n'
                else:
                    code += 8*" " + 'return [{0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)]\n\n'.format(p.vertexTraitName)
            else:
                code += 4*" " + 'def get_{0}(self, model: core.ForSyDeModel) -> Optional["{1}"]:\n'.format(p.name, p.vertexTraitName)
                code += 8*" " + 'return next(({0}.safe_cast(n) for n in model[self.viewed_vertex] if {0}.conforms(n)), None)\n\n'.format(p.vertexTraitName)
        return code

@dataclass
class EdgeTraitSpec(TraitSpec):

    @classmethod
    def from_dict(cls, data):
        return EdgeTraitSpec(
            name = data["name"],
            refinedTraitsNames = data.get("refinedTraits", [])
        )
    

    def to_viewer_code(self):
        code = ""
        # class def
        code += "class {0}(core.EdgeViewer):\n".format(self.name)
        # conforms method
        code += 4*" " + "@classmethod\n"
        code += 4*" " + "def conforms(cls, edge):\n"
        code += 8*" " + "return any(t.refines(EdgeTrait.{0}) for t in edge.edge_traits)\n\n".format(
            self.name
        )
        # safe cast method
        code += 4*" " + "@classmethod\n"
        code += 4*" " + "def safe_cast(cls, edge):\n"
        code += 8*" " + "return cls(viewed_edge=edge) if cls.conforms(edge) else None\n\n"
        return code



vertexes: List[VertexTraitSpec] = []
edges: List[EdgeTraitSpec] = []
# populate and link the model
with open('meta.json', 'r') as model_file:
    model = json.load(model_file)
    for vSpec in model["vertexTraits"]:
        vertexes.append(VertexTraitSpec.from_dict(vSpec))
    for eSpec in model["edgeTraits"]:
        edges.append(EdgeTraitSpec.from_dict(eSpec))
    # link the vertexes
    for v in vertexes:
        for vv in vertexes:
            if vv.name in v.refinedTraitsNames:
                v.refinedTraits.add(vv)
            # and the ports
            for port in v.required_ports:
                if port.vertexTraitName == vv.name:
                    port.vertexTrait = vv

# sort from least refined to most refined
for i in range(len(vertexes)-1):
    for j in range(i+1, len(vertexes)):
        if vertexes[i].refines(vertexes[j]):
            vertexes.insert(i, vertexes[j])
            vertexes.pop(j+1) # sum 1 since the list is bigger



# with open('package_template.py', 'r') as template_file:
#     template = jinja2.Template(template_file.read())

property_map = {}
default_property_map = {}

# build the trait vertex graph
# vertexTraitGraph = nx.DiGraph()
# for (vname, vdata) in model['vertexTraits'].items():
#     vertexTraitGraph.add_node(vname)
#     # build the method map
#     if vdata and 'required_properties' in vdata:
#         for (prop_name, prop_data) in vdata["required_properties"].items():
#             if not prop_name in property_map:
#                 property_map[prop_name] = ([vname], meta_to_py(prop_data))
#             else:
#                 property_map[prop_name][0].append(vname)
#             if 'default' in prop_data:
#                 default_property_map[prop_name] = prop_data['default']
# for (vname, vdata) in model['vertexTraits'].items():
#     if vdata and 'refinedTraits' in vdata:
#         for refinedTrait in vdata['refinedTraits']:
#             vertexTraitGraph.add_edge(vname, refinedTrait)

# vertexTraitSuper: Dict[str, Set[str]] = {}
# for vname in model['vertexTraits']:
#     vertexTraitSuper[vname] = set()
#     for other in model['vertexTraits']:
#         if nx.has_path(vertexTraitGraph, vname, other) and vname != other:
#             vertexTraitSuper[vname].add(other)

# build the trait edge graph
# edgeTraitGraph = nx.DiGraph()
# for vname in model['edgeTraits']:
#     edgeTraitGraph.add_node(vname)
# for (vname, vdata) in model['edgeTraits'].items():
#     if vdata and 'refinedTraits' in vdata:
#         for refinedTrait in vdata['refinedTraits']:
#             edgeTraitGraph.add_edge(vname, refinedTrait)

# edgeTraitSuper: Dict[str, Set[str]] = {}
# for vname in model['edgeTraits']:
#     edgeTraitSuper[vname] = set()
#     for other in model['edgeTraits']:
#         if nx.has_path(edgeTraitGraph, vname, other) and vname != other:
#             edgeTraitSuper[vname].add(other)

header = '''
from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

'''

vertexEnum = "class VertexTrait(core.Trait, Enum):\n"
for v in vertexes:
    vertexEnum += 4*" " + "{0} = auto()\n".format(v.name)
vertexEnum += "\n"
vertexEnum += 4*" " + "@classmethod\n"
vertexEnum += 4*" " + "def refines_static(cls, one, other):\n"
for v in vertexes:
    for vv in v.refinedTraits:
        vertexEnum += 8*" " + "if one is cls.{0} and other is cls.{1}:\n".format(v.name, vv.name)
        vertexEnum += 12*" " + "return True\n"
vertexEnum += 8*" " + "return one == other\n\n"
vertexEnum += 4*" " + "def __str__(self):\n"
vertexEnum += 8*" " + "return self.name\n\n"
vertexEnum += 4*" " + "def refines(self, other):\n"
vertexEnum += 8*" " + "return VertexTrait.refines_static(self, other)\n\n"

edgeEnum = "class EdgeTrait(core.Trait, Enum):\n"
for v in edges:
    edgeEnum += 4*" " + "{0} = auto()\n".format(v.name)
edgeEnum += "\n"
edgeEnum += 4*" " + "@classmethod\n"
edgeEnum += 4*" " + "def refines_static(cls, one, other):\n"
for v in edges:
    for vv in v.refinedTraits:
        edgeEnum += 8*" " + "if one is cls.{0} and other is cls.{1}:\n".format(v.name, vv.name)
        edgeEnum += 12*" " + "return True\n"
edgeEnum += 8*" " + "return one == other\n\n"
edgeEnum += 4*" " + "def __str__(self):\n"
edgeEnum += 8*" " + "return self.name\n\n"
edgeEnum += 4*" " + "def refines(self, other):\n"
edgeEnum += 8*" " + "return EdgeTrait.refines_static(self, other)\n\n"


with open('forsyde/io/python/types.py', 'w') as out_file:
    out_file.write(
        header + 
        vertexEnum + 
        edgeEnum +
        "\n".join(v.to_viewer_code() for v in vertexes) +
        "\n".join(e.to_viewer_code() for e in edges)
    )
        # template.render(
        # vertexTraits=model['vertexTraits'],
        # vertexTraitProps={
        #     k: {name: meta_to_py(cl) for (name, cl) in v['required_properties'].items()} 
        #        if v and 'required_properties' in v else {}
        #     for (k, v) in model['vertexTraits'].items()
        # },
        # vertexTraitSuper=vertexTraitSuper,
        # edgeTraitSuper=edgeTraitSuper,
        # property_map=property_map,
        # default_property_map=default_property_map
        # ))
