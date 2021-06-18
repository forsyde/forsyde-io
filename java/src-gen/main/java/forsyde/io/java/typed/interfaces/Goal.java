package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface Goal extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.Goal);
  }

  static Optional<Goal> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((Goal) vertex) : Optional.empty();
  }
}
