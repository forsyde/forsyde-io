package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IHardRequirement;
import java.lang.Boolean;

public final class HardRequirementPrototype extends Vertex implements IHardRequirement {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.HardRequirement);
  }
}
