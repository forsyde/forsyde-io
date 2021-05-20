import abc
import warnings
import xml.etree.ElementTree as etree
import pprint
import itertools
from functools import reduce
from typing import Any
from typing import Optional

# from lxml import etree  # type: ignore
import networkx as nx
import pydot

# from networkx.drawing.nx_pydot import write_dot  # type: ignore

from forsyde.io.python.core import Vertex
from forsyde.io.python.core import Edge
from forsyde.io.python.core import ForSyDeModel
from forsyde.io.python.core import Port
from forsyde.io.python.types import EdgeTrait
from forsyde.io.python.types import VertexTrait


class ForSyDeModelDriver(abc.ABC):
    @abc.abstractmethod
    def write(self, model: ForSyDeModel, sink: str) -> None:
        pass

    @abc.abstractmethod
    def read(
        self, source: str, other_model: Optional[ForSyDeModel] = None
    ) -> Optional[ForSyDeModel]:
        return None

    def type_to_str(self, t: type) -> str:
        if t is bool:
            return "boolean"
        elif t is int:
            return "int"
        elif t is float:
            return "double"
        elif t is str:
            return "string"
        elif t is list:
            return "list"
        else:
            return "object"

    def str_to_type(self, s: str) -> type:
        if s == "boolean":
            return bool
        elif s == "int":
            return int
        elif s == "integer":
            return int
        elif s == "long":
            return int
        elif s == "float":
            return float
        elif s == "double":
            return float
        elif s == "string":
            return str
        elif s == "list":
            return list
        else:
            return dict


class ForSyDeMLDriver(ForSyDeModelDriver):
    def __init__(self):
        self.ns = {"xmlns": "http://graphml.graphdrawing.org/xmlns"}

    def write(self, model: ForSyDeModel, sink: str) -> None:
        xmlns = "{http://graphml.graphdrawing.org/xmlns}"
        root = etree.Element(xmlns + "graphml")
        graph = etree.SubElement(root, xmlns + "graph")
        graph.set("id", "model")
        graph.set("edgedefault", "directed")
        for v in model.nodes:
            node_elem = etree.SubElement(graph, xmlns + "node")
            node_elem.set("id", v.identifier)
            node_elem.set("traits", ";".join((str(t) for t in v.vertex_traits)))
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, xmlns + "port")
                port_elem.set("name", port.identifier)
            prop_to_save = [(node_elem, v.properties)]
            while len(prop_to_save) > 0:
                (parent, current) = prop_to_save.pop()
                cur_iter = (
                    enumerate(current) if isinstance(current, list) else current.items()
                )
                for (prop, val) in cur_iter:
                    prop_elem = etree.SubElement(parent, xmlns + "data")
                    prop_elem.set("attr.name", str(prop))
                    prop_elem.set("attr.type", self.type_to_str(type(val)))
                    # prop_elem.text = json.dumps(val)
                    # it is a terminal property
                    if (
                        isinstance(val, int)
                        or isinstance(val, float)
                        or isinstance(val, str)
                        or isinstance(val, bool)
                    ):
                        prop_elem.text = str(val)
                    # it is not
                    else:
                        prop_to_save.append((prop_elem, val))
        for (s, t, edge) in model.edges.data("object"):
            edge_elem = etree.SubElement(graph, xmlns + "edge")
            edge_elem.set("source", s.identifier)
            edge_elem.set("target", t.identifier)
            edge_elem.set("traits", ";".join((str(t) for t in edge.edge_traits)))
            if edge.source_port:
                edge_elem.set("sourceport", edge.source_port.identifier)
            if edge.target_port:
                edge_elem.set("targetport", edge.target_port.identifier)
        with open(sink, "wb") as sinkstream:
            tree = etree.ElementTree(element=root)
            tree.write(sinkstream, encoding="utf-8", xml_declaration=True)
            # sinkstream.write(etree.tostring(tree, pretty_print=True, xml_declaration=True, encoding='UTF-8'))

    def read(
        self, source: str, other_model: Optional[ForSyDeModel] = None
    ) -> ForSyDeModel:
        xmlns = "{http://graphml.graphdrawing.org/xmlns}"
        model = ForSyDeModel() if not other_model else other_model
        with open(source, "r") as instream:
            tree = etree.parse(instream)
            for vnode in tree.findall(".//" + xmlns + "graph/" + xmlns + "node"):
                vertex = Vertex(identifier=vnode.get("id"))
                vertex.vertex_traits = set(
                    VertexTrait[trait_name]
                    for trait_name in vnode.get("traits", '').split(";")
                )
                model.add_node(vertex, label=vertex.identifier)
                for portnode in vnode.iterfind("./" + xmlns + "port"):
                    port = Port(identifier=portnode.get("name"))
                    vertex.ports.add(port)
                dataopen = [(vnode, vertex.properties)]
                while len(dataopen) > 0:
                    (parent, data) = dataopen.pop()
                    for datanode in parent.iterfind(xmlns + "data"):
                        dataname = datanode.get("attr.name")
                        datatype = self.str_to_type(datanode.get("attr.type"))
                        if datatype is str or datatype is int or datatype is float:
                            if isinstance(data, list):
                                data.append(datatype(datanode.text))
                            else:
                                data[dataname] = datatype(datanode.text)
                        else:
                            child = datatype()
                            if datatype is list:
                                data.append(child)
                            else:
                                data[dataname] = child
                            dataopen.append((datanode, child))
            for vedge in tree.iterfind(
                ".//" + xmlns + "graph/" + xmlns + "edge", namespaces=self.ns
            ):
                source_vertex = next(
                    (
                        n
                        for (n, nid) in model.nodes.data("label")
                        if nid == vedge.get("source")
                    ),
                    None,
                )
                if source_vertex is None:
                    raise ValueError(
                        f"Could not find source node {vedge.get('source')} to during edge building."
                    )
                target_vertex = next(
                    (
                        n
                        for (n, nid) in model.nodes.data("label")
                        if nid == vedge.get("target")
                    ),
                    None,
                )
                if target_vertex is None:
                    raise ValueError(
                        f"Could not find target node {vedge.get('target')} to during edge building."
                    )
                edge = Edge(
                    source=source_vertex,
                    target=target_vertex,
                    edge_traits=set(EdgeTrait[n] for n in vedge.get('traits', '').split(';'))
                )
                if vedge.get("sourceport"):
                    edge.source_port = source_vertex.get_port(
                        vedge.get("sourceport")
                    )
                if vedge.get("targetport"):
                    edge.target_port = target_vertex.get_port(
                        vedge.get("targetport")
                    )
                key = (
                    f"{vedge.get('source')}:{vedge.get('sourceport')}->"
                    + f"{vedge.get('target')}:{vedge.get('targetport')}"
                )
                model.add_edge(source_vertex, target_vertex, key=key, object=edge)
        return model


