package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public interface MinimumThroughput extends VertexInterface, Goal {
  default Integer getAprioriImportance() {
    return (java.lang.Integer) getProperties().get("apriori_importance").unwrap();
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

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.MinimumThroughput);
  }
}
