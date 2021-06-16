package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.SYPrefixPrototype;
import java.lang.Boolean;
import java.util.Optional;

public final class SYPrefix extends Vertex implements SYPrefixPrototype {
  public static Optional<Vertex> staticGetPrefixerPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("prefixer")) {
        return Optional.of(e.target);
      }
    }
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("prefixer")) {
        return Optional.of(e.source);
      }
    }
    return Optional.empty();
  }

  public Optional<Vertex> getPrefixerPort(ForSyDeModel model) {
    return staticGetPrefixerPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.SYPrefixTrait);
  }
}
