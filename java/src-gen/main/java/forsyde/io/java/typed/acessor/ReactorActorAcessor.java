package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;

public abstract class ReactorActorAcessor {
  public static Vertex getTriggersPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("triggers")) {
        return e.source;
      }
    }
    return null;
  }

  public static Vertex getReactionImplementationPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("reaction_implementation")) {
        return e.target;
      }
    }
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("reaction_implementation")) {
        return e.source;
      }
    }
    return null;
  }
}
