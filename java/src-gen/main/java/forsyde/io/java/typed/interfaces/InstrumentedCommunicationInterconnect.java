package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;

public interface InstrumentedCommunicationInterconnect extends VertexInterface, AbstractCommunicationComponent {
  default Integer getMaxBandwithBytesPerSec() {
    return (java.lang.Integer) getProperties().get("max_bandwith_bytes_per_sec").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedCommunicationInterconnect);
  }
}