class ForSyDeXMLDriver(ForSyDeModelDriver):
    def __init__(self) -> None:
        warnings.warn(
            "The 'ForSyDeXMLDriver' for 'xml' python driver is deprecated. Use 'ForSyDeMLDriver' for 'forxml' instead.",
            DeprecationWarning,
        )

    def property_to_xml(self, parent: etree.Element, prop: Any) -> None:
        """Transform an object into the expected XML element layout

        The library 'dicttoxml' was considered but dropped for the moment
        since there seemed to be no easy way to control the tag generation
        as this function does by always creating "Property".
        """
        if isinstance(prop, dict):
            for (k, v) in prop.items():
                child = etree.SubElement(parent, "Property")
                child.set("name", k)
                self.property_to_xml(child, v)
        elif isinstance(prop, list):
            for (i, v) in enumerate(prop):
                child = etree.SubElement(parent, "Property")
                child.set("index", i)
                self.property_to_xml(child, v)
        elif isinstance(prop, set):
            for v in prop:
                child = etree.SubElement(parent, "Property")
                self.property_to_xml(child, v)
        else:
            if isinstance(prop, int):
                parent.set("type", "Integer")
            elif isinstance(prop, float):
                parent.set("type", "Float")
            parent.text = str(prop)

    def xml_to_property(self, elem: etree.Element) -> Any:
        """Collect children of the element to expected property layout

        It collects the children recursively of 'elem' to the expected
        formats and types.
        """
        if len(elem.iterfind("Property[@index]")) > 0:
            elems = elem.iterfind("Property[@index]")
            l = [None for i in range(len(elems))]
            for e in elems:
                l[int(e.get("index"))] = self.xml_to_property(e)
            return l
        elif len(elem.iterfind("Property[@name]")) > 0:
            return {
                e.get("name"): self.xml_to_property(e)
                for e in elem.iterfind("Property[@name]")
            }
        elif len(elem.iterfind("Property")) > 0:
            return set(self.xml_to_property(e) for e in elem.iterfind("Property"))
        elif elem.text and elem.text.strip():
            if elem.iterfind('Property[@type="Integer"]'):
                return int(elem.text.strip())
            elif elem.iterfind('Property[@type="Float"]'):
                return float(elem.text.strip())
            else:
                return elem.text.strip()
        else:
            return dict()

    def write(self, model: ForSyDeModel, sink: str) -> None:
        raise NotImplementedError("Writing to ad-hoc XML format is not supported.")
        tree = etree.Element("ForSyDe")
        for v in model.nodes:
            node_elem = etree.SubElement(tree, "Vertex")
            node_elem.set("id", v.identifier)
            node_elem.set("type", v.get_type_tag())
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, "Port")
                port_elem.set("id", port.identifier)
                # if port.port_type == int:
                #     port_elem.set('type', 'Integer')
                # elif port.port_type == float:
                #     port_elem.set('type', 'Integer')
                # elif issubclass(port.port_type, Vertex):
                #     port_elem.set('type', str(port.port_type))
                # else:
                #     port_elem.set('type', 'String')
            for (prop, val) in v.properties.items():
                prop_elem = etree.SubElement(node_elem, "Property")
                prop_elem.set("name", prop)
                # prop_elem.text = json.dumps(val)
                self.property_to_xml(prop_elem, val)
        for (s, t, edge) in model.edges.data("object"):
            edge_elem = etree.SubElement(tree, "Edge")
            edge_elem.set("source_id", s.identifier)
            edge_elem.set("target_id", t.identifier)
            edge_elem.set("type", edge.get_type_tag())
            if edge.source_port:
                edge_elem.set("source_port_id", edge.source_port.identifier)
            if edge.target_port:
                edge_elem.set("target_port_id", edge.target_port.identifier)
        with open(sink, "w") as sinkstream:
            sinkstream.write(
                etree.tostring(tree, pretty_print=True, encoding="unicode")
            )

    def read(
        self, source: str, other_model: Optional[ForSyDeModel] = None
    ) -> ForSyDeModel:
        model = ForSyDeModel() if not other_model else other_model
        with open(source, "r") as instream:
            tree = etree.parse(instream)
            for vnode in tree.findall("./Vertex"):
                vertex = VertexFactory.build(
                    identifier=vnode.get("id"), type_name=vnode.get("type")
                )
                model.add_node(vertex, label=vertex.identifier)
                for portnode in vnode.iterfind("Port"):
                    port = Port(identifier=portnode.get("id"))
                    if portnode.get("type") == "Integer":
                        port.port_type = int
                    elif portnode.get("type") == "Float":
                        port.port_type = float
                    else:
                        try:
                            port.port_type = VertexFactory.get_type_from_name(
                                portnode.get("type")
                            )
                        except NotImplementedError:
                            port.port_type = str
                    vertex.ports.add(port)
                vertex.properties = self.xml_to_property(vnode)
                # for propnode in vnode.iterfind('Property'):
                #     prop_name = propnode.get('name')
                #     prop_val = model.xml_to_property(
                #         propnode)  # json.loads(propnode.text)
                #     vertex.properties[prop_name] = prop_val
            for vedge in tree.findall("./Edge"):
                source_vertex = next(
                    n
                    for (n, nid) in model.nodes.data("label")
                    if nid == vedge.get("source_id")
                )
                target_vertex = next(
                    n
                    for (n, nid) in model.nodes.data("label")
                    if nid == vedge.get("target_id")
                )
                edge = EdgeFactory.build(
                    source=source_vertex,
                    target=target_vertex,
                    type_name=vedge.get("type"),
                )
                if vedge.get("source_port_id"):
                    edge.source_port = source_vertex.get_port(
                        vedge.get("source_port_id")
                    )
                if vedge.get("target_port_id"):
                    edge.target_port = target_vertex.get_port(
                        vedge.get("target_port_id")
                    )
                key = (
                    f"{vedge.get('source_id')}:{vedge.get('source_port_id')}->"
                    + f"{vedge.get('target_id')}:{vedge.get('target_port_id')}"
                )
                model.add_edge(source_vertex, target_vertex, key=key, object=edge)
        return model


