/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import forsyde.io.java.core.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author rjordao
 *
 */
public class ForSyDeMLDriver implements ForSyDeModelDriver {

	private static Set<String> allowedVertexes = Stream.of(VertexTrait.values()).map(t -> t.getName())
			.collect(Collectors.toSet());
	private static Set<String> allowedEdges = Stream.of(EdgeTrait.values()).map(t -> t.getName())
			.collect(Collectors.toSet());

	/**
	 * Parses ForSyDe's graphML varatiation schema.
	 * 
	 * @param inStream the {@link InputStream} to fetch the model from.
	 * @return the parsed {@link ForSyDeModel} from whichever format was fed to the
	 *         function.
	 * @throws ParserConfigurationException TODO.
	 * @throws SAXException                 In case something goes wrong with any
	 *                                      XML input parsing.
	 * @throws IOException                  In case something goes wrong with the
	 *                                      input strema.
	 * @throws XPathExpressionException     The XPaths are statically compiled, so
	 *                                      this shound not normally happen.
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
			Vertex vertex = new Vertex(vertexElem.getAttribute("id"));
			vertex.vertexTraits = Stream.of(vertexElem.getAttribute("traits").split(";"))
					.map(s -> allowedVertexes.contains(s) ? VertexTrait.valueOf(s) : new OpaqueTrait(s))
					.collect(Collectors.toSet());
			model.addVertex(vertex);
			// iterate through ports and add them
			NodeList portsList = (NodeList) xPath.compile("port").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < portsList.getLength(); j++) {
				Element portElem = (Element) portsList.item(j);
				vertex.ports.add(portElem.getAttribute("name"));
			}
			// iterate through properties and add them
			NodeList propertyList = (NodeList) xPath.compile("data").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < propertyList.getLength(); j++) {
				Element propertyElem = (Element) propertyList.item(j);
				VertexProperty property = readData(propertyElem);
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
			Vertex source = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(sid)).findFirst()
					.get();
			Vertex target = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(tid)).findFirst()
					.get();
			Edge edge = new Edge(source, target);
			edge.edgeTraits = Stream.of(edgeElem.getAttribute("traits").split(";"))
					.map(s -> allowedEdges.contains(s) ? EdgeTrait.valueOf(s) : new OpaqueTrait(s))
					.collect(Collectors.toSet());
			if (edgeElem.hasAttribute("sourceport")) {
				String sourcePort = source.getPorts().stream()
						.filter(p -> p.equals(edgeElem.getAttribute("sourceport"))).findFirst().get();
				edge.sourcePort = Optional.of(sourcePort);
			}
			if (edgeElem.hasAttribute("targetport")) {
				String targetPort = target.getPorts().stream()
						.filter(p -> p.equals(edgeElem.getAttribute("targetport"))).findFirst().get();
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
			vElem.setAttribute("id", v.getIdentifier());
			vElem.setAttribute("traits", v.getTraits().stream().map(t -> t.getName()).collect(Collectors.joining(";")));
			graph.appendChild(vElem);
			for (String p : v.getPorts()) {
				Element pElem = doc.createElement("port");
				pElem.setAttribute("name", p);
				vElem.appendChild(pElem);
			}
			for (String key : v.getProperties().keySet()) {
				Element propElem = writeData(doc, v.getProperties().get(key));
				propElem.setAttribute("attr.name", key);
				vElem.appendChild(propElem);
			}
		}
		for (Edge e : model.edgeSet()) {
			Element eElem = doc.createElement("edge");
			eElem.setAttribute("source", e.getSource().getIdentifier());
			eElem.setAttribute("target", e.getTarget().getIdentifier());
			eElem.setAttribute("traits", e.getTraits().stream().map(t -> t.getName()).collect(Collectors.joining(";")));
			if (e.getSourcePort().isPresent()) {
				eElem.setAttribute("sourceport", e.getSourcePort().get());
			}
			if (e.getTargetPort().isPresent()) {
				eElem.setAttribute("targetport", e.getTargetPort().get());
			}
			graph.appendChild(eElem);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(doc), new StreamResult(outStream));
	}

	/**
	 * Function to recursively build an data object for a {@link Vertex} object.
	 * 
	 * @param elem the XML element being parsed.
	 * @return the parsed object.
	 */
	static protected VertexProperty readData(Element elem) {
		// it is a collection
		if (elem.getAttribute("attr.type").equals("integer") || elem.getAttribute("attr.type").equals("int")) {
			return VertexProperty.create(Integer.valueOf(elem.getTextContent()));
		} else if (elem.getAttribute("attr.type").equals("float")) {
			return VertexProperty.create(Float.valueOf(elem.getTextContent()));
		} else if (elem.getAttribute("attr.type").equals("double")) {
			return VertexProperty.create(Double.valueOf(elem.getTextContent()));
		} else if (elem.getAttribute("attr.type").equals("boolean") || elem.getAttribute("attr.type").equals("bool")) {
			return VertexProperty.create(Boolean.valueOf(elem.getTextContent()));
		} else if (elem.getAttribute("attr.type").equals("intMap")) {
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			NodeList children = elem.getElementsByTagName("data");
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				map.put(Integer.valueOf(child.getAttribute("attr.name")), readData(child));
			}
			return VertexProperty.create(map);
		} else if (elem.getAttribute("attr.type").equals("stringMap")) {
			Map<String, Object> map = new HashMap<String, Object>();
			NodeList children = elem.getElementsByTagName("data");
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				map.put(child.getAttribute("attr.name"), readData(child));
			}
			return VertexProperty.create(map);
		} else if (elem.getAttribute("attr.type").equals("array")) {
			NodeList children = elem.getElementsByTagName("data");
			List<Object>  array = new ArrayList<>(children.getLength());
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				array.set(Integer.parseInt(child.getAttribute("attr.name")), readData(child));
			}
			return VertexProperty.create(array);
		} else {
			return VertexProperty.create(elem.getTextContent());
		}
	}

	static protected Element writeData(Document doc, VertexProperty prop) {
		Element newElem = doc.createElement("data");
		switch (prop.type) {
			case ARRAY:
				newElem.setAttribute("attr.type", "array");
				for (int i = 0; i < prop.array.size(); i++) {
					Element child = writeData(doc, prop.array.get(i));
					child.setAttribute("attr.name", String.valueOf(i));
					newElem.appendChild(child);
				}
				break;
			case INTMAP:
				newElem.setAttribute("attr.type", "intMap");
				for (Integer key : prop.intMap.keySet()) {
					Element child = writeData(doc, prop.intMap.get(key));
					child.setAttribute("attr.name", key.toString());
					newElem.appendChild(child);
				}
				break;
			case STRINGMAP:
				newElem.setAttribute("attr.type", "stringMap");
				for (String key : prop.stringMap.keySet()) {
					Element child = writeData(doc, prop.stringMap.get(key));
					child.setAttribute("attr.name", key);
					newElem.appendChild(child);
				}
				break;
			case INTEGER:
				newElem.setAttribute("attr.type", "integer");
				newElem.setTextContent(String.valueOf(prop.i));
				break;
			case DOUBLE:
				newElem.setAttribute("attr.type", "double");
				newElem.setTextContent(String.valueOf(prop.d));
				break;
			case LONG:
				newElem.setAttribute("attr.type", "long");
				newElem.setTextContent(String.valueOf(prop.l));
				break;
			case FLOAT:
				newElem.setAttribute("attr.type", "float");
				newElem.setTextContent(String.valueOf(prop.f));
				break;
			case BOOLEAN:
				newElem.setAttribute("attr.type", "boolean");
				newElem.setTextContent(String.valueOf(prop.b));
				break;
			default:
				newElem.setAttribute("attr.type", "string");
				newElem.setTextContent(prop.s);
				break;
		}
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, MapVertexProperty map) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "object");
		for (String key : map.keySet()) {
			Element child = writeData(doc, map.get(key));
			child.setAttribute("attr.name", key);
			newElem.appendChild(child);
		}
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, ArrayVertexProperty array) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "array");
		for (int i = 0; i < array.size(); i++) {
			Element child = writeData(doc, array.get(i));
			child.setAttribute("attr.name", String.valueOf(i));
			newElem.appendChild(child);
		}
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, IntegerVertexProperty num) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "int");
		newElem.setTextContent(String.valueOf(num.intValue()));
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, LongVertexProperty num) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "long");
		newElem.setTextContent(String.valueOf(num.longValue()));
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, FloatVertexProperty num) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "float");
		newElem.setTextContent(String.valueOf(num.floatValue()));
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, DoubleVertexProperty num) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "double");
		newElem.setTextContent(String.valueOf(num.doubleValue()));
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, BooleanVertexProperty b) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "boolean");
		newElem.setTextContent(b.toString());
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, StringVertexProperty str) {
		Element newElem = doc.createElement("data");
		newElem.setAttribute("attr.type", "string");
		newElem.setTextContent(str.toString());
		return newElem;
	}

	@Deprecated
	static protected Element writeData(Document doc, VertexPropertyElement value) {
		if (value instanceof IntegerVertexProperty) {
			return writeData(doc, (IntegerVertexProperty) value);
		} else if (value instanceof LongVertexProperty) {
			return writeData(doc, (LongVertexProperty) value);
		} else if (value instanceof FloatVertexProperty) {
			return writeData(doc, (FloatVertexProperty) value);
		} else if (value instanceof DoubleVertexProperty) {
			return writeData(doc, (DoubleVertexProperty) value);
		} else if (value instanceof MapVertexProperty) {
			return writeData(doc, (MapVertexProperty) value);
		} else if (value instanceof ArrayVertexProperty) {
			return writeData(doc, (ArrayVertexProperty) value);
		} else if (value instanceof BooleanVertexProperty) {
			return writeData(doc, (BooleanVertexProperty) value);
		} else {
			return writeData(doc, (StringVertexProperty) value);
		}
	}

}
