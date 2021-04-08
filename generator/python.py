import re
from importlib.resources import read_text
from typing import Union

from jinja2 import Environment, PackageLoader, select_autoescape


def pythonify(t: Union[dict, int, float, str]) -> str:
    if isinstance(t, dict):
        c = t['class']
        if c == "object":
            return f'Dict[{pythonify(t["key"] if "key" in t else "string")}, {pythonify(t["value"])}]'
        elif c == "array":
            return f'List[{pythonify(t["value"])}]'
        else:
            return pythonify(c)
    else:
        if t == "int":
            return "int"
        elif t == "float":
            return "float"
        elif t == "string":
            return "str"
        else:
            return "Vertex"


def generate(spec):
    env = Environment(loader=PackageLoader('generator', 'templates'),
                      autoescape=select_autoescape(['html', 'xml']))
    env.filters['pythonify'] = pythonify
    pak_template = env.get_template(
        'package.py')  # read_text('templates', 'package.py')
    with open('python/forsyde/io/python/types.py', 'w') as typeout:
        typeout.write(pak_template.render(spec))


def fix_version(num: str) -> None:
    file_name = 'python/pyproject.toml'
    content = ""
    with open(file_name, 'r') as f:
        content = f.read()
        content = re.sub(r'version(\s*)=(\s*)"(.+)"', f'version\\1=\\2"{num}"',
                         content)
    with open(file_name, 'w') as f:
        f.write(content)
