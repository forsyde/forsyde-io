package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.Optional;

public abstract class TimeDivisionMultiplexerAcessor {
  public static Optional<Integer> getSlots(Vertex vertex) {
    if (vertex.properties.containsKey("slots")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("slots"));
    } else {
      return Optional.empty();
    }
  }
}
