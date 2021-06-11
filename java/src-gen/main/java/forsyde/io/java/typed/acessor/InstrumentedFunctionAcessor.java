package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedFunctionAcessor {
  public static Optional<Map<String, Integer>> getMaxOperations(Vertex vertex) {
    if (vertex.properties.containsKey("max_operations")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("max_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxMemorySizeInBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_size_in_bytes")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_memory_size_in_bytes"));
    } else {
      return Optional.empty();
    }
  }
}
