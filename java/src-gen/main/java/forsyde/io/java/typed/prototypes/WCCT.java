package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.WCCTPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class WCCT extends Vertex implements WCCTPrototype {
  public static Integer staticGetTime(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("time").unwrap();
  }

  public Integer getTime() {
    return staticGetTime(this);
  }

  public static HashSet<Process> staticGetSignalPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Process> outList = new HashSet<Process>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("signal") && Process.conforms(vertex)) {
        outList.add((Process) e.target);
      }
    }
    return outList;
  }

  public HashSet<Process> getSignalPort(ForSyDeModel model) {
    return staticGetSignalPort(model, this);
  }

  public static HashSet<AbstractCommunicationComponent> staticGetPlatformElementPort(
      ForSyDeModel model, Vertex vertex) {
    HashSet<AbstractCommunicationComponent> outList = new HashSet<AbstractCommunicationComponent>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform_element") && AbstractCommunicationComponent.conforms(vertex)) {
        outList.add((AbstractCommunicationComponent) e.target);
      }
    }
    return outList;
  }

  public HashSet<AbstractCommunicationComponent> getPlatformElementPort(ForSyDeModel model) {
    return staticGetPlatformElementPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.WCCTTrait);
  }
}