class ForSyDeDotDriver(ForSyDeModelDriver):
    """Dot writer for some visualization of the models."""

    def read(
        self, source: str, other_model: Optional[ForSyDeModel]
    ) -> Optional[ForSyDeModel]:
        raise NotImplementedError(
            "The 'DotDriver' only supports writing the model to 'dot'."
        )

    def write(self, model: ForSyDeModel, sink: str) -> None:
        dot = pydot.Dot("model", graph_type="digraph")
        # create all clusters
        graphs = {"": dot}
        for v in model.nodes:
            graph_name = "/".join(v.identifier.split("/")[:-1])
            label = f"{v.identifier}\n"
            label += v.get_type_tag() + "\\n"
            label += pprint.pformat(v.properties)
            graph = (
                graphs[graph_name]
                if graph_name in graphs
                else pydot.Subgraph(
                    f"cluster_{graph_name}", label=graph_name, color="black"
                )
            )
            graph.add_node(pydot.Node(v.identifier, label=label, shape="box"))
            while graph_name not in graphs:
                graphs[graph_name] = graph
                graph_name = "/".join(graph_name.split("/")[:-1])
                parent = (
                    graphs[graph_name]
                    if graph_name in graphs
                    else pydot.Subgraph(
                        f"cluster_{graph_name}", label=graph_name, color="black"
                    )
                )
                parent.add_subgraph(graph)
                graph = parent
        for (s, t, e) in model.edges.data("object"):
            sp = e.source_port
            tp = e.target_port
            graph_name_s = "/".join(s.identifier.split("/")[:-1])
            graph_name_t = "/".join(t.identifier.split("/")[:-1])
            g = dot
            if graph_name_s == graph_name_t:
                g = graphs[graph_name_s]
            g.add_edge(
                pydot.Edge(
                    s.identifier,
                    t.identifier,
                    label=f"{e.get_type_tag()}\\n"
                    + (f"{s.identifier}.{sp.identifier}" if sp else f"{s.identifier}")
                    + "\\n"
                    + (f"{t.identifier}.{tp.identifier}" if tp else f"{t.identifier}"),
                )
            )
        # for ((g1_name, g1), (g2_name, g2)) in itertools.product(graphs.items(), graphs.items()):
        #     if g1 != g2 and g2_name.startswith(g1_name):
        #         print(g1_name, g2_name)
        #         g2.subgraph(g1)
        # for (_, g) in graphs.items():
        #     if g != dot:
        #         dot.subgraph(g)
        with open(sink, "w") as sinkstream:
            sinkstream.write(dot.to_string())
        # write_dot(strg, sink)

