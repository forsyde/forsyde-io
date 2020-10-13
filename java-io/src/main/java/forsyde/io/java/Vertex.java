/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Set;

/**
 * @author rjordao
 *
 */
public class Vertex {
	
	public String identifier;
	public FType type;
	public Set<Port> ports;
	
	public Vertex(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier, ports, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		return Objects.equals(identifier, other.identifier) && Objects.equals(ports, other.ports)
				&& Objects.equals(type, other.type);
	}
	
	

}
