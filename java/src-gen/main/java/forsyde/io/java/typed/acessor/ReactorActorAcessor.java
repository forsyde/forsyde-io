package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.ArrayList;
import java.util.Optional;

public abstract class ReactorActorAcessor {
  public static ArrayList<Vertex> getTriggersPort(ForSyDeModel model, Vertex vertex) {
    ArrayList<Vertex> outList = new ArrayList<Vertex>();
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("triggers")) {
        outList.add(e.source);
      }
    }
    return outList;
  }

  public static Optional<Vertex> getReactionImplementationPort(ForSyDeModel model, Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("reaction_implementation")) {
        return Optional.of(e.target);
      }
    }
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("reaction_implementation")) {
        return Optional.of(e.source);
      }
    }
    return Optional.empty();
  }
}
