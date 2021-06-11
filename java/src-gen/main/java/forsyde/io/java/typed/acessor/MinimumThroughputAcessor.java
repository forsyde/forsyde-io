package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class MinimumThroughputAcessor {
  public static Optional<Integer> getAprioriImportance(Vertex vertex) {
    if (vertex.properties.containsKey("apriori_importance")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("apriori_importance"));
    } else {
      return Optional.empty();
    }
  }

  public static Set<Vertex> getApplicationPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("application")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
