package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.Optional;

public interface ReactorActor extends VertexInterface, ReactorElement, ForSyDeFunction {
  default ArrayList<ReactorElement> getTriggersPort(ForSyDeModel model) {
    ArrayList<ReactorElement> outList = new ArrayList<ReactorElement>();
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("triggers") && e.getSource() instanceof ReactorElement) {
        outList.add((ReactorElement) e.getSource());
      }
    }
    return outList;
  }

  default Optional<ForSyDeFunction> getReactionImplementationPort(ForSyDeModel model) {
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("reaction_implementation") && e.getTarget() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction)  e.getTarget());
      }
    }
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("reaction_implementation") && e.getSource() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction) e.getSource());
      }
    }
    return Optional.empty();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ReactorActor);
  }

  static Optional<ReactorActor> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((ReactorActor) vertex) : Optional.empty();
  }
}
