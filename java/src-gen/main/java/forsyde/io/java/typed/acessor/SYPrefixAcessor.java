package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.Optional;

public abstract class SYPrefixAcessor {
  public static Optional<Vertex> getPrefixerPort(ForSyDeModel model, Vertex vertex) {
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
}
