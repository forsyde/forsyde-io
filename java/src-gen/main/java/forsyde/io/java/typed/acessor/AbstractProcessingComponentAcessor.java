package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.BooleanVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Optional;

public abstract class AbstractProcessingComponentAcessor {
  public static Optional<BooleanVertexProperty> getCanBeExplored(Vertex vertex) {
    if (vertex.properties.containsKey("can_be_explored")) {
      return Optional.of((forsyde.io.java.core.BooleanVertexProperty) vertex.properties.get("can_be_explored"));
    } else {
      return Optional.empty();
    }
  }
}
