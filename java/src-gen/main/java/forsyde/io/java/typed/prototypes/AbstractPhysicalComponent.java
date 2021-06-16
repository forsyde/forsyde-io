package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractPhysicalComponentPrototype;
import java.lang.Boolean;

public final class AbstractPhysicalComponent extends Vertex implements AbstractPhysicalComponentPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractPhysicalComponentTrait);
  }
}
