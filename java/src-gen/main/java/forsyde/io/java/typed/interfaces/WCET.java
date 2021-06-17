package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public interface WCET extends VertexInterface, ExtraFunctional {
  default Integer getTime() {
    return (java.lang.Integer) getProperties().get("time").unwrap();
  }

  default HashSet<VertexInterface> getApplicationPort(ForSyDeModel model) {
    HashSet<VertexInterface> outList = new HashSet<VertexInterface>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("application")) {
        outList.add(e.getTarget());
      }
    }
    return outList;
  }

  default HashSet<VertexInterface> getPlatformPort(ForSyDeModel model) {
    HashSet<VertexInterface> outList = new HashSet<VertexInterface>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("platform")) {
        outList.add(e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.WCET);
  }
}
