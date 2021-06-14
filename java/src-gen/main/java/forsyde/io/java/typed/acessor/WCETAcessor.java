package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Optional;

public abstract class WCETAcessor {
  public static Optional<NumberVertexProperty> getTime(Vertex vertex) {
    if (vertex.properties.containsKey("time")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("time"));
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

  public static HashSet<Vertex> getPlatformPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
