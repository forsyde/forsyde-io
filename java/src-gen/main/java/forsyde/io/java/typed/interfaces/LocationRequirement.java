package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.HashSet;

public interface LocationRequirement extends VertexInterface, Requirement {
  default HashSet<VertexInterface> getProcessPort(ForSyDeModel model) {
    HashSet<VertexInterface> outList = new HashSet<VertexInterface>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("process")) {
        outList.add(e.getTarget());
      }
    }
    return outList;
  }

  default HashSet<VertexInterface> getProcessingUnitPort(ForSyDeModel model) {
    HashSet<VertexInterface> outList = new HashSet<VertexInterface>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("processing_unit")) {
        outList.add(e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.LocationRequirement);
  }
}
