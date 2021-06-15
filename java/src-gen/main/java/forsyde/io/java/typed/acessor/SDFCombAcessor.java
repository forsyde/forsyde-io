package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public abstract class SDFCombAcessor {
  public static Optional<Map<String, Integer>> getConsumption(Vertex vertex) {
    if (vertex.properties.containsKey("consumption")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("consumption").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getProduction(Vertex vertex) {
    if (vertex.properties.containsKey("production")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("production").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Vertex> getCombinatorPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("combinator")) {
        return Optional.of(e.target);
      }
    }
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("combinator")) {
        return Optional.of(e.source);
      }
    }
    return Optional.empty();
  }
}
