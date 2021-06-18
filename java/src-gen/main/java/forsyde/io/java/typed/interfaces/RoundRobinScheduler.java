package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface RoundRobinScheduler extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.RoundRobinScheduler);
  }

  static Optional<RoundRobinScheduler> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((RoundRobinScheduler) vertex) : Optional.empty();
  }
}
