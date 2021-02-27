from jinja2 import Environment, PackageLoader, select_autoescape
from importlib.resources import read_text


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
    env = Environment(loader=PackageLoader('templates', '.'),
                      autoescape=select_autoescape(['html', 'xml']))
    env.filters['pythonify'] = pythonify
    pak_template = env.get_template(
        'package.py')  # read_text('templates', 'package.py')
    with open('python/forsyde/io/python/types.py', 'w') as typeout:
        typeout.write(pak_template.render(spec))