/**
 * 
 */
package forsyde.io.java.types.edge;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.Vertex;

/**
 * @author rjordao
 *
 */
public final class EdgeFactory {

  public static Edge createEdge(Vertex s, Vertex t, String typeName) {
    switch (typeName) {
	  {%- for etype, etype_data in etypes.items() %}
      case "{{etype}}":
        return new {{etype}}(s, t);
    {%- endfor %}
      default:
        return new Edge(s, t);
    }
  }

}

