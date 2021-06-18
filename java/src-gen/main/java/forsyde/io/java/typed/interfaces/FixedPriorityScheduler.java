package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface FixedPriorityScheduler extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.FixedPriorityScheduler);
  }

  static Optional<FixedPriorityScheduler> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((FixedPriorityScheduler) vertex) : Optional.empty();
  }
}
