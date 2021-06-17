package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.MinimumThroughputPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class MinimumThroughput extends Vertex implements MinimumThroughputPrototype {
  public static Integer staticGetAprioriImportance(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("apriori_importance").unwrap();
  }

  public Integer getAprioriImportance() {
    return staticGetAprioriImportance(this);
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

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.MinimumThroughputTrait);
  }
}
