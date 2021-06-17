package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IForSyDeFunction;
import java.lang.Boolean;

public final class ForSyDeFunctionPrototype extends Vertex implements IForSyDeFunction {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ForSyDeFunction);
  }
}
