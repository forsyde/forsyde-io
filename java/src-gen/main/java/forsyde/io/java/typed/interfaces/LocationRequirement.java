package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.HashSet;
import java.util.Optional;

public interface LocationRequirement extends VertexInterface, Requirement {
  default HashSet<Process> getProcessPort(ForSyDeModel model) {
    HashSet<Process> outList = new HashSet<Process>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("process") && e.getTarget() instanceof Process) {
        outList.add((Process)  e.getTarget());
      }
    }
    return outList;
  }

  default HashSet<AbstractProcessingComponent> getProcessingUnitPort(ForSyDeModel model) {
    HashSet<AbstractProcessingComponent> outList = new HashSet<AbstractProcessingComponent>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("processing_unit") && e.getTarget() instanceof AbstractProcessingComponent) {
        outList.add((AbstractProcessingComponent)  e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.LocationRequirement);
  }

  static Optional<LocationRequirement> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((LocationRequirement) vertex) : Optional.empty();
  }
}
