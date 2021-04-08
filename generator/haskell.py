import pathlib
import re
from importlib.resources import read_text

from jinja2 import Environment, PackageLoader, select_autoescape


def haskellify(t: str) -> str:
    if t == "int":
        return "Int"
    elif t == "float":
        return "Float"
    elif t == "string":
        return "String"
    elif t == "object":
        return "MapItem"
    else:
        return "Vertex"


def generate(spec):
    env = Environment(loader=PackageLoader('generator', 'templates'),
                      autoescape=select_autoescape(['html', 'xml']))
    env.filters['haskellify'] = haskellify
    pak_template = env.get_template(
        'package.hs')  # read_text('templates', 'package.py')
    pathlib.Path("haskell/src/ForSyDe/IO/Haskell").mkdir(parents=True,
                                                         exist_ok=True)
    with open('haskell/src/ForSyDe/IO/Haskell/Types.hs', 'w') as typeout:
        typeout.write(pak_template.render(spec))


def fix_version(num: str) -> None:
    file_name = 'haskell/package.yaml'
    content = ""
    with open(file_name, 'r') as f:
        content = f.read()
        content = re.sub(r'version:(\s*)"?(.+)"?', f'version:\\1"{num}"',
                         content)
    with open(file_name, 'w') as f:
        f.write(content)
