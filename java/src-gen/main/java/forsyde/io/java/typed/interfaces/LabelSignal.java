package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface LabelSignal extends VertexInterface, Signal {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.LabelSignal);
  }

  static Optional<LabelSignal> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((LabelSignal) vertex) : Optional.empty();
  }
}
