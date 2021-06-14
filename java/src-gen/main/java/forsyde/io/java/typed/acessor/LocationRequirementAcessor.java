package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.HashSet;

public abstract class LocationRequirementAcessor {
  public static HashSet<Vertex> getProcessPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("process")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public static HashSet<Vertex> getProcessingUnitPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("processing_unit")) {
        outList.add(e.target);
      }
    }
    return outList;
  }
}
