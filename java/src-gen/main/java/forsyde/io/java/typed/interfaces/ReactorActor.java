package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.ArrayList;
import java.util.Optional;

public interface ReactorActor extends VertexInterface, ReactorElement, ForSyDeFunction {
  default ArrayList<VertexInterface> getTriggersPort(ForSyDeModel model) {
    ArrayList<VertexInterface> outList = new ArrayList<VertexInterface>();
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("triggers")) {
        outList.add(e.getSource());
      }
    }
    return outList;
  }

  default Optional<VertexInterface> getReactionImplementationPort(ForSyDeModel model) {
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("reaction_implementation")) {
        return Optional.of(e.getTarget());
      }
    }
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("reaction_implementation")) {
        return Optional.of(e.getSource());
      }
    }
    return Optional.empty();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ReactorActor);
  }
}
