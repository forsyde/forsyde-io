package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.HashSet;
import java.util.Optional;

public abstract class WCCTAcessor {
  public static Optional<Integer> getTime(Vertex vertex) {
    if (vertex.properties.containsKey("time")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("time").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static HashSet<Vertex> getSignalPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("signal")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public static HashSet<Vertex> getPlatformElementPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform_element")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
