package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.Optional;

public abstract class ReactorTimerAcessor {
  public static Optional<Integer> getPeriodNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_numerator_per_sec")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("period_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getPeriodDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_denominator_per_sec")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("period_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getOffsetNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_numerator_per_sec")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("offset_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getOffsetDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_denominator_per_sec")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("offset_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Vertex getOutputPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("output")) {
        return e.target;
      }
    }
    return null;
  }
}
