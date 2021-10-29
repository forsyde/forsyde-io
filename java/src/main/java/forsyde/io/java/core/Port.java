/**
 * 
 */
package forsyde.io.java.core;

/**
 * @author rjordao
 *
 *         Port of a vertex.
 * 
 *         This class is intended to help synthesis of components and also to
 *         keep things semantically sane when dealing with the model, for
 *         instance, to denote which slot of a time-division a piece of code is
 *         executed or to denote which input argument of a function is to be
 *         used.
 * 
 * @see Vertex
 * @see Edge
 */
@Deprecated
public class Port {

	public String identifier;

	/**
	 * Constructor, passing the unique identifier within it's containing vertex
	 * scope.
	 * 
	 * @param identifier the unique ID of this port within the vertex's port set.
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
