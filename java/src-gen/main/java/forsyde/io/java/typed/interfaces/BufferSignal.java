package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface BufferSignal extends VertexInterface, Signal {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.BufferSignal);
  }

  static Optional<BufferSignal> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((BufferSignal) vertex) : Optional.empty();
  }
}
