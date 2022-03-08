# import networkx as nx
import json


from traitdl.ForSyDeTraitDSLVisitor import *
from traitdl.ForSyDeTraitDSLParser import *
from specs import *


class ForSyDeIOTraitDSL(ForSyDeTraitDSLVisitor):
    def linkElements(self):
        pass

    # Visit a parse tree produced by ForSyDeTraitDSLParser#edgeTrait.
    def visitEdgeTrait(
        self, ctx: ForSyDeTraitDSLParser.EdgeTraitContext
    ) -> EdgeTraitSpec:
        return self.visitChildren(ctx)

    # Visit a parse tree produced by ForSyDeTraitDSLParser#vertexPort.
    def visitVertexPort(self, ctx: ForSyDeTraitDSLParser.VertexPortContext) -> PortSpec:
        return self.visitChildren(ctx)

    # Visit a parse tree produced by ForSyDeTraitDSLParser#vertexPropertyType.
    def visitVertexPropertyType(
        self, ctx: ForSyDeTraitDSLParser.VertexPropertyTypeContext
    ) -> PropertyTypeSpec:
        return self.visitChildren(ctx)

    # Visit a parse tree produced by ForSyDeTraitDSLParser#vertexProperty.
    def visitVertexProperty(
        self, ctx: ForSyDeTraitDSLParser.VertexPropertyContext
    ) -> PropertySpec:
        return self.visitChildren(ctx)

    # Visit a parse tree produced by ForSyDeTraitDSLParser#vertexTrait.
    def visitVertexTrait(
        self, ctx: ForSyDeTraitDSLParser.VertexTraitContext
    ) -> VertexTraitSpec:
        vertexTraitSpec = VertexTraitSpec(str(ctx.name))
        # final VertexTraitSpec vertexTraitSpec = vertexTraitSpecMap.get(ctx);
        vertexTraitSpec.required_ports = {
            self.visitVertexPort(p) for p in ctx.vertexPort() or []
        }
        vertexTraitSpec.required_properties = {
            self.visitVertexProperty(p) for p in ctx.vertexProperty() or []
        }
        # for (final Token token : ctx.refinedTraits) {
        #     //vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
        #     // absolute reference or local reference
        #     if (token.getText().contains("::")) {
        #         if (token.getText().startsWith("::"))
        #             vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
        #         else
        #             vertexTraitSpec.absoluteRefinedTraitNames.add("::" + token.getText());
        #         //vertexRefinedParent.add(token.getText())
        #     } else {
        #         // add the namespace in a local reference
        #         vertexTraitSpec.relativeRefinedTraitNames.add(token.getText());
        #         //vertexRefinedParent.add(namespace + "::" + token.getText());
        #     }
        # }
        return vertexTraitSpec

    # Visit a parse tree produced by ForSyDeTraitDSLParser#traitHierarchy.
    def visitTraitHierarchy(
        self, ctx: ForSyDeTraitDSLParser.TraitHierarchyContext
    ) -> TraitHierarchy:
        traitHierarchy = TraitHierarchy()
        namespace = ""
        traitHierarchies = [
            self.visitTraitHierarchy(t) for t in ctx.traitHierarchy() or []
        ]
        # concatenate grand children with children
        containedVertexTraits = [
            v for hierarchy in traitHierarchies for v in hierarchy.vertexes
        ]
        containedVertexTraits += [
            self.visitVertexTrait(v) for v in ctx.vertexTrait() or []
        ]
        # Stream.concat(traitHierarchies.stream().flatMap(c -> c.vertexTraits.stream()), ctx.vertexTrait().stream()
        #                 .map(this::))
        #         .peek(v -> v.name = namespace + "::" + v.name)
        #         .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s))
        #         .peek(v -> v.requiredPorts.replaceAll(p -> {
        #             p.relativeVertexTraitName = p.relativeVertexTraitName != null ? namespace + "::" + p.relativeVertexTraitName : null;
        #             p.relativeEdgeTraitName = p.relativeEdgeTraitName != null ? namespace + "::" + p.relativeEdgeTraitName : null;
        #             return p;
        #         }));
        # final Stream<EdgeTraitSpec> containedEdgeTraits =
        #         Stream.concat(traitHierarchies.stream().flatMap(c -> c.edgeTraits.stream()), ctx.edgeTrait().stream()
        #                         .map(this::visitEdgeTraitTyped))
        #                 .peek(v -> v.name = namespace + "::" + v.name)
        #                 .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s));
        traitHierarchy.vertexes = containedVertexTraits
        traitHierarchy.edges = []
        return traitHierarchy

    # Visit a parse tree produced by ForSyDeTraitDSLParser#rootTraitHierarchy.
    def visitRootTraitHierarchy(
        self, ctx: ForSyDeTraitDSLParser.RootTraitHierarchyContext
    ) -> TraitHierarchy:
        traitHierarchy = TraitHierarchy()
        namespace = ""
        traitHierarchies = [
            self.visitTraitHierarchy(t) for t in ctx.traitHierarchy() or []
        ]
        # concatenate grand children with children
        containedVertexTraits = [
            v for hierarchy in traitHierarchies for v in hierarchy.vertexes
        ]
        containedVertexTraits += [
            self.visitVertexTrait(v) for v in ctx.vertexTrait() or []
        ]
        # Stream.concat(traitHierarchies.stream().flatMap(c -> c.vertexTraits.stream()), ctx.vertexTrait().stream()
        #                 .map(this::))
        #         .peek(v -> v.name = namespace + "::" + v.name)
        #         .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s))
        #         .peek(v -> v.requiredPorts.replaceAll(p -> {
        #             p.relativeVertexTraitName = p.relativeVertexTraitName != null ? namespace + "::" + p.relativeVertexTraitName : null;
        #             p.relativeEdgeTraitName = p.relativeEdgeTraitName != null ? namespace + "::" + p.relativeEdgeTraitName : null;
        #             return p;
        #         }));
        # final Stream<EdgeTraitSpec> containedEdgeTraits =
        #         Stream.concat(traitHierarchies.stream().flatMap(c -> c.edgeTraits.stream()), ctx.edgeTrait().stream()
        #                         .map(this::visitEdgeTraitTyped))
        #                 .peek(v -> v.name = namespace + "::" + v.name)
        #                 .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s));
        traitHierarchy.vertexes = containedVertexTraits
        traitHierarchy.edges = []
        self.linkElements()
        return traitHierarchy


