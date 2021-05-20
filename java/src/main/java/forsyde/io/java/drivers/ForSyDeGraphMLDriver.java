package forsyde.io.java.drivers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Port;
import forsyde.io.java.core.Vertex;

public class ForSyDeGraphMLDriver extends ForSyDeModelDriver {

	@Override
	@Deprecated
	public ForSyDeModel loadModel(InputStream in) throws Exception {
		throw new Exception("GraphML reading is not supported.");
	}

	@Override
	public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
		List<String> vertexDataNames = new ArrayList<>();
		List<String> vertexDataTypes = new ArrayList<>();
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
			// vElem.setAttribute("traits", v.vertexTraits.stream().map(t -> t.getName()).reduce("", (s1, s2) -> s1 + ";" + s2));
			graph.appendChild(vElem);
			for (Port p : v.ports) {
				Element pElem = doc.createElement("port");
				pElem.setAttribute("name", p.identifier);
				vElem.appendChild(pElem);
			}
			for (String key : v.properties.keySet()) {
				for (Integer i : writeData(vertexDataNames, vertexDataTypes, key, v.properties.get(key))) {
					Element propElem = doc.createElement("data");
					propElem.setAttribute("key", "d" + String.valueOf(i));
					vElem.appendChild(propElem);
				}
			}
		}
		for (Edge e : model.edgeSet()) {
			Element eElem = doc.createElement("edge");
			eElem.setAttribute("source", e.source.identifier);
			eElem.setAttribute("target", e.target.identifier);
			// eElem.setAttribute("traits", e.edgeTraits.stream().map(t -> t.getName()).reduce("", (s1, s2) -> s1 + ";" + s2));
			if (e.sourcePort.isPresent()) {
				eElem.setAttribute("sourceport", e.sourcePort.get().identifier);
			}
			if (e.targetPort.isPresent()) {
				eElem.setAttribute("targetport", e.targetPort.get().identifier);
			}
			graph.appendChild(eElem);
		}
		for(int i = 0; i < vertexDataNames.size(); i++) {
			Element dataElem = doc.createElement("key");
			dataElem.setAttribute("for", "node");
			dataElem.setAttribute("id", "d" + String.valueOf(i));
			dataElem.setAttribute("attr.type", vertexDataTypes.get(i));
			dataElem.setAttribute("attr.name", vertexDataNames.get(i));
			root.appendChild(dataElem);
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(doc), new StreamResult(out));
	}
	
	static protected List<Integer> writeData(List<String> names, List<String> types, String name, Object value) {
		if (!names.contains(name)) {
			List<Integer> indexes = new ArrayList<>();
			if (value instanceof HashMap) {
				HashMap<String, Object> map = (HashMap<String, Object>) value;
				for (String key : map.keySet()) {
					indexes.addAll(writeData(names, types, name + "." + key, map.get(key)));
				}
			} else if (value instanceof ArrayList) {
				ArrayList<Object> list = (ArrayList<Object>) value;
				for (int i = 0; i < list.size(); i++) {
					indexes.addAll(writeData(names, types, name + "." + String.valueOf(i), list.get(i)));
				}
			} else if (value instanceof Integer) {
				names.add(name);
				types.add("int");
				indexes.add(names.size());
			} else if (value instanceof Float) {
				names.add(name);
				types.add("float");
				indexes.add(names.size());
			} else if (value instanceof Double) {
				names.add(name);
				types.add("double");
				indexes.add(names.size());
			} else if (value instanceof Boolean) {
				names.add(name);
				types.add("boolean");
				indexes.add(names.size());
			} else {
				names.add(name);
				types.add("string");
				indexes.add(names.size());
			}
			return indexes;
		}  else {
			return List.of(names.indexOf(name));
		}
	}
}
