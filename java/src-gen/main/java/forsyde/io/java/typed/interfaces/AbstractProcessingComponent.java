package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;

public interface AbstractProcessingComponent extends VertexInterface, AbstractPhysicalComponent {
  default Boolean getCanBeExplored() {
    return (java.lang.Boolean) getProperties().get("can_be_explored").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbstractProcessingComponent);
  }
}
