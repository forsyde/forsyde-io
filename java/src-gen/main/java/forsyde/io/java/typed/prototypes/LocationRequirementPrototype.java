package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ILocationRequirement;
import java.lang.Boolean;
import java.util.HashSet;

public final class LocationRequirementPrototype extends Vertex implements ILocationRequirement {
  public static HashSet<Vertex> staticGetProcessPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("process")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getProcessPort(ForSyDeModel model) {
    return staticGetProcessPort(model, this);
  }

  public static HashSet<Vertex> staticGetProcessingUnitPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Vertex> outList = new HashSet<Vertex>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("processing_unit")) {
        outList.add(e.target);
      }
    }
    return outList;
  }

  public HashSet<Vertex> getProcessingUnitPort(ForSyDeModel model) {
    return staticGetProcessingUnitPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.LocationRequirement);
  }
}
