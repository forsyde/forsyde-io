package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ReactorActorPrototype;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.Optional;

public final class ReactorActor extends Vertex implements ReactorActorPrototype {
  public static ArrayList<ReactorElement> staticGetTriggersPort(ForSyDeModel model, Vertex vertex) {
    ArrayList<ReactorElement> outList = new ArrayList<ReactorElement>();
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("triggers") && ReactorElement.conforms(vertex)) {
        outList.add((ReactorElement) e.source);
      }
    }
    return outList;
  }

  public ArrayList<ReactorElement> getTriggersPort(ForSyDeModel model) {
    return staticGetTriggersPort(model, this);
  }

  public static Optional<ForSyDeFunction> staticGetReactionImplementationPort(ForSyDeModel model,
      Vertex vertex) {
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("reaction_implementation") && ForSyDeFunction.conforms(vertex)) {
        return Optional.of((ForSyDeFunction) e.target);
      }
    }
    for (Edge e: model.incomingEdgesOf(vertex)) {
      if (e.targetPort.orElse("").equals("reaction_implementation") && ForSyDeFunction.conforms(vertex)) {
        return Optional.of((ForSyDeFunction) e.source);
      }
    }
    return Optional.empty();
  }

  public Optional<ForSyDeFunction> getReactionImplementationPort(ForSyDeModel model) {
    return staticGetReactionImplementationPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ReactorActorTrait);
  }
}
