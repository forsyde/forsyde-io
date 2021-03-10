/**
 * 
 */
package forsyde.io.java.types.vertex;

import forsyde.io.java.Vertex;

/**
 * @author rjordao
 *
 */
public final class VertexFactory {

  public static Vertex createVertex(String id, String typeName) {
    switch (typeName) {
	  {%- for vtype, vtype_data in vtypes.items() %}
      case "{{vtype}}":
        return new {{vtype}}(id);
    {%- endfor %}
      default:
        return new Vertex(id);
    }
  }

}

