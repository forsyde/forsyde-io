/**
 * 
 */
package forsyde.io.java.core;

import java.util.*;
import java.util.stream.Collectors;

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
final public class EdgeInfo {

	public String sourceId;
	public String targetId;
	public Optional<String> sourcePort;
	public Optional<String> targetPort;
	public Set<Trait> edgeTraits = new HashSet<Trait>();

	/**
	 * Utility constructor wrapping the source and target vertex into
	 * their identifiers.
	 *
	 * @param target Target Vertex for this edge.
	 * @param source Source vertex for this edge.
	 */
	public EdgeInfo(Vertex source, Vertex target) {
		this.targetId = target.getIdentifier();
		this.sourceId = source.getIdentifier();
		this.sourcePort = Optional.empty();
		this.targetPort = Optional.empty();
	}


	/**
	 * Utility constructor wrapping the source and target ports into empty optionals
	 * for constructor.
	 *
	 * @param targetId Target Vertex for this edge.
	 * @param sourceId Source vertex for this edge.
	 */
	public EdgeInfo(String sourceId, String targetId) {
		this.targetId = targetId;
		this.sourceId = sourceId;
		this.sourcePort = Optional.empty();
		this.targetPort = Optional.empty();
	}

	/**
	 * Utility constructor wrapping the source and target ports into optionals for
	 * constructor.
	 *
	 * @param targetId     Target Vertex for this edge.
	 * @param sourceId     Source vertex for this edge.
	 * @param targetPort target vertex port for this edge.
	 * @param sourcePort source vertex port for this edge.
	 */
	public EdgeInfo(String sourceId, String targetId, String sourcePort, String targetPort) {
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.targetPort = targetPort == null ? Optional.empty() : Optional.ofNullable(targetPort);
		this.sourcePort = sourcePort == null ? Optional.empty() : Optional.ofNullable(sourcePort);
	}

	/**
	 * Complete constructor for the {@link EdgeInfo} class, passing the optional ports
	 * that it might contain as well as the references for the source and target
	 * vertexes.
	 *
	 * @param targetId     Target Vertex for this edge.
	 * @param sourceId     Source vertex for this edge.
	 * @param targetPort {@link Optional} target vertex port for this edge.
	 * @param sourcePort {@link Optional} source vertex port for this edge.
	 */
	public EdgeInfo(String sourceId, String targetId, Optional<String> sourcePort, Optional<String> targetPort) {
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.sourcePort = sourcePort;
		this.targetPort = targetPort;
	}

	public Set<Trait> getTraits() {
		return edgeTraits;
	}

	public boolean hasTrait(EdgeTrait trait) {
		return edgeTraits.stream().anyMatch(t -> t.refines(trait));
	}

	@Override
	public int hashCode() {
		return Objects.hash(targetId, targetPort, sourceId, sourcePort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EdgeInfo)) {
			return false;
		}
		EdgeInfo other = (EdgeInfo) obj;
		return Objects.equals(targetId, other.targetId) && Objects.equals(targetPort, other.targetPort)
				&& Objects.equals(sourceId, other.sourceId) && Objects.equals(sourcePort, other.sourcePort);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge [traits=").append(edgeTraits).append(", source=").append(sourceId).append(", target=").append(targetId)
				.append(", sourcePort=").append(sourcePort).append(", targetPort=").append(targetPort).append("]");
		return builder.toString();
	}

	public String toIDString() {
		StringBuilder builder = new StringBuilder();
		builder.append(sourceId);
		sourcePort.ifPresent(s -> builder.append(".").append(s));
		builder.append("[").append(
				edgeTraits.stream().map(Trait::getName).collect(Collectors.joining(";"))
		).append("]");
		targetPort.ifPresent(s -> builder.append(".").append(s));
		builder.append(targetId);
		return builder.toString();
	}

	public String getSource() {
		return sourceId;
	}

	public String getTarget() {
		return targetId;
	}

	public Optional<String> getSourcePort() {
		return sourcePort;
	}

	public Optional<String> getTargetPort() {
		return targetPort;
	}

	public void addTraits(Trait... traits) {
		edgeTraits.addAll(Arrays.asList(traits.clone()));
	}

}
