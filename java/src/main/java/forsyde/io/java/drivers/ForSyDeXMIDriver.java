/**
 * 
 */
package forsyde.io.java.drivers;

import forsyde.io.java.core.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.NamespaceContext;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author rjordao
 *
 */
public class ForSyDeXMIDriver implements ForSyDeModelDriver {

	private final static Set<String> allowedVertexes = Stream.of(VertexTrait.values()).map(t -> t.getName())
			.collect(Collectors.toSet());
	private final static Set<String> allowedEdges = Stream.of(EdgeTrait.values()).map(t -> t.getName())
			.collect(Collectors.toSet());
	private final static XPath xPath = XPathFactory.newInstance().newXPath();

	protected static class ForSyDeXMINamespace implements NamespaceContext {

		@Override
		public String getNamespaceURI(String s) {
			if (s.equals("forsydeio") || s.equals("graph")) {
				return "forsyde.io.system.graph";
			} else if (s.equals("xmi")) {
				return "http://www.omg.org/XMI";
			} else if (s.equals("xsi")) {
				return "http://www.w3.org/2001/XMLSchema-instance";
			} else if (s.equals("xmlns")) {
				return "http://www.w3.org/2000/xmlns/";
			}
			return null;
		}

		@Override
		public String getPrefix(String s) {
			if (s.equals("forsyde.io.system.graph")) {
				return "forsydeio";
			} else if (s.equals("http://www.omg.org/XMI")) {
				return "xmi";
			} else if (s.equals("http://www.w3.org/2001/XMLSchema-instance")) {
				return "xsi";
			} else if (s.equals("http://www.w3.org/2000/xmlns/")) {
				return "xmlns";
			}
			return null;
		}

		@Override
		public Iterator<String> getPrefixes(String s) {
			return null;
		}
	}

	public ForSyDeXMIDriver() {
		xPath.setNamespaceContext(new ForSyDeXMINamespace());
	}

	@Override
	public List<String> inputExtensions() {
		return List.of("forsyde.xmi", "forxmi");
	}

	@Override
	public List<String> outputExtensions() {
		return List.of("forsyde.xmi", "forxmi");
	}

	/**
	 * Parses ForSyDe's graphML varatiation schema.
	 * 
	 * @param inStream the {@link InputStream} to fetch the model from.
	 * @return the parsed {@link ForSyDeSystemGraph} from whichever format was fed to the
	 *         function.
	 * @throws ParserConfigurationException TODO.
	 * @throws SAXException                 In case something goes wrong with any
	 *                                      XML input parsing.
	 * @throws IOException                  In case something goes wrong with the
	 *                                      input strema.
	 * @throws XPathExpressionException     The XPaths are statically compiled, so
	 *                                      this shound not normally happen.
	 */
	public ForSyDeSystemGraph loadModel(InputStream inStream)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// Build the ForSyDeModel representation
		ForSyDeSystemGraph model = new ForSyDeSystemGraph();
		// prepare XML internal representation and build it
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(inStream);

