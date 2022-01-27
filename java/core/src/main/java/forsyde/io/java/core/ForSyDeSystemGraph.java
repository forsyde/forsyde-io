/**
 * 
 */
package forsyde.io.java.core;

import org.jgrapht.graph.DirectedPseudograph;

import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.drivers.ForSyDeModelHandler;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class ForSyDeSystemGraph extends DirectedPseudograph<Vertex, EdgeInfo> {

	/**
	 * Default constructor, returning an empty (system) model graph.
	 */
	public ForSyDeSystemGraph() {
		super(EdgeInfo.class);

	}

	public boolean mergeInPlace(ForSyDeSystemGraph other) {
		boolean mergeDefined = true;
		// do the double for since we would need to
		// find the conflicting vertexInterface anyhow. Indexing/hashing
		// approaches can speed this up in the future.
		for (Vertex v : other.vertexSet()) {
			boolean present = false;
			for (Vertex thisV : vertexSet()) {
				// found a match
				if (Objects.equals(v.getIdentifier(), thisV.getIdentifier())) {
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
		for (EdgeInfo e : other.edgeSet()) {
			boolean present = false;
			for (EdgeInfo thisE : edgeSet()) {
				// found a match
				if (e.equals(thisE)) {
					thisE.getTraits().addAll(e.getTraits());
					present = true;
				}
			}
			if (!present) {
				Vertex source = vertexSet().stream().filter(v -> v.getIdentifier().equals(e.getSource())).findAny().get();
				Vertex target = vertexSet().stream().filter(v -> v.getIdentifier().equals(e.getTarget())).findAny().get();
				addEdge(source, target, e);
			}
		}
		return mergeDefined;
	}
	
	public ForSyDeSystemGraph merge(ForSyDeSystemGraph other) {
		ForSyDeSystemGraph merged = new ForSyDeSystemGraph();
		merged.mergeInPlace(this);
		merged.mergeInPlace(other);
		return merged;
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
		EdgeInfo e = new EdgeInfo(src.getIdentifier(), dst.getIdentifier());
		e.edgeTraits.addAll(Arrays.asList(traits.clone()));
		return addEdge(src, dst, e);
	}
	
	
	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model, via their viewers.
	 *
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @return True if the addition succeeded. False otherwise.
	 */
	public boolean connect(VertexViewer src, VertexViewer dst, EdgeTrait... traits) {
		return connect(src.getViewedVertex(), dst.getViewedVertex(), traits);
	}

	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model. Also takes care of the ports in
	 * the source vertex.
	 *
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @param portSrc Non empty string naming the ports at the source vertex to connect.
	 * @return True if the addition succeeded. False otherwise.
	 *
	 * @see #connect(Vertex, Vertex, EdgeTrait...) 
	 */
	public boolean connect(Vertex src, Vertex dst, String portSrc, EdgeTrait... traits) {
		if (portSrc != null && !portSrc.isEmpty() && src.ports.contains(portSrc)) {
			// check if some connection already exists
			final Set<EdgeInfo> present = getAllEdges(src, dst).stream()
					.filter(edgeInfo ->
							edgeInfo.getSourcePort().equals(Optional.of(portSrc)))
					.collect(Collectors.toSet());
			if (present.isEmpty()) {
				EdgeInfo e = new EdgeInfo(src.getIdentifier(), dst.getIdentifier(), portSrc != null && !portSrc.isEmpty() ? Optional.of(portSrc) : Optional.empty(), Optional.empty());
				e.edgeTraits.addAll(Arrays.asList(traits.clone()));
				return addEdge(src, dst, e);
			} else {
				present.forEach(e -> e.edgeTraits.addAll(Arrays.asList(traits.clone())));
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model, via their viewers. Also takes care of the ports in
	 * the source vertex.
	 *
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @param portSrc Non empty string naming the ports at the source vertex to connect.
	 * @return True if the addition succeeded. False otherwise.
	 *
	 * @see #connect(Vertex, Vertex, EdgeTrait...) 
	 */
	public boolean connect(VertexViewer src, VertexViewer dst, String portSrc, EdgeTrait... traits) {
		return connect(src.getViewedVertex(), dst.getViewedVertex(), portSrc, traits);
	}


	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model. Also takes care of the ports in
	 * the source and destination vertex. The source port string may be null in case
	 * only the destination port is to be used.
	 * 
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @param portSrc possibly empty or null string naming the ports at the source vertex to connect.
	 * @param portDst Non empty string naming the ports at the destination vertex to connect.
	 * @return True if the addition succeeded. False otherwise.
	 *
	 * @see #connect(Vertex, Vertex, String, EdgeTrait...)
	 */
	public boolean connect(Vertex src, Vertex dst, String portSrc, String portDst, EdgeTrait... traits) {
		// portDst must not be null and 'if' portSrc is not null, it must be in src's ports
		if (portDst != null && !portDst.isEmpty() && dst.ports.contains(portDst) && (portSrc == null || portSrc.isEmpty() || src.ports.contains(portSrc))) {
			// check if some connection already exists
			final Set<EdgeInfo> present = getAllEdges(src, dst).stream()
					.filter(edgeInfo ->
							edgeInfo.getSourcePort().equals(Optional.ofNullable(portSrc)) &&
							edgeInfo.getTargetPort().equals(Optional.of(portDst)))
					.collect(Collectors.toSet());
			if (present.isEmpty()) {
				EdgeInfo e = new EdgeInfo(src.getIdentifier(), dst.getIdentifier(), portSrc != null && !portSrc.isEmpty() ? Optional.of(portSrc) : Optional.empty(), Optional.of(portDst));
				e.edgeTraits.addAll(Arrays.asList(traits.clone()));
				return addEdge(src, dst, e);
			} else {
				present.forEach(e -> e.edgeTraits.addAll(Arrays.asList(traits.clone())));
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Convenience method to connect two vertexes directly without having to manually
	 * create the edge and add it to the model, via their viewers. Also takes care of the ports in
	 * the source and destination vertex. The source port string may be null in case
	 * only the destination port is to be used.
	 * 
	 * @param src Source vertex of the Edge
	 * @param dst Destination vertex of the Edge
	 * @param traits A list of traits that the new edge conforms to.
	 * @param portSrc possibly empty or null string naming the ports at the source vertex to connect.
	 * @param portDst Non empty string naming the ports at the destination vertex to connect.
	 * @return True if the addition succeeded. False otherwise.
	 *
	 * @see #connect(Vertex, Vertex, String, EdgeTrait...)
	 */
	public boolean connect(VertexViewer src, VertexViewer dst, String portSrc, String portDst, EdgeTrait... traits) {
		return connect(src.getViewedVertex(), dst.getViewedVertex(), portSrc, portDst, traits);
	}

	/**
	 * Convenience method to check existence of an edge connecting two vertexes,
	 * through the viewer that are wrapping them. This method ignores ports and
	 * returns true if both port-to-port and vertex-to-vertex connections exist.
	 * 
	 * @param src The viewer for the source vertex.
	 * @param dst The viewer for the target vertex.
	 * @return whether the edge exists or not (with or without ports)
	 */
	public boolean hasConnection(VertexViewer src, VertexViewer dst) {
		return containsEdge(src.getViewedVertex(), dst.getViewedVertex());
	}

	/**
	 * Convenience method to check existence of an edge connecting two vertexes,
	 * through the viewer that are wrapping them. This method ignores ports and
	 * returns true if both port-to-port and vertex-to-vertex connections exist
	 * filtering by edge traits.
	 *
	 * @param src The viewer for the source vertex.
	 * @param dst The viewer for the target vertex.
	 * @param traits the required traits.
	 * @return whether the edge exists or not (with or without ports)
	 */
	public boolean hasConnection(VertexViewer src, VertexViewer dst, EdgeTrait... traits) {
		final Set<EdgeInfo> edgeInfoSet = getAllEdges(src.getViewedVertex(), dst.getViewedVertex());
		for (EdgeTrait trait : traits) {
			if (edgeInfoSet.stream().noneMatch(e -> e.hasTrait(trait)))
				return false;
		}
		return true;
	}
	
	/**
	 * Convenience method to check existence of an edge connecting two vertexes,
	 * through the viewer that are wrapping them. This method considers ports and
	 * returns true if the port-to-port connection exist. The ports can also be
	 * null or empty to symbolize the edge connects to the vertex directly.
	 * 
	 * @param src The viewer for the source vertex. 
	 * @param dst The viewer for the target vertex.
	 * @param srcPort The optional port for the source vertex.
	 * @param dstPort the optional port for the target vertex.
	 * @return whether the edge exists or not (with specified ports)
	 */
	public boolean hasConnection(VertexViewer src, VertexViewer dst, String srcPort, String dstPort) {
		Set<EdgeInfo> edges = getAllEdges(src.getViewedVertex(), dst.getViewedVertex());
		Boolean isConnected = edges.size() > 0;
		if (isConnected && srcPort != null && !srcPort.isEmpty()) {
			isConnected = isConnected && edges.stream().filter(e -> e.sourcePort.isPresent())
				.flatMap(e -> e.sourcePort.stream())
				.anyMatch(p -> p.equals(srcPort));
		}
		if (isConnected && dstPort != null && !dstPort.isEmpty()) {
			isConnected = isConnected && edges.stream().filter(e -> e.targetPort.isPresent())
				.flatMap(e -> e.targetPort.stream())
				.anyMatch(p -> p.equals(dstPort));
		}
		return isConnected; 
	}
	
	public Optional<Vertex> queryVertex(String vertexId) {
		return vertexSet().stream().filter(v -> v.identifier.equals(vertexId)).findAny();
	}

}
