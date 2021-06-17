package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IAbstractOrdering;
import java.lang.Boolean;

public final class AbstractOrderingPrototype extends Vertex implements IAbstractOrdering {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractOrdering);
  }
}
