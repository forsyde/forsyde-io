package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedFunctionAcessor {
  public static Optional<Map<StringVertexProperty, NumberVertexProperty>> getMaxOperations(
      Vertex vertex) {
    if (vertex.properties.containsKey("max_operations")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>) vertex.properties.get("max_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getMaxMemorySizeInBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_size_in_bytes")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_memory_size_in_bytes"));
    } else {
      return Optional.empty();
    }
  }
}
