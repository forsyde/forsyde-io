/**
 * 
 */
package forsyde.io.java.core;

import java.util.Set;

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

	public boolean mergeInPlace(ForSyDeModel other) {
		boolean mergeDefined = true;
		// do the double for since we would need to
		// find the conflicting vertex anyhow. Indexing/hashing
		// approaches can speed this up in the future.
		for (Vertex v: other.vertexSet()) {
			boolean present = false;
			for (Vertex thisV : vertexSet()) {
				// found a match
				if (v.identifier == thisV.identifier) {
					mergeDefined = mergeDefined && thisV.mergeInPlace(v);
					present = true;
					break;
				}
			}
			if (!present) {
				addVertex(v);
			}
			
		}
		// this is OK to be done since "contains" checks for equality
		for (Edge e : other.edgeSet()) {
			boolean present = false;
			for (Edge thisE : edgeSet()) {
				// found a match
				if (e.equals(thisE)) {
					thisE.edgeTraits.addAll(e.edgeTraits);
				}
			}
			if (!present) {
				Vertex source = vertexSet().stream().filter(v -> v.equals(e.source)).findAny().get();
				Vertex target = vertexSet().stream().filter(v -> v.equals(e.target)).findAny().get();
				addEdge(source, target, e);
			}
		}
		return mergeDefined;
	}

}
