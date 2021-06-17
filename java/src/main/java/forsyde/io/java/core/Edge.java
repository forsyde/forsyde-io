/**
 * 
 */
package forsyde.io.java.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author rjordao
 *
 *         Class containing all information for an Edge.
 * 
 *         The edge contains references to the source and target
 *         {@link Vertex}es as well as the {@link Port}s being connect on both
 *         ends, in case they exist. The edges also have types associated with
 *         them so that extra deductions can be made along the EDA flow.
 */
public class Edge implements EdgeInterface {

	public VertexInterface source;
	public VertexInterface target;
	public Optional<String> sourcePort;
	public Optional<String> targetPort;
	public Set<Trait> edgeTraits = new HashSet<Trait>();

	/**
	 * Utility constructor wrapping the source and target ports into empty optionals
	 * for constructor {@link #Edge(Vertex, Vertex, Optional, Optional)}.
	 * 
	 * @param target Target Vertex for this edge.
	 * @param source Source vertex for this edge.
	 * @see #Edge(Vertex, Vertex, Optional, Optional)
	 */
	public Edge(VertexInterface target, VertexInterface source) {
		this.target = target;
		this.source = source;
		this.sourcePort = Optional.empty();
		this.targetPort = Optional.empty();
	}

	/**
	 * Utility constructor wrapping the source and target ports into optionals for
	 * constructor {@link #Edge(Vertex, Vertex, Optional, Optional)}.
	 * 
	 * @param target     Target Vertex for this edge.
	 * @param source     Source vertex for this edge.
	 * @param targetPort target vertex port for this edge.
	 * @param sourcePort source vertex port for this edge.
	 * @see #Edge(Vertex, Vertex, Optional, Optional)
	 */
	public Edge(VertexInterface source, VertexInterface target, String sourcePort, String targetPort) {
		this.target = target;
		this.source = source;
		this.targetPort = Optional.of(targetPort);
		this.sourcePort = Optional.of(sourcePort);
	}

	/**
	 * Complete constructor for the {@link Edge} class, passing the optional ports
	 * that it might contain as well as the references for the source and target
	 * vertexes.
	 * 
	 * @param target     Target Vertex for this edge.
	 * @param source     Source vertex for this edge.
	 * @param targetPort {@link Optional} target vertex port for this edge.
	 * @param sourcePort {@link Optional} source vertex port for this edge.
	 */
	public Edge(VertexInterface source, VertexInterface target, Optional<String> sourcePort,
			Optional<String> targetPort) {
		this.target = target;
		this.source = source;
		this.targetPort = targetPort;
		this.sourcePort = sourcePort;
	}

	public Set<Trait> getTraits() {
		return edgeTraits;
	}

	public boolean hasTrait(EdgeTrait trait) {
		return edgeTraits.stream().anyMatch(t -> t.refines(trait));
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, targetPort, source, sourcePort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge other = (Edge) obj;
		return Objects.equals(target, other.target) && Objects.equals(targetPort, other.targetPort)
				&& Objects.equals(source, other.source) && Objects.equals(sourcePort, other.sourcePort);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge [type=").append(", source=").append(source).append(", target=").append(target)
				.append(", sourcePort=").append(sourcePort).append(", targetPort=").append(targetPort).append("]");
		return builder.toString();
	}

	@Override
	public VertexInterface getSource() {
		return source;
	}

	@Override
	public VertexInterface getTarget() {
		return target;
	}

	@Override
	public Optional<String> getSourcePort() {
		return sourcePort;
	}

	@Override
	public Optional<String> getTargetPort() {
		return targetPort;
	}

	@Override
	public void addTrait(Trait t) {
		edgeTraits.add(t);
	}

}
