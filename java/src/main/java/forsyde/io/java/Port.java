/**
 * 
 */
package forsyde.io.java;

import javax.annotation.processing.Generated;

import lombok.Builder;
import lombok.ToString;

/**
 * @author rjordao
 *
 */
public class Port {

	public String identifier;
	public ModelType type;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Port [identifier=").append(identifier).append(", type=").append(type).append("]");
		return builder.toString();
	}

	/**
	 * @param identifier
	 * @param type
	 */
	public Port(String identifier, ModelType type) {
		this.identifier = identifier;
		this.type = type;
	}
	
}
