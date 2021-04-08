import pathlib
import re
from typing import Any

from jinja2 import Environment, PackageLoader, select_autoescape


def snake_to_pascal(s: str) -> str:
    # could have used re, but this is fasters and easier to grasp
    res = s.capitalize()
    while '_' in res:
        idx = res.index('_')
        res = res[0:idx] + res[(idx + 1):].capitalize()
    return res


def javify(t: Any) -> str:
    if isinstance(t, dict):
        c = t['class']
        if c == "object":
            return f'Map<{javify(t["key"] if "key" in t else "string")}, {javify(t["value"])}>'
        elif c == "array":
            return f'List<{javify(t["value"])}>'
        else:
            return javify(c)
    else:
        if t == "int" or t == "integer":
            return "Integer"
        elif t == "float":
            return "Float"
        elif t == "string":
            return "String"
        else:
            return t


def generate(spec):
    env = Environment(loader=PackageLoader('generator', 'templates'),
                      autoescape=select_autoescape(['html', 'xml']))
    vertex_template = ""
    env.filters['javify'] = javify
    env.filters['snake_to_pascal'] = snake_to_pascal
    vertex_template = env.get_template('java/Vertex.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/vertex").mkdir(
        parents=True, exist_ok=True)
    for (v, d) in spec['vertexTypes'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/vertex/{v}.java",
                  'w') as typeout:
            typeout.write(
                vertex_template.render({
                    "type_name": v,
                    "type_data": d
                }))
    edge_template = env.get_template('java/Edge.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/edge").mkdir(
        parents=True, exist_ok=True)
    for (e, d) in spec['edgeTypes'].items():
        with open(f"java/src/main/java/forsyde/io/java/types/edge/{e}.java",
                  'w') as typeout:
            typeout.write(
                edge_template.render({
                    "type_name": e,
                    "type_data": d
                }))
    vfactory_template = env.get_template('java/VertexFactory.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/vertex").mkdir(
        parents=True, exist_ok=True)
    with open(
            f"java/src/main/java/forsyde/io/java/types/vertex/VertexFactory.java",
            'w') as typeout:
        typeout.write(vfactory_template.render({"vtypes":
                                                spec['vertexTypes']}))
    efactory_template = env.get_template('java/EdgeFactory.java')
    pathlib.Path("java/src/main/java/forsyde/io/java/types/edge").mkdir(
        parents=True, exist_ok=True)
    with open(
            f"java/src/main/java/forsyde/io/java/types/edge/EdgeFactory.java",
            'w') as typeout:
        typeout.write(efactory_template.render({"etypes": spec['edgeTypes']}))


def fix_version(num: str) -> None:
    file_name = 'java/build.gradle'
    content = ""
    with open(file_name, 'r') as f:
        content = f.read()
        content = re.sub(r"version(\s*)=(\s*)'(.+)'", f"version\\1=\\2'{num}'",
                         content)
    with open(file_name, 'w') as f:
        f.write(content)
