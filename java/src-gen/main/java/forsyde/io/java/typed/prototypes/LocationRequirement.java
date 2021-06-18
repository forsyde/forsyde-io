package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.LocationRequirementPrototype;
import java.lang.Boolean;
import java.util.HashSet;

public final class LocationRequirement extends Vertex implements LocationRequirementPrototype {
  public static HashSet<ForSyDeFunction> staticGetForSyDeFunctionPort(ForSyDeModel model,
      Vertex vertex) {
    HashSet<ForSyDeFunction> outList = new HashSet<ForSyDeFunction>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("ForSyDeFunction") && ForSyDeFunction.conforms(vertex)) {
        outList.add((ForSyDeFunction) e.target);
      }
    }
    return outList;
  }

  public HashSet<ForSyDeFunction> getForSyDeFunctionPort(ForSyDeModel model) {
    return staticGetForSyDeFunctionPort(model, this);
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