class ForSyDeGraphMLDriver(ForSyDeModelDriver):
    """GraphML writer for some visualization of the models."""

    def read(
        self, source: str, other_model: Optional[ForSyDeModel]
    ) -> Optional[ForSyDeModel]:
        raise NotImplementedError(
            "The 'GraphMLDriver' only supports writing the model to 'graphml'."
        )

    def write(self, model: ForSyDeModel, sink: str) -> None:
        xmlns = "{http://graphml.graphdrawing.org/xmlns}"
        root = etree.Element(xmlns + "graphml")
        graph = etree.SubElement(root, xmlns + "graph")
        graph.set("id", "model")
        graph.set("edgedefault", "directed")
        saved_props = []
        for v in model.nodes:
            node_elem = etree.SubElement(graph, xmlns + "node")
            node_elem.set("id", v.identifier)
            # node_elem.set("type", v.get_type_tag())
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, xmlns + "port")
                port_elem.set("name", port.identifier)
            prop_to_save = [("", v.properties)]
            while len(prop_to_save) > 0:
                (parent, current) = prop_to_save.pop()
                cur_iter = (
                    enumerate(current) if isinstance(current, list) else current.items()
                )
                for (prop, val) in cur_iter:
                    # prop_elem.text = json.dumps(val)
                    # it is a terminal property
                    name = parent + "." + prop if parent else prop
                    if (
                        isinstance(val, int)
                        or isinstance(val, float)
                        or isinstance(val, str)
                        or isinstance(val, bool)
                    ):
                        prop_elem = etree.SubElement(node_elem, xmlns + "data")
                        type_str = self.type_to_str(type(val))
                        idx = -1 
                        if (name, type_str) not in saved_props:
                            saved_props.append((name, type_str))
                            idx = len(saved_props)-1
                        else:
                            idx = saved_props.index((name, type_str))
                        prop_elem.set("key", "d" + str(idx))
                        prop_elem.text = str(val)
                    # it is not
                    else:
                        prop_to_save.append((name, val))
        for (s, t, edge) in model.edges.data("object"):
            edge_elem = etree.SubElement(graph, xmlns + "edge")
            edge_elem.set("source", s.identifier)
            edge_elem.set("target", t.identifier)
            # edge_elem.set("type", edge.get_type_tag())
            if edge.source_port:
                edge_elem.set("sourceport", edge.source_port.identifier)
            if edge.target_port:
                edge_elem.set("targetport", edge.target_port.identifier)
        for (idx, (name, type_str)) in enumerate(saved_props):
            data_elem = etree.SubElement(root, xmlns + "key")
            data_elem.set("attr.name", name)
            data_elem.set("attr.type", type_str)
            data_elem.set("id", "d" + str(idx))
        with open(sink, "wb") as sinkstream:
            tree = etree.ElementTree(element=root)
            tree.write(sinkstream, encoding="utf-8", xml_declaration=True)
            # sinkstream.write(etree.tostring(tree, pretty_print=True, xml_declaration=True, encoding='UTF-8'))
