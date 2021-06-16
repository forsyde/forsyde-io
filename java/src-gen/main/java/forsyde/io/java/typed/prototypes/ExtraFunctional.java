package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ExtraFunctionalPrototype;
import java.lang.Boolean;

public final class ExtraFunctional extends Vertex implements ExtraFunctionalPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ExtraFunctionalTrait);
  }
}
