import jinja2


def generate(spec):
    loader = jinja2.FileSystemLoader('templates')
    env = jinja2.Environment(autoescape=True, loader=loader)
    vertex_template = ""
    # env.filters['pythonify'] = pythonify
    vertex_template = env.get_template('java/Vertex.java')
    for (v, d) in spec['vertexClasses'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/{v}.java",
                  'w') as typeout:
            typeout.write(
                vertex_template.render({
                    "type_name": v,
                    "type_data": d
                }))
