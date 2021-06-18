package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface AbstractCommunicationComponent extends VertexInterface, AbstractPhysicalComponent {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbstractCommunicationComponent);
  }

  static Optional<AbstractCommunicationComponent> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((AbstractCommunicationComponent) vertex) : Optional.empty();
  }
}
