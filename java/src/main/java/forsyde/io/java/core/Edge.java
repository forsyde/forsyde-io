/**
 * 
 */
package forsyde.io.java.core;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.processing.Generated;


/**
 * @author rjordao
 *
 * Class containing all information for an Edge.
 * 
 * The edge contains references to the source and target {@link Vertex}es
 * as well as the {@link Port}s being connect on both ends, in case
 * they exist. The edges also have types associated with them
 * so that extra deductions can be made along the EDA flow.
 */
public class Edge {

	public Vertex source;
	public Vertex target;
	public Optional<Port> sourcePort;
	public Optional<Port> targetPort;
	
	/**
	 * @param type
	 * @param target
	 * @param source
	 */
	public Edge(Vertex target, Vertex source) {
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
	public Edge(Vertex source, Vertex target, Port sourcePort, Port targetPort) {
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
	public Edge(Vertex source, Vertex target, Optional<Port> sourcePort, Optional<Port> targetPort) {
		this.target = target;
		this.source = source;
		this.targetPort = targetPort;
		this.sourcePort = sourcePort;
	}
	
	public String getTypeName() {
		return "Edge";
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

}
