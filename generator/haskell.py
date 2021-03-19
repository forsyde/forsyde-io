from jinja2 import Environment, PackageLoader, select_autoescape
from importlib.resources import read_text


def haskellify(t: str) -> str:
    if t == "Integer":
        return "Int"
    elif t == "Float":
        return "Float"
    elif t == "String":
        return "String"
    elif t == "Dictionary":
        return "MapItem"
    else:
        return "Vertex"


def generate(spec):
    env = Environment(loader=PackageLoader('generator', 'templates'),
                      autoescape=select_autoescape(['html', 'xml']))
    env.filters['haskellify'] = haskellify
    pak_template = env.get_template(
        'package.hs')  # read_text('templates', 'package.py')
    with open('haskell/src/ForSyDe/IO/Haskell/Types.hs', 'w') as typeout:
        typeout.write(pak_template.render(spec))
