package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Optional;

public abstract class TimeDivisionMultiplexerAcessor {
  public static Optional<NumberVertexProperty> getSlots(Vertex vertex) {
    if (vertex.properties.containsKey("slots")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("slots"));
    } else {
      return Optional.empty();
    }
  }
}
