package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface ForSyDeFunction extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ForSyDeFunction);
  }

  static Optional<ForSyDeFunction> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((ForSyDeFunction) vertex) : Optional.empty();
  }
}
