import json


def get_spec():
    spec = {"vertexTypes": {}, "edgeTypes": {}, "portClasses": {}}
    with open('model/graph.json', 'r') as modelin:
        spec = json.load(modelin)
    return spec
