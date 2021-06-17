package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IWCET;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class WCETPrototype extends Vertex implements IWCET {
  public static Integer staticGetTime(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("time").unwrap();
  }

  public Integer getTime() {
    return staticGetTime(this);
  }

  public static HashSet<Vertex> staticGetApplicationPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("application")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getApplicationPort(ForSyDeModel model) {
    return staticGetApplicationPort(model, this);
  }

  public static HashSet<Vertex> staticGetPlatformPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getPlatformPort(ForSyDeModel model) {
    return staticGetPlatformPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.WCET);
  }
}
