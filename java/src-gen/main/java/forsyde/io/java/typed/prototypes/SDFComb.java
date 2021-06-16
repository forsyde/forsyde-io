package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.SDFCombPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public final class SDFComb extends Vertex implements SDFCombPrototype {
  public static Map<String, Integer> staticGetConsumption(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("consumption").unwrap();
  }

  public Map<String, Integer> getConsumption() {
    return staticGetConsumption(this);
  }

  public static Map<String, Integer> staticGetProduction(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("production").unwrap();
  }

  public Map<String, Integer> getProduction() {
    return staticGetProduction(this);
  }

  public static Optional<Vertex> staticGetCombinatorPort(ForSyDeModel model, Vertex vertex) {
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

  public Optional<Vertex> getCombinatorPort(ForSyDeModel model) {
    return staticGetCombinatorPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.SDFCombTrait);
  }
}
