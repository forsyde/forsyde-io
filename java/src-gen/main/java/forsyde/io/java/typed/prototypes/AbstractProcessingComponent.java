package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractProcessingComponentPrototype;
import java.lang.Boolean;

public final class AbstractProcessingComponent extends Vertex implements AbstractProcessingComponentPrototype {
  public static Boolean staticGetCanBeExplored(Vertex vertex) {
    return (java.lang.Boolean) vertex.properties.get("can_be_explored").unwrap();
  }

  public Boolean getCanBeExplored() {
    return staticGetCanBeExplored(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractProcessingComponentTrait);
  }
}
