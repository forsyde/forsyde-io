/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Port;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.types.edge.EdgeFactory;
import forsyde.io.java.types.vertex.VertexFactory;

/**
 * @author rjordao
 *
 */
public class ForSyDeXMLDriver {
	
	static public ForSyDeModel loadModel(String filePath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		return loadModel(Files.newInputStream(Paths.get(filePath)));
	}
	
	static public void writeModel(ForSyDeModel model, String filePath) throws ParserConfigurationException, TransformerException, IOException {
		writeModel(model, Files.newOutputStream(Paths.get(filePath)));
	}
	
	/**
	 * 
	 * @param inStream
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 */
	static public ForSyDeModel loadModel(InputStream inStream) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// Build the ForSyDeModel representation
		ForSyDeModel model = new ForSyDeModel();
		// prepare XML internal representation and build it
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(inStream);
		// get the XPath object
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList vertexList = (NodeList) xPath.compile("/ForSyDeModel/Vertex").evaluate(xmlDoc, XPathConstants.NODESET);
		for(int i = 0; i < vertexList.getLength(); i++) {
			Element vertexElem = (Element) vertexList.item(i);
			// TODO: the type creation could be safer or signal some exception
			Vertex vertex = VertexFactory.createVertex(
					vertexElem.getAttribute("id"),
					vertexElem.getAttribute("type"));
			model.addVertex(vertex);
			// iterate through ports and add them
			NodeList portsList = (NodeList) xPath.compile("Port").evaluate(vertexElem, XPathConstants.NODESET);
			for(int j = 0; j < portsList.getLength(); j++) {
				Element portElem = (Element) portsList.item(j);
				// TODO: the type creation could be safer or signal some exception
				Port port = new Port(
						portElem.getAttribute("id")
						);
				vertex.ports.add(port);
			}
			// iterate through properties and add them
			NodeList propertyList = (NodeList) xPath.compile("Property").evaluate(vertexElem, XPathConstants.NODESET);
			for(int j = 0; j < propertyList.getLength(); j++) {
				Element propertyElem = (Element) propertyList.item(j);
				// TODO: the type creation could be safer or signal some exception
				Object property = readXMLIntoMap(propertyElem);
				vertex.properties.put(propertyElem.getAttribute("name"), property);
			}
		}
		NodeList edgeList = (NodeList) xPath.compile("/ForSyDeModel/Edge").evaluate(xmlDoc, XPathConstants.NODESET);
		for(int i = 0; i < edgeList.getLength(); i++) {
			Element edgeElem = (Element) edgeList.item(i);
			String sid = edgeElem.getAttribute("source_id");
			String tid = edgeElem.getAttribute("target_id");
			// TODO: later put more safety here, even though for consistency should never fail
			Vertex source = model.vertexSet().stream().filter(v -> v.identifier.equals(sid)).findFirst().get();
			Vertex target = model.vertexSet().stream().filter(v -> v.identifier.equals(tid)).findFirst().get();
			Edge edge = EdgeFactory.createEdge(source, target, edgeElem.getAttribute("type"));
			if (edgeElem.hasAttribute("source_port_id")) {
				Port sourcePort = source.ports.stream().filter(p -> p.identifier.equals(edgeElem.getAttribute("source_port_id"))).findFirst().get();
				edge.sourcePort = Optional.of(sourcePort);
			}
			if (edgeElem.hasAttribute("target_port_id")) {
				Port targetPort = source.ports.stream().filter(p -> p.identifier.equals(edgeElem.getAttribute("target_port_id"))).findFirst().get();
				edge.targetPort = Optional.of(targetPort);
			}
			model.addEdge(source, target, edge);
		}
		return model;
	}
	
	static public void writeModel(ForSyDeModel model, OutputStream outStream) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("ForSyDeModel");
		doc.appendChild(root);
		for (Vertex v : model.vertexSet()) {
			Element vElem = doc.createElement("Vertex");
			vElem.setAttribute("id", v.identifier);
			vElem.setAttribute("type", v.getTypeName());
			root.appendChild(vElem);
			for (Port p : v.ports) {
				Element pElem = doc.createElement("Port");
				pElem.setAttribute("id", p.identifier);
				vElem.appendChild(pElem);
			}
			writeMapIntoXML(doc, vElem, v.properties);
		}
		for(Edge e : model.edgeSet()) {
			Element eElem = doc.createElement("Edge");
			eElem.setAttribute("source_id", e.source.identifier);
			eElem.setAttribute("target_id", e.target.identifier);
			eElem.setAttribute("type", e.getTypeName());
			if (e.sourcePort.isPresent()) {
				eElem.setAttribute("source_port_id", e.sourcePort.get().identifier);
			}
			if (e.targetPort.isPresent()) {
				eElem.setAttribute("target_port_id", e.targetPort.get().identifier);
			}
			root.appendChild(eElem);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(outStream));
	}
	
	/**
	 * 
	 * @param elem
	 * @return
	 */
	static protected Object readXMLIntoMap(Element elem) {
		// it is a collection
		NodeList children = elem.getElementsByTagName("Property");
		if (children.getLength() > 0) {
			Element first = (Element) children.item(0);
			// it is a map
			if(first.hasAttribute("name")) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for(int i = 0; i < children.getLength(); i++) {
					Element child = (Element) children.item(i);
					map.put(child.getAttribute("name"), readXMLIntoMap(child));
				}
				return map;
			}
			// it is a list
			else if(first.hasAttribute("index")) {
				ArrayList<Object> list = new ArrayList<Object>();
				for(int i = 0; i <  children.getLength(); i++) {
					Element child = (Element) children.item(i);
					//TODO: This can maybe break if the index are out of order, to be fixed later
					list.add(Integer.parseInt(child.getAttribute("index")), readXMLIntoMap(child));
				}
				return list;
			} 
			// nothing, default to an unordered set
			else {
				HashSet<Object> set = new HashSet<Object>();
				for(int i = 0; i <  children.getLength(); i++) {
					Element child = (Element) children.item(i);
					//TODO: This can maybe break if the index are out of order, to be fixed later
					set.add(readXMLIntoMap(child));
				}
				return set;
			}
		} 
		// it is a single value
		else {
			switch (elem.getAttribute("type")) {
			case "Integer":
				return Integer.valueOf(elem.getTextContent());
			case "Float":
				return Double.valueOf(elem.getTextContent());
			default:
				return elem.getTextContent(); 
			}
		}
	}
	
	static protected void writeMapIntoXML(Document doc, Element parent, Object value) {
		if (value instanceof HashMap) {
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			for(String key : map.keySet()) {
				Object mapValue = map.get(key);
				Element child = doc.createElement("Property");
				child.setAttribute("name", key);
				writeMapIntoXML(doc, child, mapValue);
				parent.appendChild(child);
			}
		} else if (value instanceof ArrayList) {
			ArrayList<Object> list = (ArrayList<Object>) value;
			for (int i = 0; i < list.size(); i++) {
				Element child = doc.createElement("Property");
				child.setAttribute("index", String.valueOf(i));
				writeMapIntoXML(doc, child, list.get(i));
				parent.appendChild(child);
			}
		} else if (value instanceof HashSet) {
			HashSet<Object> set = (HashSet<Object>) value;
			for(Object o : set) {
				Element child = doc.createElement("Property");
				writeMapIntoXML(doc, child, o);
				parent.appendChild(child);
			}
		} else {
			if (value instanceof Integer) {
				parent.setAttribute("type", "Integer");
			} else if (value instanceof Double) {
				parent.setAttribute("type", "Float");
			}
			parent.setTextContent(value.toString());
		}
	}

}
