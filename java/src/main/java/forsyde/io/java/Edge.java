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
@lombok.Builder
@ToString
public class Edge {

	public FType type;
	public String targetNodeId;
	public String sourceNodeId;
	public Optional<String> targetNodePortId;
	public Optional<String> sourceNodePortId;

	@Override
	public int hashCode() {
		return Objects.hash(targetNodeId, targetNodePortId, sourceNodeId, sourceNodePortId, type);
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
		return Objects.equals(targetNodeId, other.targetNodeId) && Objects.equals(targetNodePortId, other.targetNodePortId)
				&& Objects.equals(sourceNodeId, other.sourceNodeId) && Objects.equals(sourceNodePortId, other.sourceNodePortId)
				&& Objects.equals(type, other.type);
	}
	
}
