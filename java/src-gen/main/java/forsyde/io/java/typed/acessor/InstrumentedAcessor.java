package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedAcessor {
  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getRequires(
      Vertex vertex) {
    if (vertex.properties.containsKey("requires")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>>) vertex.properties.get("requires"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getProvides(
      Vertex vertex) {
    if (vertex.properties.containsKey("provides")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>>) vertex.properties.get("provides"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<List<StringVertexProperty>> getConfigurations(Vertex vertex) {
    if (vertex.properties.containsKey("configurations")) {
      return Optional.of((java.util.List<forsyde.io.java.core.StringVertexProperty>) vertex.properties.get("configurations"));
    } else {
      return Optional.empty();
    }
  }
}
