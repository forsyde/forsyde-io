package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface InstrumentedSignal extends VertexInterface, Signal, Instrumented {
  default String getMaxElemSizeBytes() {
    return (String) getProperties().get("max_elem_size_bytes").unwrap();
  }

  default String getMaxElemCount() {
    return (String) getProperties().get("max_elem_count").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedSignal);
  }

  static Optional<InstrumentedSignal> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((InstrumentedSignal) vertex) : Optional.empty();
  }
}
