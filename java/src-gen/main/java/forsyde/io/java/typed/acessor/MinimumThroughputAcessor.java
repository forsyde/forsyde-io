package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Optional;

public abstract class MinimumThroughputAcessor {
  public static Optional<NumberVertexProperty> getAprioriImportance(Vertex vertex) {
    if (vertex.properties.containsKey("apriori_importance")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("apriori_importance"));
    } else {
      return Optional.empty();
    }
  }

  public static HashSet<Vertex> getApplicationPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("application")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
