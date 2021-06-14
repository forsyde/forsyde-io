package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Optional;

public abstract class InstrumentedCommunicationInterconnectAcessor {
  public static Optional<NumberVertexProperty> getMaxBandwithBytesPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("max_bandwith_bytes_per_sec")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_bandwith_bytes_per_sec"));
    } else {
      return Optional.empty();
    }
  }
}
