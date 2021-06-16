package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.HardRequirementPrototype;
import java.lang.Boolean;

public final class HardRequirement extends Vertex implements HardRequirementPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.HardRequirementTrait);
  }
}
