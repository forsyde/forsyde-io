import yaml


def get_spec():
    spec = {"vertexClasses": {}, "edgeClasses": {}, "portClasses": {}}
    with open('model/graph.yaml', 'r') as modelin:
        spec = yaml.load(modelin, Loader=yaml.Loader)
    return spec
