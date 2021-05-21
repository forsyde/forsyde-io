import networkx as nx
import jinja2
import json
from typing import Set
from typing import Dict

def meta_to_py(md):
    if md["class"] == "object":
        return f"Mapping[{meta_to_py(md['key'])}, {meta_to_py(md['value'])}]"
    elif md["class"] == "array":
        return f"Sequence[{meta_to_py(md['value'])}]"
    elif md["class"] == "int" or md["class"] == "integer":
        return "int"
    elif md["class"] == "double" or md["class"] == "float":
        return "float"
    elif md["class"] == "boolean" or md["class"] == "bool":
        return "bool"
    else:
        return "str"
	

model = None
template = None
with open('meta.json', 'r') as model_file:
    model = json.load(model_file)
with open('package_template.py', 'r') as template_file:
    template = jinja2.Template(template_file.read())

property_map = {}

# build the trait vertex graph
vertexTraitGraph = nx.DiGraph()
for (vname, vdata) in model['vertexTraits'].items():
    vertexTraitGraph.add_node(vname)
    # build the method map
    if vdata and 'required_properties' in vdata:
        for (prop_name, prop_data) in vdata["required_properties"].items():
            if not prop_name in property_map:
                property_map[prop_name] = ([vname], meta_to_py(prop_data))
            else:
                property_map[prop_name][0].append(vname)
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
        edgeTraitSuper=edgeTraitSuper,
        property_map=property_map
        ))
