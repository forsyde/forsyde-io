import pathlib
from jinja2 import Environment, PackageLoader, select_autoescape


def generate(spec):
    env = Environment(loader=PackageLoader('generator', 'templates'),
                      autoescape=select_autoescape(['html', 'xml']))
    vertex_template = ""
    # env.filters['pythonify'] = pythonify
    vertex_template = env.get_template('java/Vertex.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/vertex").mkdir(parents=True, exist_ok=True)
    for (v, d) in spec['vertexTypes'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/vertex/{v}.java",
                  'w') as typeout:
            typeout.write(
                vertex_template.render({
                    "type_name": v,
                    "type_data": d
                }))
    edge_template = env.get_template('java/Edge.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/edge").mkdir(parents=True, exist_ok=True)
    for (e, d) in spec['edgeTypes'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/edge/{e}.java",
                  'w') as typeout:
            typeout.write(
                edge_template.render({
                    "type_name": e,
                    "type_data": d
                }))
    vfactory_template = env.get_template('java/VertexFactory.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/vertex").mkdir(parents=True, exist_ok=True)
    with open(f"java/src/main/java/forsyde/io/java/types/vertex/VertexFactory.java",
              'w') as typeout:
        typeout.write(
            vfactory_template.render({
                "vtypes": spec['vertexTypes']
            }))
    efactory_template = env.get_template('java/EdgeFactory.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/edge").mkdir(parents=True, exist_ok=True)
    with open(f"java/src/main/java/forsyde/io/java/types/edge/EdgeFactory.java",
              'w') as typeout:
        typeout.write(
            efactory_template.render({
                "etypes": spec['edgeTypes']
            }))
