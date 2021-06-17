package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ITriggeredTask;
import java.lang.Boolean;

public final class TriggeredTaskPrototype extends Vertex implements ITriggeredTask {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.TriggeredTask);
  }
}
