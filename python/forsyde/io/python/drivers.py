from typing import Any
from typing import Optional

# from lxml import etree  # type: ignore
import xml.etree.ElementTree as etree

from forsyde.io.python.core import Vertex
from forsyde.io.python.core import Edge
from forsyde.io.python.core import ForSyDeModel
from forsyde.io.python.core import Port
from forsyde.io.python.types import VertexFactory
from forsyde.io.python.types import EdgeFactory


class ForSyDeModelDriver:

    def write(self, model: ForSyDeModel, sink: str) -> None:
        pass

    def read(self, source: str, other_model: Optional[ForSyDeModel] = None) -> Optional[ForSyDeModel]:
        return None


class ForSyDeMLDriver(ForSyDeModelDriver):

    def __init__(self):
        self.ns = {'xmlns': 'http://graphml.graphdrawing.org/xmlns'}

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

    def write(self, model: ForSyDeModel, sink: str) -> None:
        xmlns = '{http://graphml.graphdrawing.org/xmlns}'
        root = etree.Element(xmlns + 'graphml')
        graph = etree.SubElement(root, xmlns + 'graph')
        graph.set('id', 'model')
        graph.set('edgedefault', 'directed')
        for v in model.nodes:
            node_elem = etree.SubElement(graph, xmlns + 'node')
            node_elem.set('id', v.identifier)
            node_elem.set('type', v.get_type_tag())
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, xmlns + 'port')
                port_elem.set('name', port.identifier)
            prop_to_save = [(node_elem, v.properties)]
            while len(prop_to_save) > 0:
                (parent, current) = prop_to_save.pop()
                cur_iter = enumerate(current) if isinstance(current, list) else current.items()
                for (prop, val) in cur_iter:
                    prop_elem = etree.SubElement(parent, xmlns + 'data')
                    prop_elem.set('attr.name', str(prop))
                    prop_elem.set('attr.type', self.type_to_str(type(val)))
                    # prop_elem.text = json.dumps(val)
                    # it is a terminal property
                    if isinstance(val, int)\
                            or isinstance(val, float)\
                            or isinstance(val, str)\
                            or isinstance(val, bool):
                        prop_elem.text = str(val)
                    # it is not
                    else:
                        prop_to_save.append((prop_elem, val))
        for (s, t, edge) in model.edges.data("object"):
            edge_elem = etree.SubElement(graph, xmlns + 'edge')
            edge_elem.set('source', s.identifier)
            edge_elem.set('target', t.identifier)
            edge_elem.set('type', edge.get_type_tag())
            if edge.source_vertex_port:
                edge_elem.set('sourceport', edge.source_vertex_port.identifier)
            if edge.target_vertex_port:
                edge_elem.set('targetport', edge.target_vertex_port.identifier)
        with open(sink, 'wb') as sinkstream:
            tree = etree.ElementTree(element=root)
            tree.write(sinkstream, encoding="utf-8", xml_declaration=True)
            #sinkstream.write(etree.tostring(tree, pretty_print=True, xml_declaration=True, encoding='UTF-8'))

    def read(self, source: str, other_model: Optional[ForSyDeModel] = None) -> ForSyDeModel:
        xmlns = '{http://graphml.graphdrawing.org/xmlns}'
        model = ForSyDeModel() if not other_model else other_model
        with open(source, 'r') as instream:
            tree = etree.parse(instream)
            for vnode in tree.findall('.//' + xmlns + 'graph/' + xmlns + 'node'):
                vertex = VertexFactory.build(identifier=vnode.get('id'), type_name=vnode.get('type'))
                model.add_node(vertex, label=vertex.identifier)
                for portnode in vnode.iterfind('./' + xmlns + "port"):
                    port = Port(identifier=portnode.get('name'))
                    vertex.ports.add(port)
                dataopen = [(vnode, vertex.properties)]
                while len(dataopen) > 0:
                    (parent, data) = dataopen.pop()
                    for datanode in parent.iterfind(xmlns + 'data'):
                        dataname = datanode.get('attr.name')
                        datatype = self.str_to_type(datanode.get('attr.type'))
                        if datatype is str\
                                or datatype is int\
                                or datatype is float:
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
            for vedge in tree.iterfind('.//' + xmlns + 'graph/' + xmlns + 'edge',
                                      namespaces=self.ns):
                source_vertex = next(n for (n, nid) in model.nodes.data('label') if nid == vedge.get('source'))
                target_vertex = next(n for (n, nid) in model.nodes.data('label') if nid == vedge.get('target'))
                edge = EdgeFactory.build(source=source_vertex, target=target_vertex, type_name=vedge.get('type'))
                if vedge.get('sourceport'):
                    edge.source_vertex_port = source_vertex.get_port(vedge.get('sourceport'))
                if vedge.get('targetport'):
                    edge.target_vertex_port = target_vertex.get_port(vedge.get('targetport'))
                key = (f"{vedge.get('source')}:{vedge.get('sourceport')}->" +
                       f"{vedge.get('target')}:{vedge.get('targetport')}")
                model.add_edge(source_vertex, target_vertex, key=key, object=edge)
        return model


