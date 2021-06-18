package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface AbsractInterfaceComponent extends VertexInterface, AbstractPhysicalComponent {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbsractInterfaceComponent);
  }

  static Optional<AbsractInterfaceComponent> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((AbsractInterfaceComponent) vertex) : Optional.empty();
  }
}
