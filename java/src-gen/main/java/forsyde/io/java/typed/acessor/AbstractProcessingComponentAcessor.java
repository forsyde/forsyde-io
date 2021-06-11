package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Boolean;
import java.util.Optional;

public abstract class AbstractProcessingComponentAcessor {
  public static Optional<Boolean> getCanBeExplored(Vertex vertex) {
    if (vertex.properties.containsKey("can_be_explored")) {
      return Optional.of((java.lang.Boolean) vertex.properties.get("can_be_explored"));
    } else {
      return Optional.empty();
    }
  }
}
