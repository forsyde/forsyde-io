package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface ExtraFunctional extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ExtraFunctional);
  }

  static Optional<ExtraFunctional> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((ExtraFunctional) vertex) : Optional.empty();
  }
}
