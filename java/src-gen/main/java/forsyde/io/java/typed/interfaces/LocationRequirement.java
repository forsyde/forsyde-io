package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.HashSet;
import java.util.Optional;

public interface LocationRequirement extends VertexInterface, Requirement {
  default HashSet<ForSyDeFunction> getForSyDeFunctionPort(ForSyDeModel model) {
    HashSet<ForSyDeFunction> outList = new HashSet<ForSyDeFunction>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("ForSyDeFunction") && e.getTarget() instanceof ForSyDeFunction) {
        outList.add((ForSyDeFunction)  e.getTarget());
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
