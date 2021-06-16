package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.InstrumentedCommunicationInterconnectPrototype;
import java.lang.Boolean;
import java.lang.Integer;

public final class InstrumentedCommunicationInterconnect extends Vertex implements InstrumentedCommunicationInterconnectPrototype {
  public static Integer staticGetMaxBandwithBytesPerSec(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_bandwith_bytes_per_sec").unwrap();
  }

  public Integer getMaxBandwithBytesPerSec() {
    return staticGetMaxBandwithBytesPerSec(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.InstrumentedCommunicationInterconnectTrait);
  }
}
