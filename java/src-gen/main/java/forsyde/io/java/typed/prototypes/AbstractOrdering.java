package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractOrderingPrototype;
import java.lang.Boolean;

public final class AbstractOrdering extends Vertex implements AbstractOrderingPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractOrderingTrait);
  }
}
