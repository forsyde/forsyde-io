/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.Generated;

import java.util.Collections;
import java.util.HashSet;

/**
 * @author rjordao
 *
 */
public class Vertex {
	
	public String identifier;
	public FType type;
	public Set<Port> ports;

	@Generated("SparkTools")
	private Vertex(Builder builder) {
		this.identifier = builder.identifier;
		this.type = builder.type;
		this.ports = builder.ports;
	}
	
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

	/**
	 * Creates builder to build {@link Vertex}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Vertex}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String identifier;
		private FType type;
		private Set<Port> ports = new HashSet<Port>();

		private Builder() {
		}

		public Builder withIdentifier(String identifier) {
			this.identifier = identifier;
			return this;
		}

		public Builder withType(FType type) {
			this.type = type;
			return this;
		}

		public Builder withPorts(Set<Port> ports) {
			this.ports = ports;
			return this;
		}

		public Vertex build() {
			return new Vertex(this);
		}
	}
	
	
	

}
