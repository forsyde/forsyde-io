package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.TriggeredTaskPrototype;
import java.lang.Boolean;

public final class TriggeredTask extends Vertex implements TriggeredTaskPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.TriggeredTaskTrait);
  }
}
