/**
 * 
 */
package forsyde.io.java;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author rjordao
 *
 */
public class ForSyDeModelPrologWriter {
	
//	public void write(ForSyDeModel model, String fileName) throws IOException {
//		write(model, 
//				Files.newBufferedWriter(Paths.get(fileName))
//				);
//	}
//
//	public void write(ForSyDeModel model, BufferedWriter writer) throws IOException {
//		
//		for(Vertex v : model.vertexSet()) {
//			String interpolated = "vertex('" 
//					+ v.identifier 
//					+ "', '" 
//					+ v.type.getName() 
//					+ "').\n"; 
//			writer.append(interpolated);
//		}
//		for(Vertex v : model.vertexSet()) {
//			for(Port p : v.ports) {
//				String interpolated = "port('" 
//						+ v.identifier 
//						+ "', '" 
//						+ p.identifier 
//						+ "', '"
//						+ p.type.getName()
//						+ "').\n";
//				writer.append(interpolated);	
//			}
//			
//			for(String propName : v.properties.keySet()) {
//				String interpolated = "prop('" 
//						+ v.identifier 
//						+ "', '" 
//						+ propName 
//						+ "', '"
//						+ v.properties.get(propName).toString()
//						+ "').\n";
//				writer.append(interpolated);
//			}
//		}
//		for(Edge e : model.edgeSet()) {
//			String interpolated = "edge('" 
//					+ e.sourceNodeId
//					+ "', '" 
//					+ e.targetNodeId
//					+ "', '"
//					+ e.sourceNodePortId.orElse("None")
//					+ "', '"
//					+ e.targetNodePortId.orElse("None")
//					+ "', '"
//					+ e.type.getName()
//					+ "').\n";
//			writer.append(interpolated);
//		}
//		writer.flush();
//		writer.close();
//	}

	
}
