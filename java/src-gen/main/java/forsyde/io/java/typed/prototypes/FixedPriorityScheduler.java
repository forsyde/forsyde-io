package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.FixedPrioritySchedulerPrototype;
import java.lang.Boolean;

public final class FixedPriorityScheduler extends Vertex implements FixedPrioritySchedulerPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.FixedPrioritySchedulerTrait);
  }
}
