package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ForSyDeFunctionPrototype;
import java.lang.Boolean;

public final class ForSyDeFunction extends Vertex implements ForSyDeFunctionPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ForSyDeFunctionTrait);
  }
}
