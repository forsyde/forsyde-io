/**
 *
 */
package forsyde.io.java.core;

import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (portSrc != null &&  src.hasPort(portSrc)) {
            // check if some connection already exists
            final Set<EdgeInfo> present = Optional.ofNullable(getAllEdges(src, dst))
                    .map(set ->
                            set.stream()
                                    .filter(edgeInfo ->
                                            edgeInfo.getSourcePort().equals(Optional.of(portSrc)))
                                    .collect(Collectors.toSet()))
					.orElse(Set.of());
            if (present.isEmpty()) {
                EdgeInfo e = new EdgeInfo(src.getIdentifier(), dst.getIdentifier(), portSrc, null);
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
        if (portDst != null && dst.hasPort(portDst)) {
            // check if some connection already exists
            final Set<EdgeInfo> present = getAllEdges(src, dst).stream()
                    .filter(edgeInfo ->
                            edgeInfo.getSourcePort().equals(Optional.ofNullable(portSrc)) &&
                                    edgeInfo.getTargetPort().equals(Optional.of(portDst)))
                    .collect(Collectors.toSet());
            if (present.isEmpty()) {
                EdgeInfo e = new EdgeInfo(src.getIdentifier(), dst.getIdentifier(), portSrc, portDst);
                e.edgeTraits.addAll(Arrays.asList(traits.clone()));
                return addEdge(src, dst, e);
            } else {
                present.forEach(e -> e.edgeTraits.addAll(Arrays.asList(traits.clone())));
                return true;
            }
        } else if (portSrc != null) {
            return connect(src, dst, portSrc, traits);
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

    public boolean connect(Vertex src, Vertex dst, String portSrc, String portDst, String... traitNames) {
        return connect(src, dst, portSrc, portDst,
                (EdgeTrait[]) Arrays.stream(traitNames).map(EdgeTrait::fromName).toArray());
    }

    public boolean connect(VertexViewer src, VertexViewer dst, String portSrc, String portDst, String... traitNames) {
        return connect(src.getViewedVertex(), dst.getViewedVertex(), portSrc, portDst,
                (EdgeTrait[]) Arrays.stream(traitNames).map(EdgeTrait::fromName).toArray());
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
            isConnected = isConnected && edges.stream().filter(e -> e.getSourcePort().isPresent())
                    .flatMap(e -> e.getSourcePort().stream())
                    .anyMatch(p -> p.equals(srcPort));
        }
        if (isConnected && dstPort != null && !dstPort.isEmpty()) {
            isConnected = isConnected && edges.stream().filter(e -> e.getTargetPort().isPresent())
                    .flatMap(e -> e.getTargetPort().stream())
                    .anyMatch(p -> p.equals(dstPort));
        }
        return isConnected;
    }

    public Set<EdgeInfo> getAllEdges(VertexViewer sourceVertex, VertexViewer targetVertex) {
        return super.getAllEdges(sourceVertex.getViewedVertex(), targetVertex.getViewedVertex());
    }

    public Optional<Vertex> queryVertex(String vertexId) {
        return vertexSet().stream().filter(v -> v.identifier.equals(vertexId)).findAny();
    }

    /**
     * This is convenience function that enables a vertex to be created (if it does not exist in the model)
     * and to be immediately returned for usage. The vertex is guaranteed to be part of the system graph
     * after this function is called.
     *
     * @param vertexId the identifier for the new or existing vertex.
     * @return a vertex with the ID specified. If the vertex exists in the model, the existing one is returned.
     */
    public Vertex newVertex(String vertexId) {
        return queryVertex(vertexId).orElseGet(() -> {
            final Vertex v = new Vertex(vertexId);
            addVertex(v);
            return v;
        });
    }

    @Override
    public String toString() {
        return "SystemGraph([" +
                vertexSet().stream().map(Vertex::toString).collect(Collectors.joining(", ")) + "; " +
                edgeSet().stream().map(EdgeInfo::toString).collect(Collectors.joining(", ")) +
                "])";
    }

    /**
     * This equality check does not go deep into the vertexes properties, but stops
     * at their identities
     * @param obj the other ForSyDeSystemGraph
     * @return true if the system graphs are equals except for the vertexes propertie and ports
     */
    public boolean shallowEquals(ForSyDeSystemGraph obj) {
        return vertexSet().equals(obj.vertexSet()) && edgeSet().equals(obj.edgeSet());
    }

    /**
     * Simple wrapper for vertex viewers.
     * @param vertex
     * @return
     */
    public Set<EdgeInfo> incomingEdgesOf(VertexViewer vertex) {
        return super.incomingEdgesOf(vertex.getViewedVertex());
    }

    /**
     * Simple wrapper for vertex viewers.
     * @param vertex
     * @return
     */
    public Set<EdgeInfo> outgoingEdgesOf(VertexViewer vertex) {
        return super.outgoingEdgesOf(vertex.getViewedVertex());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ForSyDeSystemGraph) {
            final ForSyDeSystemGraph osg = (ForSyDeSystemGraph) obj;
            return vertexSet().equals(osg.vertexSet()) && edgeSet().equals(osg.edgeSet());
        }
        return false;
    }
}
