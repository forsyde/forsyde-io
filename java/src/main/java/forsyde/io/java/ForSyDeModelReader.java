/**
 * 
 */
package forsyde.io.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import forsyde.io.java.types.TypesFactory;

/**
 * @author rjordao
 *
 */
public class ForSyDeModelReader {
	
	public ForSyDeModel read(String fileName) throws IOException {
		return read(Files.newBufferedReader(Paths.get(fileName)));
	}
	
	public ForSyDeModel read(BufferedReader reader) throws IOException {
		ForSyDeModel model = new ForSyDeModel();
		String line = reader.readLine();
		while(line != null) {
			if (line.matches("\\s*%")) continue;
			else if (line.contains("vertex")) addVertex(model, line);
			else if (line.contains("prop")) addProp(model, line);
			else if (line.contains("port")) addPort(model, line);
			else if (line.contains("edge")) addEdge(model, line);
			line = reader.readLine();
		}
		return model;
		
	}
	
	protected void addVertex(ForSyDeModel model, String line) {
		Pattern vertexPattern = Pattern.compile("vertex\\('(\\w+)', '(\\w+)'\\).");
		Matcher matcher = vertexPattern.matcher(line);
		matcher.find();
		FType type = TypesFactory.getTypeFromName(matcher.group(2)).orElseThrow();
		model.addVertex(
			Vertex.builder()
				.identifier(matcher.group(1))
				.type(type)
				.build()
		);
	}
	
	protected void addPort(ForSyDeModel model, String line) {
		Pattern portPattern = Pattern.compile("port\\('(\\w+)', '(\\w+)', '(\\w+)'\\).");
		Matcher matcher = portPattern.matcher(line);
		matcher.find();
		String vId = matcher.group(1);
		Vertex vertex = model.vertexSet()
				.stream()
				.filter((v) -> v.identifier.equals(vId))
				.findFirst()
				.orElseThrow();
		FType type = TypesFactory.getTypeFromName(matcher.group(3)).orElseThrow();
		vertex.ports.add(
				Port.builder()
					.identifier(matcher.group(2))
					.type(type)
					.build()
				);
	}
	
	protected void addProp(ForSyDeModel model, String line) {
		Pattern propPattern = Pattern.compile("prop\\('(\\w+)', '(\\w+)', '(\\w+)'\\).");
		Matcher matcher = propPattern.matcher(line);
		matcher.find();
		String vId = matcher.group(1);
		Vertex vertex = model.vertexSet()
				.stream()
				.filter((v) -> v.identifier.equals(vId))
				.findFirst()
				.orElseThrow();
		String objStr = matcher.group(3);
		Object obj;
		// try to convert things and fail back to string
		if (objStr.matches("-?\\d*\\.\\d+")) obj = Double.parseDouble(objStr);
		else if (objStr.matches("-\\d+")) obj = Integer.parseInt(objStr);
		else if (objStr.matches("\\d+")) obj = Integer.parseUnsignedInt(objStr);
		else obj = objStr;
		// add the result
		vertex.properties.put(
				matcher.group(2),
				obj
				);
	}
	
	protected void addEdge(ForSyDeModel model, String line) {
		Pattern edgePattern = Pattern.compile("edge\\('(\\w+)', '(\\w+)', '(\\w+)', '(\\w+)', '(\\w+)'\\).");
		Matcher matcher = edgePattern.matcher(line);
		matcher.find();
		String sId = matcher.group(1);
		String tId = matcher.group(2);
		String sPortId = matcher.group(3);
		String tPortId = matcher.group(4);
		FType type = TypesFactory.getTypeFromName(matcher.group(5)).orElseThrow();
		Vertex s = model.vertexSet()
				.stream()
				.filter((v) -> v.identifier.equals(sId))
				.findFirst()
				.orElseThrow();
		Vertex t = model.vertexSet()
				.stream()
				.filter((v) -> v.identifier.equals(tId))
				.findFirst()
				.orElseThrow();
		model.addEdge(s, t, Edge.builder()
					.sourceNodeId(sId)
					.targetNodeId(tId)
					.sourceNodePortId(Optional.ofNullable(sPortId))
					.targetNodePortId(Optional.ofNullable(tPortId))
					.type(type)
					.build()
				);
	}

}
 