package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.SporadicTaskPrototype;
import java.lang.Boolean;

public final class SporadicTask extends Vertex implements SporadicTaskPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.SporadicTaskTrait);
  }
}
