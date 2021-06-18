package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface InstrumentedFunction extends VertexInterface, ForSyDeFunction {
  default String getMaxOperations() {
    return (String) getProperties().get("max_operations").unwrap();
  }

  default String getMaxMemorySizeInBytes() {
    return (String) getProperties().get("max_memory_size_in_bytes").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedFunction);
  }

  static Optional<InstrumentedFunction> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((InstrumentedFunction) vertex) : Optional.empty();
  }
}
