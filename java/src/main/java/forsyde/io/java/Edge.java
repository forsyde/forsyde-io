/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.processing.Generated;

import lombok.ToString;

/**
 * @author rjordao
 *
 */

public class Edge {

	public ModelType type;
	public Vertex source;
	public Vertex target;
	public Optional<Port> sourcePort;
	public Optional<Port> targetPort;
	
	/**
	 * @param type
	 * @param target
	 * @param source
	 */
	public Edge(ModelType type, Vertex target, Vertex source) {
		this.type = type;
		this.target = target;
		this.source = source;
		this.sourcePort = Optional.empty();
		this.targetPort = Optional.empty();
	}
	
	/**
	 * @param type
	 * @param target
	 * @param source
	 * @param targetPort
	 * @param sourcePort
	 */
	public Edge(ModelType type, Vertex source, Vertex target, Port sourcePort, Port targetPort) {
		this.type = type;
		this.target = target;
		this.source = source;
		this.targetPort = Optional.of(targetPort);
		this.sourcePort = Optional.of(sourcePort);
	}
	
	/**
	 * @param type
	 * @param target
	 * @param source
	 * @param targetPort
	 * @param sourcePort
	 */
	public Edge(ModelType type, Vertex source, Vertex target, Optional<Port> sourcePort, Optional<Port> targetPort) {
		this.type = type;
		this.target = target;
		this.source = source;
		this.targetPort = targetPort;
		this.sourcePort = sourcePort;
	}

	@Override
	public int hashCode() {
		return Objects.hash(target, targetPort, source, sourcePort, type);
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
				&& Objects.equals(source, other.source) && Objects.equals(sourcePort, other.sourcePort)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Edge [type=").append(type).append(", source=").append(source).append(", target=").append(target)
				.append(", sourcePort=").append(sourcePort).append(", targetPort=").append(targetPort).append("]");
		return builder.toString();
	}

}
