package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Map;
import java.util.Optional;

public abstract class SDFCombAcessor {
  public static Optional<Map<StringVertexProperty, NumberVertexProperty>> getConsumption(
      Vertex vertex) {
    if (vertex.properties.containsKey("consumption")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>) vertex.properties.get("consumption"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<StringVertexProperty, NumberVertexProperty>> getProduction(
      Vertex vertex) {
    if (vertex.properties.containsKey("production")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>) vertex.properties.get("production"));
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
