import networkx as nx
import jinja2
import json
from typing import Set
from typing import Dict


model = None
template = None
with open('meta.json', 'r') as model_file:
    model = json.load(model_file)
with open('package_template.py', 'r') as template_file:
    template = jinja2.Template(template_file.read())

# build the trait vertex graph
vertexTraitGraph = nx.DiGraph()
for vname in model['vertexTraits']:
    vertexTraitGraph.add_node(vname)
for (vname, vdata) in model['vertexTraits'].items():
    if vdata and 'superTraits' in vdata:
        for superTrait in vdata['superTraits']:
            vertexTraitGraph.add_edge(vname, superTrait)

vertexTraitSuper: Dict[str, Set[str]] = {}
for vname in model['vertexTraits']:
    vertexTraitSuper[vname] = set()
    for other in model['vertexTraits']:
        if nx.has_path(vertexTraitGraph, vname, other):
            vertexTraitSuper[vname].add(other)

# build the trait edge graph
edgeTraitGraph = nx.DiGraph()
for vname in model['edgeTraits']:
    edgeTraitGraph.add_node(vname)
for (vname, vdata) in model['edgeTraits'].items():
    if vdata and 'superTraits' in vdata:
        for superTrait in vdata['superTraits']:
            edgeTraitGraph.add_edge(vname, superTrait)

edgeTraitSuper: Dict[str, Set[str]] = {}
for vname in model['edgeTraits']:
    edgeTraitSuper[vname] = set()
    for other in model['edgeTraits']:
        if nx.has_path(edgeTraitGraph, vname, other):
            edgeTraitSuper[vname].add(other)

with open('forsyde/io/python/types.py', 'w') as out_file:
    out_file.write(template.render(
        vertexTraitSuper=vertexTraitSuper,
        edgeTraitSuper=edgeTraitSuper
        ))
