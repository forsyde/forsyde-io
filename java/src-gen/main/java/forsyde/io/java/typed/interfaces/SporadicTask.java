package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface SporadicTask extends VertexInterface, AbstractOrdering {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.SporadicTask);
  }

  static Optional<SporadicTask> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((SporadicTask) vertex) : Optional.empty();
  }
}
