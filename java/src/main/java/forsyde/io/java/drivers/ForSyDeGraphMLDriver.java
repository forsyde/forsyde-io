package forsyde.io.java.drivers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import forsyde.io.java.core.*;
import forsyde.io.java.drivers.ForSyDeModelDriver;
import org.jgrapht.nio.graphml.GraphMLExporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ForSyDeGraphMLDriver implements ForSyDeModelDriver {

	GraphMLExporter<Vertex, Edge> graphMLExporter;

	ForSyDeGraphMLDriver() {
		graphMLExporter = new GraphMLExporter<>(Vertex::getIdentifier);
		graphMLExporter.setEdgeIdProvider(Edge::toIDString);
	}

	@Override
	public List<String> inputExtensions() {
		return List.of("graphml");
	}

	@Override
	public List<String> outputExtensions() {
		return List.of("graphml");
	}

	@Override
	@Deprecated
	public ForSyDeModel loadModel(InputStream in) throws Exception {
		throw new Exception("GraphML reading is not supported.");
	}

	@Override
	public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
		graphMLExporter.exportGraph(model, out);
//		List<String> vertexDataNames = new ArrayList<>();
//		List<String> vertexDataTypes = new ArrayList<>();
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document doc = builder.newDocument();
//		Element root = doc.createElement("graphml");
//		root.setAttribute("xmlns", "http://graphml.graphdrawing.org/xmlns");
//		Element graph = doc.createElement("graph");
//		graph.setAttribute("id", "model");
//		graph.setAttribute("edgedefault", "directed");
//		root.appendChild(graph);
//		doc.appendChild(root);
//		for (Vertex v : model.vertexSet()) {
//			Element vElem = doc.createElement("node");
//			vElem.setAttribute("id", v.getIdentifier());
//			// vElem.setAttribute("traits", v.vertexTraits.stream().map(t ->
//			// t.getName()).reduce("", (s1, s2) -> s1 + ";" + s2));
//			graph.appendChild(vElem);
//			for (String p : v.getPorts()) {
//				Element pElem = doc.createElement("port");
//				pElem.setAttribute("name", p);
//				vElem.appendChild(pElem);
//			}
//			for (String key : v.getProperties().keySet()) {
//				for (Integer i : writeData(vertexDataNames, vertexDataTypes, key, v.getProperties().get(key))) {
//					Element propElem = doc.createElement("data");
//					propElem.setAttribute("key", "d" + String.valueOf(i));
//					vElem.appendChild(propElem);
//				}
//			}
//		}
//		for (Edge e : model.edgeSet()) {
//			Element eElem = doc.createElement("edge");
//			eElem.setAttribute("source", e.getSource().getIdentifier());
//			eElem.setAttribute("target", e.getTarget().getIdentifier());
//			// eElem.setAttribute("traits", e.edgeTraits.stream().map(t ->
//			// t.getName()).reduce("", (s1, s2) -> s1 + ";" + s2));
//			if (e.getSourcePort().isPresent()) {
//				eElem.setAttribute("sourceport", e.getSourcePort().get());
//			}
//			if (e.getTargetPort().isPresent()) {
//				eElem.setAttribute("targetport", e.getTargetPort().get());
//			}
//			graph.appendChild(eElem);
//		}
//		for (int i = 0; i < vertexDataNames.size(); i++) {
//			Element dataElem = doc.createElement("key");
//			dataElem.setAttribute("for", "node");
//			dataElem.setAttribute("id", "d" + String.valueOf(i));
//			dataElem.setAttribute("attr.type", vertexDataTypes.get(i));
//			dataElem.setAttribute("attr.name", vertexDataNames.get(i));
//			root.appendChild(dataElem);
//		}
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		transformer.transform(new DOMSource(doc), new StreamResult(out));
	}

	static protected List<Integer> writeData(List<String> names, List<String> types, String name,
			VertexProperty value) {
		if (!names.contains(name)) {
			List<Integer> indexes = new ArrayList<>();
			switch (value.type) {
				case STRINGMAP:
					for (String key : value.stringMap.keySet()) {
						indexes.addAll(writeData(names, types, name + "." + key, value.stringMap.get(key)));
					}
					break;
				case INTMAP:
					for (Integer key : value.intMap.keySet()) {
						indexes.addAll(writeData(names, types, name + "." + key, value.intMap.get(key)));
					}
					break;
				case ARRAY:
					for (int i = 0; i < value.array.size(); i++) {
						indexes.addAll(writeData(names, types, name + "." + String.valueOf(i), value.array.get(i)));
					}
					break;
				case BOOLEAN:
					names.add(name);
					types.add("boolean");
					indexes.add(names.size());
					break;
				case FLOAT:
					names.add(name);
					types.add("float");
					indexes.add(names.size());
					break;
				case LONG:
					names.add(name);
					types.add("long");
					indexes.add(names.size());
					break;
				case DOUBLE:
					names.add(name);
					types.add("double");
					indexes.add(names.size());
					break;
				case INTEGER:
					names.add(name);
					types.add("int");
					indexes.add(names.size());
					break;
				default:
					names.add(name);
					types.add("string");
					indexes.add(names.size());
					break;
			}
			return indexes;
//			if (value instanceof MapVertexProperty) {
//				MapVertexProperty map = (MapVertexProperty) value;
//				for (String key : map.keySet()) {
//					indexes.addAll(writeData(names, types, name + "." + key, map.get(key)));
//				}
//			} else if (value instanceof ArrayVertexProperty) {
//				ArrayVertexProperty list = (ArrayVertexProperty) value;
//				for (int i = 0; i < list.size(); i++) {
//					indexes.addAll(writeData(names, types, name + "." + String.valueOf(i), list.get(i)));
//				}
//			} else if (value instanceof IntegerVertexProperty) {
//				names.add(name);
//				types.add("int");
//				indexes.add(names.size());
//			} else if (value instanceof FloatVertexProperty) {
//				names.add(name);
//				types.add("float");
//				indexes.add(names.size());
//			} else if (value instanceof DoubleVertexProperty) {
//				names.add(name);
//				types.add("double");
//				indexes.add(names.size());
//			} else if (value instanceof BooleanVertexProperty) {
//				names.add(name);
//				types.add("boolean");
//				indexes.add(names.size());
//			} else {
//				names.add(name);
//				types.add("string");
//				indexes.add(names.size());
//			}
//			return indexes;
		} else {
			return List.of(names.indexOf(name));
		}
	}
}
