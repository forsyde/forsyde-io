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
public class ForSyDeMLDriver extends ForSyDeModelDriver {

	/**
	 * 
	 * @param inStream
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws XPathExpressionException
	 */
	public ForSyDeModel loadModel(InputStream inStream)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// Build the ForSyDeModel representation
		ForSyDeModel model = new ForSyDeModel();
		// prepare XML internal representation and build it
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(inStream);
		// get the XPath object
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList vertexList = (NodeList) xPath.compile("/graphml//graph/node").evaluate(xmlDoc, XPathConstants.NODESET);
		for (int i = 0; i < vertexList.getLength(); i++) {
			Element vertexElem = (Element) vertexList.item(i);
			// TODO: the type creation could be safer or signal some exception
			Vertex vertex = VertexFactory.createVertex(vertexElem.getAttribute("id"), vertexElem.getAttribute("type"));
			model.addVertex(vertex);
			// iterate through ports and add them
			NodeList portsList = (NodeList) xPath.compile("port").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < portsList.getLength(); j++) {
				Element portElem = (Element) portsList.item(j);
				// TODO: the type creation could be safer or signal some exception
				Port port = new Port(portElem.getAttribute("name"));
				vertex.ports.add(port);
			}
			// iterate through properties and add them
			NodeList propertyList = (NodeList) xPath.compile("data").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < propertyList.getLength(); j++) {
				Element propertyElem = (Element) propertyList.item(j);
				// TODO: the type creation could be safer or signal some exception
				Object property = readData(propertyElem);
				vertex.properties.put(propertyElem.getAttribute("attr.name"), property);
			}
		}
		NodeList edgeList = (NodeList) xPath.compile("/graphml//graph/edge").evaluate(xmlDoc, XPathConstants.NODESET);
		for (int i = 0; i < edgeList.getLength(); i++) {
			Element edgeElem = (Element) edgeList.item(i);
			String sid = edgeElem.getAttribute("source");
			String tid = edgeElem.getAttribute("target");
			// TODO: later put more safety here, even though for consistency should never
			// fail
			Vertex source = model.vertexSet().stream().filter(v -> v.identifier.equals(sid)).findFirst().get();
			Vertex target = model.vertexSet().stream().filter(v -> v.identifier.equals(tid)).findFirst().get();
			Edge edge = EdgeFactory.createEdge(source, target, edgeElem.getAttribute("type"));
			if (edgeElem.hasAttribute("sourceport")) {
				Port sourcePort = source.ports.stream()
						.filter(p -> p.identifier.equals(edgeElem.getAttribute("sourceport"))).findFirst().get();
				edge.sourcePort = Optional.of(sourcePort);
			}
			if (edgeElem.hasAttribute("targetport")) {
				Port targetPort = target.ports.stream()
						.filter(p -> p.identifier.equals(edgeElem.getAttribute("targetport"))).findFirst().get();
				edge.targetPort = Optional.of(targetPort);
			}
			model.addEdge(source, target, edge);
		}
		return model;
	}

	public void writeModel(ForSyDeModel model, OutputStream outStream)
			throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("graphml");
		root.setAttribute("xmlns", "http://graphml.graphdrawing.org/xmlns");
		Element graph = doc.createElement("graph");
		graph.setAttribute("id", "model");
		graph.setAttribute("edgedefault", "directed");
		root.appendChild(graph);
		doc.appendChild(root);
		for (Vertex v : model.vertexSet()) {
			Element vElem = doc.createElement("node");
			vElem.setAttribute("id", v.identifier);
			vElem.setAttribute("type", v.getTypeName());
			graph.appendChild(vElem);
			for (Port p : v.ports) {
				Element pElem = doc.createElement("port");
				pElem.setAttribute("name", p.identifier);
				vElem.appendChild(pElem);
			}
			for (String key : v.properties.keySet()) {
				Element propElem = writeData(doc, v.properties.get(key));
				propElem.setAttribute("attr.name", key);
				vElem.appendChild(propElem);
			}
		}
		for (Edge e : model.edgeSet()) {
			Element eElem = doc.createElement("edge");
			eElem.setAttribute("source", e.source.identifier);
			eElem.setAttribute("target", e.target.identifier);
			eElem.setAttribute("type", e.getTypeName());
			if (e.sourcePort.isPresent()) {
				eElem.setAttribute("sourceport", e.sourcePort.get().identifier);
			}
			if (e.targetPort.isPresent()) {
				eElem.setAttribute("targetport", e.targetPort.get().identifier);
			}
			graph.appendChild(eElem);
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
	static protected Object readData(Element elem) {
		// it is a collection
		if (elem.getAttribute("attr.type").equals("integer")) {
			return Integer.valueOf(elem.getTextContent());
		} else if (elem.getAttribute("attr.type").equals("int")) {
			return Integer.valueOf(elem.getTextContent());
		} else if (elem.getAttribute("attr.type").equals("float")) {
			return Float.valueOf(elem.getTextContent());
		} else if (elem.getAttribute("attr.type").equals("double")) {
			return Double.valueOf(elem.getTextContent());
		} else if (elem.getAttribute("attr.type").equals("boolean")) {
			return Boolean.valueOf(elem.getTextContent());
		} else if (elem.getAttribute("attr.type").equals("object")) {
			HashMap<String, Object> object = new HashMap<String, Object>();
			NodeList children = elem.getElementsByTagName("data");
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				object.put(child.getAttribute("attr.name"), readData(child));
			}
			return object;
		} else if (elem.getAttribute("attr.type").equals("array")) {
			NodeList children = elem.getElementsByTagName("data");
			ArrayList<Object> array = new ArrayList<Object>(children.getLength());
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				array.set(Integer.valueOf(child.getAttribute("attr.name")), readData(child));
			}
			return array;
		} else {
			return elem.getTextContent();
		}
	}

	static protected Element writeData(Document doc, Object value) {
		Element newElem = doc.createElement("data");
		if (value instanceof HashMap) {
			HashMap<String, Object> map = (HashMap<String, Object>) value;
			newElem.setAttribute("attr.type", "object");
			for (String key : map.keySet()) {
				Element child = writeData(doc, map.get(key));
				child.setAttribute("attr.name", key);
				newElem.appendChild(child);
			}
		} else if (value instanceof ArrayList) {
			ArrayList<Object> list = (ArrayList<Object>) value;
			newElem.setAttribute("attr.type", "array");
			for (int i = 0; i < list.size(); i++) {
				Element child = writeData(doc, list.get(i));
				child.setAttribute("attr.name", String.valueOf(i));
				newElem.appendChild(child);
			}
		} else if (value instanceof Integer) {
			newElem.setAttribute("attr.type", "integer");
			newElem.setTextContent(value.toString());
		} else if (value instanceof Float) {
			newElem.setAttribute("attr.type", "float");
			newElem.setTextContent(value.toString());
		} else if (value instanceof Double) {
			newElem.setAttribute("attr.type", "double");
			newElem.setTextContent(value.toString());
		} else if (value instanceof Boolean) {
			newElem.setAttribute("attr.type", "boolean");
			newElem.setTextContent(value.toString());
		} else {
			newElem.setAttribute("attr.type", "string");
			newElem.setTextContent(value.toString());
		}
		return newElem;
	}

}
