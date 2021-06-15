package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedSignalAcessor {
  public static Optional<Integer> getMaxElemSizeBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_size_bytes")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_elem_size_bytes").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxElemCount(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_count")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_elem_count").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getRequires(Vertex vertex) {
    return InstrumentedAcessor.getRequires(vertex);
  }

  public static Optional<Map<String, Map<String, Integer>>> getProvides(Vertex vertex) {
    return InstrumentedAcessor.getProvides(vertex);
  }

  public static Optional<List<String>> getConfigurations(Vertex vertex) {
    return InstrumentedAcessor.getConfigurations(vertex);
  }
}
