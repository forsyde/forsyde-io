from jinja2 import Environment, PackageLoader, select_autoescape


def generate(spec):
    env = Environment(loader=PackageLoader('templates', '.'),
                      autoescape=select_autoescape(['html', 'xml']))
    vertex_template = ""
    # env.filters['pythonify'] = pythonify
    vertex_template = env.get_template('java/Vertex.java')
    for (v, d) in spec['vertexTypes'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/{v}.java",
                  'w') as typeout:
            typeout.write(
                vertex_template.render({
                    "type_name": v,
                    "type_data": d
                }))
