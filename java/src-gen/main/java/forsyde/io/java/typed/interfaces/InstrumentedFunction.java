package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface InstrumentedFunction extends VertexInterface, ForSyDeFunction {
  default Map<String, Integer> getMaxOperations() {
    return (java.util.Map<java.lang.String, java.lang.Integer>) getProperties().get("max_operations").unwrap();
  }

  default Integer getMaxMemorySizeInBytes() {
    return (java.lang.Integer) getProperties().get("max_memory_size_in_bytes").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedFunction);
  }
}
