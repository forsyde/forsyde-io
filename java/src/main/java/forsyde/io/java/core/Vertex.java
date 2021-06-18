/**
 * 
 */
package forsyde.io.java.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * 
 * Class holding data for a Vertex (Node) in memory.
 * 
 * Every vertex contains a number of {@link Port}s (which are repeated in the
 * vertexes to increase reliability in the model, since putting them in edges
 * would have been sufficient) with their associated types. Also, every vertex
 * contains "Properties" which are arbitrary self-contained associated data,
 * such as the size of bits in a Signal or the time slots in a Time Division
 * Multiplexer.
 * 
 * @author Rodolfo Jordao (jordao@kth.se)
 */
public class Vertex implements VertexInterface {

	private volatile static long genSymSuffix = 0L;

	public String identifier;
	public Set<String> ports = new HashSet<String>();
	public Map<String, VertexPropertyElement> properties = new HashMap<String, VertexPropertyElement>();
	public Set<Trait> vertexTraits = new HashSet<Trait>();

	/**
	 * Utility constructor initializing all associated data as empty and the vertex
	 * with a unique random identifier.
	 * 
	 */
	public Vertex() {
		genSymSuffix += 1L;
		this.identifier = "v" + String.valueOf(genSymSuffix);
	}

	/**
	 * Utility constructor initializing all associated data as empty.
	 * 
	 * @param identifier the obligatory unique ID for this vertex.q
	 */
	public Vertex(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Complete constructor, also initializing all the data one vertex may be
	 * associated with. In the case of Java, the association is given by object
	 * containment.
	 * 
	 * @param identifier The obligatory unique ID for this vertex.
	 * @param ports      The list (set-like, no duplicates) of ports for this
	 *                   vertex.
	 * @param properties The mapping (associative array) of properties for this
	 *                   vertex. Remember that it should be a tree of primitive
	 *                   types such as Integers, Floats, Strings etc.
	 */
	public Vertex(String identifier, Set<String> ports, Map<String, VertexPropertyElement> properties) {
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

	public Set<Trait> getTraits() {
		return vertexTraits;
	}

	public Boolean hasTrait(Trait trait) {
		return vertexTraits.stream().anyMatch(t -> t.refines(trait));
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

	public boolean mergeInPlace(VertexInterface other) {
		boolean mergeDefined = true;
		if (identifier != other.getIdentifier())
			return false;
		ports.addAll(other.getPorts());
		vertexTraits.addAll(other.getTraits());
		for (String key : other.getProperties().keySet()) {
			if (properties.containsKey(key)) {
				mergeDefined = mergeDefined && properties.get(key).mergeInPlace(other.getProperties().get(key));
			} else {
				properties.put(key, other.getProperties().get(key));
			}
		}
		return mergeDefined;
	}

	public Optional<Vertex> merge(VertexInterface other) {
		if (identifier != other.getIdentifier())
			return Optional.empty();
		else {
			Vertex merged = new Vertex(identifier);
			if (merged.mergeInPlace(this) && merged.mergeInPlace(other)) {
				return Optional.of(merged);
			} else {
				return Optional.empty();
			}
		}
	}

	@Override
	public Map<String, VertexPropertyElement> getProperties() {
		return properties;
	}

	@Override
	public Set<String> getPorts() {
		return ports;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public void addTrait(Trait t) {
		vertexTraits.add(t);
	}

}
