/**
 * 
 */
package forsyde.io.java;

import org.jgrapht.graph.DirectedPseudograph;

/**
 * @author rjordao
 *
 */
public class ForSyDeModel extends DirectedPseudograph<Vertex, Edge> {

	public ForSyDeModel() {
		super(Edge.class);
		
	}

}
