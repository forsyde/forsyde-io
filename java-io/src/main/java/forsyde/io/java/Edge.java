/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Optional;

/**
 * @author rjordao
 *
 */
public class Edge {

	public FType type;
	public String inNodeId;
	public String outNodeId;
	public Optional<String> inNodePortId;
	public Optional<String> outNodePortId;
	
	public Edge() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(inNodeId, inNodePortId, outNodeId, outNodePortId, type);
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
		return Objects.equals(inNodeId, other.inNodeId) && Objects.equals(inNodePortId, other.inNodePortId)
				&& Objects.equals(outNodeId, other.outNodeId) && Objects.equals(outNodePortId, other.outNodePortId)
				&& Objects.equals(type, other.type);
	}
	
	
	
	
}
