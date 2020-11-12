/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Set;

import lombok.Builder.Default;
import lombok.ToString;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rjordao
 *
 */
@lombok.Builder
@ToString
public class Vertex {
	
	public String identifier;
	public FType type;
	@Default public Set<Port> ports = new HashSet<Port>();
	@Default public Map<String, Object> properties = new HashMap<String, Object>();

	@Override
	public int hashCode() {
		return Objects.hash(identifier);
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
		return Objects.equals(identifier, other.identifier);
	}
	

}
