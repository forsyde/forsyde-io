import xml.etree.ElementTree as etree
from typing import Any
from typing import Optional

from forsyde.io.python.drivers import ForSyDeModelDriver
from forsyde.io.python.core import Vertex
from forsyde.io.python.core import EdgeInfo
from forsyde.io.python.core import OpaqueTrait
from forsyde.io.python.core import ForSyDeModel


class ForSyDeXMIDriver(ForSyDeModelDriver):
    def __init__(self) -> None:
        super().__init__()
        self.ns = {
            "forsyde.io.eclipse.systemgraph": "forsyde.io.eclipse.systemgraph",
            "graph": "forsyde.io.eclipse.systemgraph",
            "systemgraph": "forsyde.io.eclipse.systemgraph",
            "forsyde": "forsyde.io.eclipse.systemgraph",
            "xmi": "http://www.omg.org/XMI",
            "xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xmlns": "http://www.w3.org/2000/xmlns/",
        }

    def get_supported_read_formats(self):
        return ["forsyde.xmi", "forxmi"]

    def get_supported_write_formats(self):
        return ["forsyde.xmi", "forxmi"]

    def write(self, model: ForSyDeModel, sink: str) -> None:
        pass

    def read(
        self, source: str, other_model: Optional[ForSyDeModel] = None
    ) -> Optional[ForSyDeModel]:
        xmlns = "{forsyde.io.eclipse.systemgraph}"
        model = ForSyDeModel() if not other_model else other_model
        with open(source, "r") as instream:
            tree = etree.parse(instream)
            for vnode in tree.findall(
                "./" + xmlns + "ForSyDeSystemGraph//" + xmlns + "nodes"
            ):
                vertex_id = str(vnode.get("id"))
                vertex_traits = set(
                    OpaqueTrait(traitnode.text)
                    for traitnode in vnode.iterfind("./" + xmlns + "traits")
                )
                vertex_ports = set(
                    portnode.text for portnode in vnode.iterfind("./" + xmlns + "ports")
                )
                # assume that the properties names are just after their values
                vertex_properties = {
                    vnode[i + 1].text: self.read_data(vnode[i])
                    for i in range(0, len(vnode))
                    if vnode.tag == "propertiesValues"
                }
                vertex = Vertex(
                    vertex_id, vertex_ports, vertex_properties, vertex_traits,
                )
                model.add_node(vertex, label=vertex_id)
                # for portnode in vnode.iterfind("./" + xmlns + "port"):
                #     port = Port(identifier=portnode.get("name"))
                #     vertex.ports.add(port)
                # dataopen = [(vnode, vertex.properties)]
                # while len(dataopen) > 0:
                #     (parent, data) = dataopen.pop()
                #     for datanode in parent.iterfind(xmlns + "data"):
                #         dataname = datanode.get("attr.name")
                #         datatype = self.str_to_type(datanode.get("attr.type"))
                #         if datatype is str or datatype is int or datatype is float:
                #             if isinstance(data, list):
                #                 data.append(datatype(datanode.text))
                #             else:
                #                 data[dataname] = datatype(datanode.text)
                #         else:
                #             child = datatype()
                #             if isinstance(data, list):
                #                 data.append(child)
                #             else:
                #                 data[dataname] = child
                #             dataopen.append((datanode, child))
            for vedge in tree.iterfind(
                "./" + xmlns + "ForSyDeSystemGraph//" + xmlns + "edge",
                namespaces=self.ns,
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
                source_port = vedge.get("sourceport", None)
                target_port = vedge.get("targetport", None)
                # if vedge.get("sourceport"):
                #     edge.source_port = source_vertex.get_port(vedge.get("sourceport"))
                # if vedge.get("targetport"):
                #     edge.target_port = target_vertex.get_port(vedge.get("targetport"))
                edge = EdgeInfo(
                    source_vertex.identifier,
                    target_vertex.identifier,
                    source_port,
                    target_port,
                    set(
                        OpaqueTrait(traitnode.text)
                        for traitnode in vnode.iterfind("./" + xmlns + "traits")
                    ),
                )
                model.add_edge(
                    source_vertex, target_vertex, key=edge._str_key, object=edge
                )
        return model

    def read_data(self, elem):
        xmlns = "{forsyde.io.eclipse.systemgraph}"
        # it is a collection
        if (
            elem.get("xsi:type") == "forsyde.io.eclipse.systemgraph:IntVertexProperty"
            or elem.get("xsi:type")
            == "forsyde.io.eclipse.systemgraph:LongVertexProperty"
        ):
            return int(elem.get("intValue"))
        elif (
            elem.get("xsi:type") == "forsyde.io.eclipse.systemgraph:FloatVertexProperty"
            or elem.get("xsi:type")
            == "forsyde.io.eclipse.systemgraph:DoubleVertexProperty"
        ):
            return float(elem.get("floatValue"))
        elif (
            elem.get("xsi:type")
            == "forsyde.io.eclipse.systemgraph:BooleanVertexProperty"
        ):
            return bool(elem.get("booleanValue"))
        elif (
            elem.get("xsi:type")
            == "forsyde.io.eclipse.systemgraph:IntMapVertexProperty"
        ):
            mapping = dict()
            for i in range(0, len(elem), 2):
                # assumes data and then indexes
                mapping[int(elem[i + 1].text)] = self.read_data(elem[i])
            return mapping
        elif (
            elem.get("xsi:type")
            == "forsyde.io.eclipse.systemgraph:StringMapVertexProperty"
        ):
            mapping = dict()
            for i in range(0, len(elem), 2):
                # assumes data and then indexes
                mapping[elem[i + 1].ext] = self.read_data(elem[i])
            return mapping
        elif (
            elem.get("xsi:type") == "forsyde.io.eclipse.systemgraph:ArrayVertexProperty"
        ):
            return [self.read_data(child) for child in elem]
        else:
            return elem.text
