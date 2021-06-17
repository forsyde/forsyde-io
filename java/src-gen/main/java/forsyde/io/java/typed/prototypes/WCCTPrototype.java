package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IWCCT;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class WCCTPrototype extends Vertex implements IWCCT {
  public static Integer staticGetTime(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("time").unwrap();
  }

  public Integer getTime() {
    return staticGetTime(this);
  }

  public static HashSet<Vertex> staticGetSignalPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("signal")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getSignalPort(ForSyDeModel model) {
    return staticGetSignalPort(model, this);
  }

  public static HashSet<Vertex> staticGetPlatformElementPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform_element")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getPlatformElementPort(ForSyDeModel model) {
    return staticGetPlatformElementPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.WCCT);
  }
}
