/**
 * 
 */
package forsyde.io.java.core;

import javax.annotation.processing.Generated;

import lombok.Builder;
import lombok.ToString;

/**
 * @author rjordao
 *
 */
public class Port {

	public String identifier;
	
	/**
	 * @param identifier
	 */
	public Port(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Port [identifier=").append(identifier).append(", type=").append("]");
		return builder.toString();
	}
	
}
