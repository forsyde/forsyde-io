/**
 * 
 */
package forsyde.io.java;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author rjordao
 *
 */
public class ForSyDeModelWriter {
	
	public void write(ForSyDeModel model, String fileName) throws IOException {
		write(model, 
				Files.newBufferedWriter(Paths.get(fileName))
				);
	}

	public void write(ForSyDeModel model, BufferedWriter writer) throws IOException {
		
		for(Vertex v : model.vertexSet()) {
			String interpolated = "vertex('" 
					+ v.identifier 
					+ "', '" 
					+ v.type.getName() 
					+ "').\n"; 
			writer.append(interpolated);
		}
		for(Vertex v : model.vertexSet()) {
			for(Port p : v.ports) {
				String interpolated = "port('" 
						+ p.identifier 
						+ "', '" 
						+ v.identifier 
						+ "', '"
						+ p.type.getName()
						+ "').\n";
				writer.append(interpolated);	
			}
		}
		for(Edge e : model.edgeSet()) {
			String interpolated = "edge('" 
					+ e.outNodeId
					+ "', '" 
					+ e.inNodeId
					+ "', '"
					+ e.outNodePortId.orElse("None")
					+ "', '"
					+ e.inNodePortId.orElse("None")
					+ "', '"
					+ e.type.getName()
					+ "').\n";
			writer.append(interpolated);
		}
	}
	
}
