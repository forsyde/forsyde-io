package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedSignalAcessor {
  public static Optional<NumberVertexProperty> getMaxElemSizeBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_size_bytes")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_elem_size_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getMaxElemCount(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_count")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_elem_count"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getRequires(
      Vertex vertex) {
    return InstrumentedAcessor.getRequires(vertex);
  }

  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getProvides(
      Vertex vertex) {
    return InstrumentedAcessor.getProvides(vertex);
  }

  public static Optional<List<StringVertexProperty>> getConfigurations(Vertex vertex) {
    return InstrumentedAcessor.getConfigurations(vertex);
  }
}
