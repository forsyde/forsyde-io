package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface TriggeredTask extends VertexInterface, AbstractOrdering {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.TriggeredTask);
  }

  static Optional<TriggeredTask> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((TriggeredTask) vertex) : Optional.empty();
  }
}
