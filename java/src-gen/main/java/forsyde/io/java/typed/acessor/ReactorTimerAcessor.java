package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Optional;

public abstract class ReactorTimerAcessor {
  public static Optional<NumberVertexProperty> getPeriodNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_numerator_per_sec")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("period_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getPeriodDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_denominator_per_sec")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("period_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getOffsetNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_numerator_per_sec")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("offset_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getOffsetDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_denominator_per_sec")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("offset_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static HashSet<Vertex> getOutputPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("output")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
