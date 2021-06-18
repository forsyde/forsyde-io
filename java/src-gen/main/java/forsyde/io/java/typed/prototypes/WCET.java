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

  public static HashSet<ForSyDeFunction> staticGetApplicationPort(ForSyDeModel model,
      Vertex vertex) {
    HashSet<ForSyDeFunction> outList = new HashSet<ForSyDeFunction>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("application") && ForSyDeFunction.conforms(vertex)) {
        outList.add((ForSyDeFunction) e.target);
      }
    }
    return outList;
  }

  public HashSet<ForSyDeFunction> getApplicationPort(ForSyDeModel model) {
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
