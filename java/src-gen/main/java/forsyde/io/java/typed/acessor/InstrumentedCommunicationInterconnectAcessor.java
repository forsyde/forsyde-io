package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.Optional;

public abstract class InstrumentedCommunicationInterconnectAcessor {
  public static Optional<Integer> getMaxBandwithBytesPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("max_bandwith_bytes_per_sec")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_bandwith_bytes_per_sec").unwrap());
    } else {
      return Optional.empty();
    }
  }
}