		// get the XPath object
		NodeList vertexList = (NodeList) xPath.compile("/graph:ForSyDeSystemGraph//nodes").evaluate(xmlDoc, XPathConstants.NODESET);
		for (int i = 0; i < vertexList.getLength(); i++) {
			Element vertexElem = (Element) vertexList.item(i);
			Vertex vertex = new Vertex(vertexElem.getAttribute("identifier"));
			NodeList vertexTraitList = (NodeList) xPath.compile("graph:traits").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < vertexTraitList.getLength(); j++) {
				final String name = vertexTraitList.item(j).getTextContent();
				vertex.vertexTraits.add(allowedVertexes.contains(name) ?
						VertexTrait.valueOf(name) : new OpaqueTrait(name));
			}
			model.addVertex(vertex);
			// iterate through ports and add them
			NodeList portsList = (NodeList) xPath.compile("graph:ports").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < portsList.getLength(); j++) {
				Element portElem = (Element) portsList.item(j);
				vertex.ports.add(portElem.getTextContent());
			}
			// iterate through properties and add them
			NodeList propertyValuesList = (NodeList) xPath.compile("graph:propertiesValues").evaluate(vertexElem, XPathConstants.NODESET);
			NodeList propertyNamesList = (NodeList) xPath.compile("graph:propertiesNames").evaluate(vertexElem, XPathConstants.NODESET);
			for (int j = 0; j < propertyValuesList.getLength(); j++) {
				Element propertyElem = (Element) propertyValuesList.item(j);
				VertexProperty property = readData(propertyElem);
				vertex.properties.put(propertyNamesList.item(j).getTextContent(), property);
			}
		}
		NodeList edgeList = (NodeList) xPath.compile("/graph:ForSyDeSystemGraph//edges").evaluate(xmlDoc, XPathConstants.NODESET);
		for (int i = 0; i < edgeList.getLength(); i++) {
			Element edgeElem = (Element) edgeList.item(i);
			String sid = edgeElem.getAttribute("source");
			String tid = edgeElem.getAttribute("target");
			// TODO: later put more safety here, even though for consistency should never
			// fail
			Vertex source = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(sid)).findFirst().get();
			Vertex target = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(tid)).findFirst().get();
			Edge edge = new Edge(source, target);
			NodeList edgeTraitList = (NodeList) xPath.compile("graph:traits").evaluate(edgeElem, XPathConstants.NODESET);
			for (int j = 0; j < edgeTraitList.getLength(); j++) {
				final String name = edgeTraitList.item(j).getTextContent();
				edge.edgeTraits.add(allowedEdges.contains(name) ?
						EdgeTrait.valueOf(name) : new OpaqueTrait(name));
			}
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

	public void writeModel(ForSyDeSystemGraph model, OutputStream outStream)
			throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement("graph:ForSyDeSystemGraph");
		root.setAttribute("xmi:version", "2.0");
		root.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xmlns:graph", "forsyde.io.system.graph");
		root.setAttribute("xsi:schemaLocation", "forsyde.io.system.graph systemgraph.xcore#/EPackage");
		// Element graph = doc.createElement( "ForSyDeSystemGraph");
		// graph.setAttribute("id", "model");
		// graph.setAttribute("edgedefault", "directed");
		//root.appendChild(graph);
		doc.appendChild(root);
		for (Vertex v : model.vertexSet()) {
			Element vElem = doc.createElement("nodes");
			vElem.setAttribute("identifier", v.getIdentifier());
			v.vertexTraits.forEach(t -> {
				final Element traitElem = doc.createElement( "traits");
				traitElem.setTextContent(t.getName());
				vElem.appendChild(traitElem);
			});
			//vElem.setAttribute("traits", v.getTraits().stream().map(t -> t.getName()).collect(Collectors.joining(";")));
			root.appendChild(vElem);
			for (String p : v.getPorts()) {
				Element pElem = doc.createElement("ports");
				pElem.setTextContent(p);
				//pElem.setAttribute("name", p);
				vElem.appendChild(pElem);
			}
			for (String key : v.getProperties().keySet()) {
				Element propElem = writeData(doc, v.getProperties().get(key));
				doc.renameNode(propElem, null, "propertiesValues");
				//propElem.setAttribute("attr.name", key);
				vElem.appendChild(propElem);
				final Element name = doc.createElement( "propertiesNames");
				name.setTextContent(key);
				vElem.appendChild(name);
			}
		}
		for (Edge e : model.edgeSet()) {
			Element eElem = doc.createElement( "edges");
			eElem.setAttribute("source", e.getSource().getIdentifier());
			eElem.setAttribute("target", e.getTarget().getIdentifier());
			e.edgeTraits.forEach(t -> {
				final Element traitElem = doc.createElement( "traits");
				traitElem.setTextContent(t.getName());
				eElem.appendChild(traitElem);
			});
			// eElem.setAttribute("traits", e.getTraits().stream().map(t -> t.getName()).collect(Collectors.joining(";")));
			if (e.getSourcePort().isPresent()) {
				eElem.setAttribute("sourceport", e.getSourcePort().get());
			}
			if (e.getTargetPort().isPresent()) {
				eElem.setAttribute("targetport", e.getTargetPort().get());
			}
			root.appendChild(eElem);
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
	 * @throws XPathExpressionException 
	 */
	static protected VertexProperty readData(Element elem) throws XPathExpressionException {
		// it is a collection
		if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:IntVertexProperty")) {
			return VertexProperty.create(Integer.valueOf(elem.getAttributeNS("forsyde.io.system.graph", "intValue")));
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:FloatVertexProperty")) {
			return VertexProperty.create(Float.valueOf(elem.getAttributeNS("forsyde.io.system.graph", "floatValue")));
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:LongVertexProperty")) {
			return VertexProperty.create(Long.valueOf(elem.getAttributeNS("forsyde.io.system.graph", "longValue")));
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:DoubleVertexProperty")) {
			return VertexProperty.create(Double.valueOf(elem.getAttributeNS("forsyde.io.system.graph", "doubleValue")));
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:BooleanVertexProperty")) {
			return VertexProperty.create(Boolean.valueOf(elem.getAttributeNS("forsyde.io.system.graph", "booleanValue")));
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:IntMapVertexProperty")) {
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			NodeList children = (NodeList) xPath.compile("graph:values").evaluate(elem, XPathConstants.NODESET);
			NodeList childrenIndexes = (NodeList) xPath.compile("graph:indexes").evaluate(elem, XPathConstants.NODESET);
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				map.put(Integer.valueOf(childrenIndexes.item(i).getTextContent()), readData(child));
			}
			return VertexProperty.create(map);
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:StringMapVertexProperty")) {
			Map<String, Object> map = new HashMap<String, Object>();
			NodeList children = (NodeList) xPath.compile("graph:values").evaluate(elem, XPathConstants.NODESET);
			NodeList childrenIndexes = (NodeList) xPath.compile("graph:indexes").evaluate(elem, XPathConstants.NODESET);
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				map.put(childrenIndexes.item(i).getTextContent(), readData(child));
			}
			return VertexProperty.create(map);
		} else if (elem.getAttribute("xsi:type").equalsIgnoreCase("graph:ArrayVertexProperty")) {
			NodeList children = (NodeList) xPath.compile("graph:values").evaluate(elem, XPathConstants.NODESET);
			List<Object> array = new ArrayList<>(children.getLength());
			for (int i = 0; i < children.getLength(); i++) {
				Element child = (Element) children.item(i);
				array.set(i, readData(child));
			}
			return VertexProperty.create(array);
		} else {
			return VertexProperty.create(elem.getTextContent());
		}
	}

	static protected Element writeData(Document doc, VertexProperty prop) {
		final Element newElem = doc.createElement( "values");
		return VertexProperties.cases()
				.StringVertexProperty(s -> {
					newElem.setAttribute("xsi:type", "graph:StringVertexProperty");
					newElem.setAttribute("string", s);
					return newElem;
				})
				.IntVertexProperty(i -> {
					newElem.setAttribute("xsi:type", "graph:IntVertexProperty");
					newElem.setAttribute("intValue", i.toString());
					return newElem;
				})
				.BooleanVertexProperty(b -> {
					newElem.setAttribute("xsi:type", "graph:BooleanVertexProperty");
					newElem.setAttribute("booleanValue", b.toString());
					return newElem;
				})
				.FloatVertexProperty(f -> {
					newElem.setAttribute("xsi:type", "graph:FloatVertexProperty");
					newElem.setAttribute("floatValue", f.toString());
					return newElem;
				})
				.LongVertexProperty(l -> {
					newElem.setAttribute("xsi:type", "graph:LongVertexProperty");
					newElem.setAttribute("longValue", l.toString());
					return newElem;
				})
				.ArrayVertexProperty(array -> {
					newElem.setAttribute("xsi:type", "graph:ArrayVertexProperty");
					for (VertexProperty vertexProperty : array) {
						Element child = writeData(doc, vertexProperty);
						//child.setAttribute("attr.name", String.valueOf(i));
						newElem.appendChild(child);
					}
					return newElem;
				})
				.IntMapVertexProperty(intMap -> {
					newElem.setAttribute("xsi:type", "graph:IntMapVertexProperty");
					// newElem.setAttribute("attr.type", "intMap");
					for (Integer key : intMap.keySet()) {
						Element child = writeData(doc, intMap.get(key));
						final Element index = doc.createElement( "indexes");
						index.setTextContent(key.toString());
						//child.setAttribute("attr.name", key.toString());
						newElem.appendChild(child);
						newElem.appendChild(index);
					}
					return newElem;
				})
				.StringMapVertexProperty(stringMap -> {
					newElem.setAttribute("xsi:type", "graph:StringMapVertexProperty");
					for (String key : stringMap.keySet()) {
						Element child = writeData(doc, stringMap.get(key));
						final Element index = doc.createElement( "indexes");
						index.setTextContent(key.toString());
						//child.setAttribute("attr.name", key);
						newElem.appendChild(child);
						newElem.appendChild(index);
					}
					return newElem;
				})
				.otherwise_(newElem)
				.apply(prop);
	}

}
