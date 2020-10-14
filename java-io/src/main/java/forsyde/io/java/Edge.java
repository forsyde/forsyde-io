/**
 * 
 */
package forsyde.io.java;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.processing.Generated;

/**
 * @author rjordao
 *
 */
public class Edge {

	public FType type;
	public String targetNodeId;
	public String sourceNodeId;
	public Optional<String> targetNodePortId;
	public Optional<String> sourceNodePortId;


	@Generated("SparkTools")
	private Edge(Builder builder) {
		this.type = builder.type;
		this.targetNodeId = builder.targetNodeId;
		this.sourceNodeId = builder.sourceNodeId;
		this.targetNodePortId = builder.targetNodePortId;
		this.sourceNodePortId = builder.sourceNodePortId;
	}

	
	public Edge() {
	}

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

	/**
	 * Creates builder to build {@link Edge}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Edge}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private FType type;
		private String targetNodeId;
		private String sourceNodeId;
		private Optional<String> targetNodePortId = Optional.empty();
		private Optional<String> sourceNodePortId = Optional.empty();

		private Builder() {
		}

		public Builder withType(FType type) {
			this.type = type;
			return this;
		}

		public Builder withTargetNodeId(String targetNodeId) {
			this.targetNodeId = targetNodeId;
			return this;
		}

		public Builder withSourceNodeId(String sourceNodeId) {
			this.sourceNodeId = sourceNodeId;
			return this;
		}

		public Builder withTargetNodePortId(Optional<String> targetNodePortId) {
			this.targetNodePortId = targetNodePortId;
			return this;
		}

		public Builder withSourceNodePortId(Optional<String> sourceNodePortId) {
			this.sourceNodePortId = sourceNodePortId;
			return this;
		}

		public Edge build() {
			return new Edge(this);
		}
	}
	
		
	
}
