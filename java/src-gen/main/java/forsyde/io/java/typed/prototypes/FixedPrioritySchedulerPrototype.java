package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IFixedPriorityScheduler;
import java.lang.Boolean;

public final class FixedPrioritySchedulerPrototype extends Vertex implements IFixedPriorityScheduler {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.FixedPriorityScheduler);
  }
}
