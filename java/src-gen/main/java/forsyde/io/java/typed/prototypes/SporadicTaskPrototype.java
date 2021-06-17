package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ISporadicTask;
import java.lang.Boolean;

public final class SporadicTaskPrototype extends Vertex implements ISporadicTask {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.SporadicTask);
  }
}
