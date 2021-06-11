package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedAcessor {
  public static Optional<Map<String, Map<String, Integer>>> getRequires(Vertex vertex) {
    if (vertex.properties.containsKey("requires")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("requires"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getProvides(Vertex vertex) {
    if (vertex.properties.containsKey("provides")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("provides"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<List<String>> getConfigurations(Vertex vertex) {
    if (vertex.properties.containsKey("configurations")) {
      return Optional.of((java.util.List<java.lang.String>) vertex.properties.get("configurations"));
    } else {
      return Optional.empty();
    }
  }
}
