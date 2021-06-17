package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;

public interface InstrumentedSignal extends VertexInterface, Signal, Instrumented {
  default Integer getMaxElemSizeBytes() {
    return (java.lang.Integer) getProperties().get("max_elem_size_bytes").unwrap();
  }

  default Integer getMaxElemCount() {
    return (java.lang.Integer) getProperties().get("max_elem_count").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedSignal);
  }
}
