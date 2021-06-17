package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IRoundRobinScheduler;
import java.lang.Boolean;

public final class RoundRobinSchedulerPrototype extends Vertex implements IRoundRobinScheduler {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.RoundRobinScheduler);
  }
}
