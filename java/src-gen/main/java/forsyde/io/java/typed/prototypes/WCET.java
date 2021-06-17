package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.WCETPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class WCET extends Vertex implements WCETPrototype {
  public static Integer staticGetTime(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("time").unwrap();
  }

  public Integer getTime() {
    return staticGetTime(this);
  }

  public static HashSet<Process> staticGetApplicationPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Process> outList = new HashSet<Process>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("application") && Process.conforms(vertex)) {
        outList.add((Process) e.target);
      }
    }
    return outList;
  }

  public HashSet<Process> getApplicationPort(ForSyDeModel model) {
    return staticGetApplicationPort(model, this);
  }

  public static HashSet<AbstractProcessingComponent> staticGetPlatformPort(ForSyDeModel model,
      Vertex vertex) {
    HashSet<AbstractProcessingComponent> outList = new HashSet<AbstractProcessingComponent>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("platform") && AbstractProcessingComponent.conforms(vertex)) {
        outList.add((AbstractProcessingComponent) e.target);
      }
    }
    return outList;
  }

  public HashSet<AbstractProcessingComponent> getPlatformPort(ForSyDeModel model) {
    return staticGetPlatformPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.WCETTrait);
  }
}
