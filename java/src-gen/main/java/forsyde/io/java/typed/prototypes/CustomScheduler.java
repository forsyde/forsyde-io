package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.CustomSchedulerPrototype;
import java.lang.Boolean;

public final class CustomScheduler extends Vertex implements CustomSchedulerPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.CustomSchedulerTrait);
  }
}
