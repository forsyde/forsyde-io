import jinja2


def pythonify(t: str) -> str:
    if t == "Integer":
        return "int"
    elif t == "Float":
        return "float"
    elif t == "String":
        return "str"
    elif t == "Dictionary":
        return "dict"
    else:
        return "Vertex"


def generate(spec):
    loader = jinja2.FileSystemLoader('templates')
    env = jinja2.Environment(autoescape=True, loader=loader)
    pak_template = ""
    env.filters['pythonify'] = pythonify
    pak_template = env.get_template('package.py')
    with open('python/forsyde/io/python/types.py', 'w') as typeout:
        typeout.write(pak_template.render(spec))