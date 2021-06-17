package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IGoal;
import java.lang.Boolean;

public final class GoalPrototype extends Vertex implements IGoal {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.Goal);
  }
}
