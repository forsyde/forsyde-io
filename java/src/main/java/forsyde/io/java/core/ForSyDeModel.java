/**
 * 
 */
package forsyde.io.java.core;

import org.jgrapht.graph.DirectedPseudograph;

import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.drivers.ForSyDeModelHandler;

import java.util.Arrays;
import java.util.Optional;

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
		// find the conflicting vertexInterface anyhow. Indexing/hashing
		// approaches can speed this up in the future.
		for (Vertex v : other.vertexSet()) {
			boolean present = false;
			for (Vertex thisV : vertexSet()) {
				// found a match
				if (v.getIdentifier() == thisV.getIdentifier()) {
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
					thisE.getTraits().addAll(e.getTraits());
				}
			}
			if (!present) {
				Vertex source = vertexSet().stream().filter(v -> v.equals(e.getSource())).findAny().get();
				Vertex target = vertexSet().stream().filter(v -> v.equals(e.getTarget())).findAny().get();
				addEdge(source, target, e);
			}
		}
		return mergeDefined;
	}

	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model.
	 *
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @return True if the addition succeeded. False otherwise.
	 */
	public boolean connect(Vertex src, Vertex dst, EdgeTrait... traits) {
		Edge e = new Edge(src, dst);
		e.edgeTraits.addAll(Arrays.asList(traits.clone()));
		return addEdge(src, dst, e);
	}

	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model. Also takes care of the ports in
	 * the source vertex.
	 *
	 * @param portSrc Non empty string naming the ports at the source vertex to connect.
	 *
	 * @see #connect(Vertex, Vertex, EdgeTrait...) 
	 */
	public boolean connect(Vertex src, Vertex dst, String portSrc, EdgeTrait... traits) {
		if (portSrc != null && !portSrc.isEmpty() && src.ports.contains(portSrc)) {
			Edge e = new Edge(src, dst, Optional.of(portSrc), Optional.empty());
			e.edgeTraits.addAll(Arrays.asList(traits.clone()));
			return addEdge(src, dst, e);
		} else {
			return false;
		}
	}

	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model. Also takes care of the ports in
	 * the source and destination vertex. The source port string may be null in case
	 * only the destination port is to be used.
	 *
	 * @param portDst Non empty string naming the ports at the destination vertex to connect.
	 *
	 * @see #connect(Vertex, Vertex, String, EdgeTrait...)
	 */
	public boolean connect(Vertex src, Vertex dst, String portSrc, String portDst, EdgeTrait... traits) {
		// portDst must not be null and 'if' portSrc is not null, it must be in src's ports
		if (portDst != null && !portDst.isEmpty() && dst.ports.contains(portDst) && (portSrc == null || portSrc.isEmpty() || src.ports.contains(portSrc))) {
			Edge e = new Edge(src, dst, portSrc != null && !portSrc.isEmpty() ? Optional.of(portSrc) : Optional.empty(), Optional.of(portDst));
			e.edgeTraits.addAll(Arrays.asList(traits.clone()));
			return addEdge(src, dst, e);
		} else {
			return false;
		}
	}

	public Optional<Vertex> queryVertex(String vertexId) {
		return vertexSet().stream().filter(v -> v.identifier.equals(vertexId)).findAny();
	}

}
