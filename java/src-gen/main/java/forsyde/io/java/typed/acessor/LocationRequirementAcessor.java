package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;
import java.util.Set;

public abstract class LocationRequirementAcessor {
  public static Set<Vertex> getProcessPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("process")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public static Set<Vertex> getProcessingUnitPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("processing_unit")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
