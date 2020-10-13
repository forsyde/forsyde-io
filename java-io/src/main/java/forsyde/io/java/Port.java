/**
 * 
 */
package forsyde.io.java;

import javax.annotation.processing.Generated;

/**
 * @author rjordao
 *
 */
public class Port {

	public String identifier;
	public FType type;
	
	@Generated("SparkTools")
	private Port(Builder builder) {
		this.identifier = builder.identifier;
		this.type = builder.type;
	}
	/**
	 * Creates builder to build {@link Port}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link Port}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String identifier;
		private FType type;

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

		public Port build() {
			return new Port(this);
		}
	}
	
	
}