vertexes: List[VertexTraitSpec] = []
edges: List[EdgeTraitSpec] = []
# populate and link the model
with open("meta.json", "r") as model_file:
    model = json.load(model_file)
    for vSpec in model["vertexTraits"]:
        vertexes.append(VertexTraitSpec.from_dict(vSpec))
    for eSpec in model["edgeTraits"]:
        edges.append(EdgeTraitSpec.from_dict(eSpec))
    # link the vertexes
    for v in vertexes:
        for vv in vertexes:
            if vv.name in v.absoluteRefinedTraitsNames:
                v.refinedTraits.add(vv)
            # and the ports
            for port in v.required_ports:
                if port.vertexTraitName == vv.name:
                    port.vertexTrait = vv

# sort from least refined to most refined
for i in range(len(vertexes) - 1):
    for j in range(i + 1, len(vertexes)):
        if vertexes[i].refines(vertexes[j]):
            vertexes.insert(i, vertexes[j])
            vertexes.pop(j + 1)  # sum 1 since the list is bigger


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

header = """
from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

"""

vertexEnum = "class VertexTrait(core.Trait, Enum):\n"
for v in vertexes:
    vertexEnum += 4 * " " + "{0} = auto()\n".format(v.name)
vertexEnum += "\n"
vertexEnum += 4 * " " + "@classmethod\n"
vertexEnum += 4 * " " + "def refines_static(cls, one, other):\n"
for v in vertexes:
    for vv in v.refinedTraits:
        vertexEnum += 8 * " " + "if one is cls.{0} and other is cls.{1}:\n".format(
            v.name, vv.name
        )
        vertexEnum += 12 * " " + "return True\n"
vertexEnum += 8 * " " + "return one == other\n\n"
vertexEnum += 4 * " " + "def __str__(self):\n"
vertexEnum += 8 * " " + "return self.name\n\n"
vertexEnum += 4 * " " + "def refines(self, other):\n"
vertexEnum += 8 * " " + "return VertexTrait.refines_static(self, other)\n\n"

edgeEnum = "class EdgeTrait(core.Trait, Enum):\n"
for v in edges:
    edgeEnum += 4 * " " + "{0} = auto()\n".format(v.name)
edgeEnum += "\n"
edgeEnum += 4 * " " + "@classmethod\n"
edgeEnum += 4 * " " + "def refines_static(cls, one, other):\n"
for v in edges:
    for vv in v.refinedTraits:
        edgeEnum += 8 * " " + "if one is cls.{0} and other is cls.{1}:\n".format(
            v.name, vv.name
        )
        edgeEnum += 12 * " " + "return True\n"
edgeEnum += 8 * " " + "return one == other\n\n"
edgeEnum += 4 * " " + "def __str__(self):\n"
edgeEnum += 8 * " " + "return self.name\n\n"
edgeEnum += 4 * " " + "def refines(self, other):\n"
edgeEnum += 8 * " " + "return EdgeTrait.refines_static(self, other)\n\n"


with open("forsyde/io/python/types.py", "w") as out_file:
    out_file.write(
        header
        + vertexEnum
        + edgeEnum
        + "\n".join(v.to_viewer_code() for v in vertexes)
        + "\n".join(e.to_viewer_code() for e in edges)
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
