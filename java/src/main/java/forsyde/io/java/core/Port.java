/**
 * 
 */
package forsyde.io.java.core;

import javax.annotation.processing.Generated;


/**
 * @author rjordao
 *
 * Port of a vertex.
 * 
 * This class is intended to help synthesis of components and also
 * to keep things semantically sane when dealing with the model, for instance,
 * to denote which slot of a time-division a piece of code is executed or
 * to denote which input argument of a function is to be used.
 * 
 * @see Vertex
 * @see Edge
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