class ForSyDeXMLDriver(ForSyDeModelDriver):

    def property_to_xml(self, parent: etree.Element, prop: Any) -> None:
        '''Transform an object into the expected XML element layout
        
        The library 'dicttoxml' was considered but dropped for the moment
        since there seemed to be no easy way to control the tag generation
        as this function does by always creating "Property".
        '''
        if isinstance(prop, dict):
            for (k, v) in prop.items():
                child = etree.SubElement(parent, 'Property')
                child.set('name', k)
                self.property_to_xml(child, v)
        elif isinstance(prop, list):
            for (i, v) in enumerate(prop):
                child = etree.SubElement(parent, 'Property')
                child.set('index', i)
                self.property_to_xml(child, v)
        elif isinstance(prop, set):
            for v in prop:
                child = etree.SubElement(parent, 'Property')
                self.property_to_xml(child, v)
        else:
            if isinstance(prop, int):
                parent.set('type', 'Integer')
            elif isinstance(prop, float):
                parent.set('type', 'Float')
            parent.text = str(prop)

    def xml_to_property(self, elem: etree.Element) -> Any:
        '''Collect children of the element to expected property layout

        It collects the children recursively of 'elem' to the expected
        formats and types.
        '''
        if len(elem.iterfind("Property[@index]")) > 0:
            elems = elem.iterfind("Property[@index]")
            l = [None for i in range(len(elems))]
            for e in elems:
                l[int(e.get('index'))] = self.xml_to_property(e)
            return l
        elif len(elem.iterfind("Property[@name]")) > 0:
            return {e.get("name"): self.xml_to_property(e) for e in elem.iterfind("Property[@name]")}
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
        tree = etree.Element('ForSyDe')
        for v in model.nodes:
            node_elem = etree.SubElement(tree, 'Vertex')
            node_elem.set('id', v.identifier)
            node_elem.set('type', v.get_type_tag())
            for port in v.ports:
                port_elem = etree.SubElement(node_elem, 'Port')
                port_elem.set('id', port.identifier)
                if port.port_type == int:
                    port_elem.set('type', 'Integer')
                elif port.port_type == float:
                    port_elem.set('type', 'Integer')
                elif issubclass(port.port_type, Vertex):
                    port_elem.set('type', str(port.port_type))
                else:
                    port_elem.set('type', 'String')
            for (prop, val) in v.properties.items():
                prop_elem = etree.SubElement(node_elem, 'Property')
                prop_elem.set('name', prop)
                # prop_elem.text = json.dumps(val)
                self.property_to_xml(prop_elem, val)
        for (s, t, edge) in model.edges.data("object"):
            edge_elem = etree.SubElement(tree, 'Edge')
            edge_elem.set('source_id', s.identifier)
            edge_elem.set('target_id', t.identifier)
            edge_elem.set('type', edge.get_type_tag())
            if edge.source_vertex_port:
                edge_elem.set('source_port_id', edge.source_vertex_port.identifier)
            if edge.target_vertex_port:
                edge_elem.set('target_port_id', edge.target_vertex_port.identifier)
        with open(sink, 'w') as sinkstream:
            sinkstream.write(etree.tostring(tree, pretty_print=True, encoding='unicode'))

    def read(self, source: str, other_model: Optional[ForSyDeModel] = None) -> ForSyDeModel:
        model = ForSyDeModel() if not other_model else other_model
        with open(source, 'r') as instream:
            tree = etree.parse(instream)
            for vnode in tree.iterfind('/ForSyDeModel/Vertex'):
                vertex = VertexFactory.build(identifier=vnode.get('id'), type_name=vnode.get('type'))
                model.add_node(vertex, label=vertex.identifier)
                for portnode in vnode.iterfind("Port"):
                    port = Port(identifier=portnode.get('id'))
                    if portnode.get('type') == "Integer":
                        port.port_type = int
                    elif portnode.get('type') == "Float":
                        port.port_type = float
                    else:
                        try:
                            port.port_type = VertexFactory.get_type_from_name(portnode.get('type'))
                        except NotImplementedError:
                            port.port_type = str
                    vertex.ports.add(port)
                vertex.properties = self.xml_to_property(vnode)
                # for propnode in vnode.iterfind('Property'):
                #     prop_name = propnode.get('name')
                #     prop_val = model.xml_to_property(
                #         propnode)  # json.loads(propnode.text)
                #     vertex.properties[prop_name] = prop_val
            for vedge in tree.iterfind('/ForSyDeModel/Edge'):
                source_vertex = next(n for (n, nid) in model.nodes.data('label') if nid == vedge.get('source_id'))
                target_vertex = next(n for (n, nid) in model.nodes.data('label') if nid == vedge.get('target_id'))
                edge = EdgeFactory.build(source=source_vertex, target=target_vertex, type_name=vedge.get('type'))
                if vedge.get('source_port_id'):
                    edge.source_vertex_port = source_vertex.get_port(vedge.get('source_port_id'))
                if vedge.get('target_port_id'):
                    edge.target_vertex_port = target_vertex.get_port(vedge.get('target_port_id'))
                key = (f"{vedge.get('source_id')}:{vedge.get('source_port_id')}->" +
                       f"{vedge.get('target_id')}:{vedge.get('target_port_id')}")
                model.add_edge(source_vertex, target_vertex, key=key, object=edge)
        return model
