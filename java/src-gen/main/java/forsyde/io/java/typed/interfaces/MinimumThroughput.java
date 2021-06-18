package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.HashSet;
import java.util.Optional;

public interface MinimumThroughput extends VertexInterface, Goal {
  default String getAprioriImportance() {
    return (String) getProperties().get("apriori_importance").unwrap();
  }

  default HashSet<Process> getApplicationPort(ForSyDeModel model) {
    HashSet<Process> outList = new HashSet<Process>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("application") && e.getTarget() instanceof Process) {
        outList.add((Process)  e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.MinimumThroughput);
  }

  static Optional<MinimumThroughput> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((MinimumThroughput) vertex) : Optional.empty();
  }
}
