/**
 * 
 */
package forsyde.io.java.core;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rjordao
 *
 * Class holding data for a Vertex (Node) in memory.
 * 
 * Every vertex contains a number of {@link Port}s (which are repeated in the
 * vertexed to increase reliability in the model, since putting
 * them in edges would have been sufficient) with their associated types.
 * Also, every vertex contains "Properties" which are arbitrary self-contained associated data,
 * such as the size of bits in a Signal or the time slots in a Time Division
 * Multiplexer.
 * 
 * 
 */
public class Vertex {

	public String identifier;
	public Set<Port> ports = new HashSet<Port>();
	public Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * @param identifier
	 */
	public Vertex(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @param identifier
	 * @param ports
	 * @param properties
	 */
	public Vertex(String identifier, Set<Port> ports, Map<String, Object> properties) {
		this.identifier = identifier;
		this.ports = ports;
		this.properties = properties;
	}

	@Override
	public String toString() {
		final int maxLen = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("Vertex [identifier=").append(identifier).append(", type=").append(", ports=")
				.append(ports != null ? toString(ports, maxLen) : null).append(", properties=")
				.append(properties != null ? toString(properties.entrySet(), maxLen) : null).append("]");
		return builder.toString();
	}

	public String getTypeName() {
		return "Vertex";
	}

	public Optional<Port> getPort(String portName) {
		return ports.stream().filter((p) -> p.identifier.equals(portName)).findFirst();
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

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
