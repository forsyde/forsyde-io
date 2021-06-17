package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IReactorActor;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.Optional;

public final class ReactorActorPrototype extends Vertex implements IReactorActor {
  public static ArrayList<Vertex> staticGetTriggersPort(ForSyDeModel model, Vertex vertex) {
    ArrayList<Vertex> outList = new ArrayList<Vertex>();
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("triggers")) {
        outList.add(e.source);
      }
    }
    return outList;
  }

  public ArrayList<Vertex> getTriggersPort(ForSyDeModel model) {
    return staticGetTriggersPort(model, this);
  }

  public static Optional<Vertex> staticGetReactionImplementationPort(ForSyDeModel model,
      Vertex vertex) {
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

  public Optional<Vertex> getReactionImplementationPort(ForSyDeModel model) {
    return staticGetReactionImplementationPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ReactorActor);
  }
}
