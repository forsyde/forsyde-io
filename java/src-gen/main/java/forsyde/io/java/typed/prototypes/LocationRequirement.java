package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.LocationRequirementPrototype;
import java.lang.Boolean;
import java.util.HashSet;

public final class LocationRequirement extends Vertex implements LocationRequirementPrototype {
  public static HashSet<Process> staticGetProcessPort(ForSyDeModel model, Vertex vertex) {
    HashSet<Process> outList = new HashSet<Process>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("process") && Process.conforms(vertex)) {
        outList.add((Process) e.target);
      }
    }
    return outList;
  }

  public HashSet<Process> getProcessPort(ForSyDeModel model) {
    return staticGetProcessPort(model, this);
  }

  public static HashSet<AbstractProcessingComponent> staticGetProcessingUnitPort(ForSyDeModel model,
      Vertex vertex) {
    HashSet<AbstractProcessingComponent> outList = new HashSet<AbstractProcessingComponent>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("processing_unit") && AbstractProcessingComponent.conforms(vertex)) {
        outList.add((AbstractProcessingComponent) e.target);
      }
    }
    return outList;
  }

  public HashSet<AbstractProcessingComponent> getProcessingUnitPort(ForSyDeModel model) {
    return staticGetProcessingUnitPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.LocationRequirementTrait);
  }
}
