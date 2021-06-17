package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IRequirement;
import java.lang.Boolean;

public final class RequirementPrototype extends Vertex implements IRequirement {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.Requirement);
  }
}
