package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface AbstractStorageComponent extends VertexInterface, AbstractPhysicalComponent {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbstractStorageComponent);
  }

  static Optional<AbstractStorageComponent> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((AbstractStorageComponent) vertex) : Optional.empty();
  }
}
