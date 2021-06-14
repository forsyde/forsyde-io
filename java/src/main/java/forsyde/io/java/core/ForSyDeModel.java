/**
 * 
 */
package forsyde.io.java.core;

import org.jgrapht.graph.DirectedPseudograph;

import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.drivers.ForSyDeModelHandler;

/**
 * @author rjordao
 *
 *         The main graph holder element representing a ForSyDe Model in memory.
 *
 *         A subclass of DirectedPseudograph from the jgrapht library, this
 *         class holds the model (a graph model therefore) which can be used for
 *         any purpose in the ForSyDe design flow.
 * 
 *         to read and write this model, check subclasses of
 *         {@link ForSyDeModelDriver} or simply use the 'API' class
 *         {@link ForSyDeModelHandler} directly.
 * 
 * @see DirectedPseudograph
 * @see Vertex
 * @see Edge
 */
public class ForSyDeModel extends DirectedPseudograph<Vertex, Edge> {

	/**
	 * Default constructor, returning an empty (system) model graph.
	 */
	public ForSyDeModel() {
		super(Edge.class);

	}

	public void mergeInPlace(ForSyDeModel other) {
		for (Vertex v: other.vertexSet()) {
			if (!vertexSet().contains(v)) {
				addVertex(v);
			} else {
				
			}
		}
	}

}
