package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.SYCombPrototype;
import java.lang.Boolean;
import java.util.Optional;

public final class SYComb extends Vertex implements SYCombPrototype {
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
    return vertex.vertexTraits.contains(VertexTrait.SYCombTrait);
  }
}
