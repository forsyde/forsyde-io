package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface AbstractProcessingComponent extends VertexInterface, AbstractPhysicalComponent {
  default String getCanBeExplored() {
    return (String) getProperties().get("can_be_explored").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbstractProcessingComponent);
  }

  static Optional<AbstractProcessingComponent> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((AbstractProcessingComponent) vertex) : Optional.empty();
  }
}
