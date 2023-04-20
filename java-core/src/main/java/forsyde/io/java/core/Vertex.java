/**
 * 
 */
package forsyde.io.java.core;

import forsyde.io.java.core.properties.*;
import forsyde.io.java.typed.viewers.typing.datatypes.Integer;

import java.util.*;
import java.util.stream.Collectors;

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
final public class Vertex {

	private volatile static long genSymSuffix = 0L;

	final public String identifier;
	final private Set<String> ports = new HashSet<>();
	final private Map<String, Object> properties = new HashMap<>();
	final private Set<Trait> vertexTraits = new HashSet<>();

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
	 * @param identifier the obligatory unique ID for this vertex.
	 */
	public Vertex(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Utility constructor initializing all associated data as empty.
	 *
	 * @param identifier the obligatory unique ID for this vertex.
	 * @param traits all initial traits for this vertex
	 */
	public Vertex(String identifier, Trait... traits) {
		this.identifier = identifier;
		addTraits(traits);
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
	public Vertex(String identifier, Set<String> ports, Map<String, Object> properties) {
		this.identifier = identifier;
		this.ports.addAll(ports);
		this.properties.replaceAll(properties::getOrDefault);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("");
		sb.append(identifier);
		sb.append("[").append(
				vertexTraits.stream().map(Trait::getName).collect(Collectors.joining("; "))
		).append("]");
		sb.append("(").append(
				String.join(", ", ports)
		).append(")");
		sb.append("{").append(
				properties.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue().toString())
						.collect(Collectors.joining(", "))
		).append("}");
		return sb.toString();
	}

	public Set<Trait> getTraits() {
		return vertexTraits;
	}

	public Boolean hasTrait(Trait trait) {
		return vertexTraits.stream().anyMatch(t -> t.refines(trait));
	}
	
	public Boolean hasTrait(String traitName) {
		try {
			VertexTrait trait = VertexTrait.valueOf(traitName);
			return hasTrait(trait);
		} catch (IllegalArgumentException e) {
			return vertexTraits.stream().map(Trait::getName).anyMatch(t -> t.equals(traitName));
		}
	}

	public Object getProperty(String propertyName) throws NullPointerException {
		return properties.get(propertyName);
	}

	public boolean putProperty(String propertyName, Object propertyValue) throws IllegalArgumentException {
		if (
				(propertyValue instanceof Integer) ||
						(propertyValue instanceof Long) ||
						(propertyValue instanceof String) ||
						(propertyValue instanceof Float) ||
						(propertyValue instanceof Double) ||
						(propertyValue instanceof Map<?, ?>) ||
						(propertyValue instanceof List<?>) ||
						(propertyValue instanceof Boolean)
		) {
			return properties.put(propertyName, propertyValue) == null;
		} else {
			throw new IllegalArgumentException("Only allowed vertex properties types are (boxed) numeric values, strings, lists and maps.");
		}
	}

	public void addPort(String s) {
		ports.add(s);
	}
	public void addPorts(String... strings) {
		ports.addAll(Arrays.asList(strings));
	}

	public void addPorts(Collection<String> strings) {
		ports.addAll(strings);
	}

	public boolean hasPort(String p) {
		return ports.contains(p);
	}

	public boolean hasPorts(Collection<String> ps) {
		return ports.containsAll(ps);
	}

	public boolean hasPorts(String... ps) {
		return ports.containsAll(Arrays.asList(ps));
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

	public boolean mergeInPlace(Vertex other) {
		boolean mergeDefined = true;
		if (!Objects.equals(identifier, other.getIdentifier()))
			return false;
		ports.addAll(other.getPorts());
		vertexTraits.addAll(other.getTraits());
		for (String key : other.getProperties().keySet()) {
			if (properties.containsKey(key)) {
				mergeDefined = mergeDefined && mergeInPlace(properties.get(key), other.getProperties().get(key));
			} else {
				properties.put(key, other.getProperties().get(key));
			}
		}
		return mergeDefined;
	}

	protected boolean mergeInPlace(Object main, Object other) {
		if (main instanceof List<?> && other instanceof List<?>) {
			final Iterator<Object> otherIt = ((List<Object>) other).iterator();
			final Iterator<Object> thisIt = ((List<Object>) main).iterator();
			while (otherIt.hasNext() && thisIt.hasNext()) {
				// until the sizes overlap, the elements should be mergeable
				if (!mergeInPlace(thisIt.next(), otherIt.next()))
					return false;
			}
			// if other is bigger than this
			otherIt.forEachRemaining(((List<Object>) main)::add);
			return true;
		}
		if (main instanceof Map<?, ?> && other instanceof Map<?, ?>) {
			// check if the keys are integers or not
			if (((Map<Object, ?>) main).keySet().stream().anyMatch(i -> i instanceof Integer)) {
				// merge keys that overlap and just insert keys that don't
				final Map<Integer, Object> m = ((Map<Integer, Object>) main);
				final Map<Integer, Object> mOther = ((Map<Integer, Object>) other);
				for (Integer key : mOther.keySet()) {
					if (m.containsKey(key)) {
						if(!mergeInPlace(m.get(key), mOther.get(key)))
							return false;
					} else {
						m.put(key, mOther.get(key));
					}
				}
				return true;
			} else if (((Map<Object, ?>) main).keySet().stream().anyMatch(i -> i instanceof String)) {
				final Map<String, Object> m = ((Map<String, Object>) main);
				final Map<String, Object> mOther = ((Map<String, Object>) other);
				for (String key : mOther.keySet()) {
					if (m.containsKey(key)) {
						if(!mergeInPlace(m.get(key), mOther.get(key)))
							return false;
					} else {
						m.put(key, mOther.get(key));
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return main.equals(other);
		}
	}

	public Optional<Vertex> merge(Vertex other) {
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

	public Map<String, Object> getProperties() {
		return properties;
	}

	public Set<String> getPorts() {
		return ports;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void addTraits(Collection<Trait> col) {
		vertexTraits.addAll(col);
	}

	public void addTraits(Trait... traits) {
		vertexTraits.addAll(Arrays.asList(traits.clone()));
	}

	public void addTraits(String... traitNames) {
		vertexTraits.addAll(Arrays.stream(traitNames).map(VertexTrait::fromName).collect(Collectors.toList()));
	}

}
