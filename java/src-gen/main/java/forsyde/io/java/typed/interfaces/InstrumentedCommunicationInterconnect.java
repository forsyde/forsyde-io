package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface InstrumentedCommunicationInterconnect extends VertexInterface, AbstractCommunicationComponent {
  default String getMaxBandwithBytesPerSec() {
    return (String) getProperties().get("max_bandwith_bytes_per_sec").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedCommunicationInterconnect);
  }

  static Optional<InstrumentedCommunicationInterconnect> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((InstrumentedCommunicationInterconnect) vertex) : Optional.empty();
  }
}
