package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface SDFComb extends VertexInterface, ForSyDeFunction {
  default String getConsumption() {
    return (String) getProperties().get("consumption").unwrap();
  }

  default String getProduction() {
    return (String) getProperties().get("production").unwrap();
  }

  default Optional<ForSyDeFunction> getCombinatorPort(ForSyDeModel model) {
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("combinator") && e.getTarget() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction)  e.getTarget());
      }
    }
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("combinator") && e.getSource() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction) e.getSource());
      }
    }
    return Optional.empty();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.SDFComb);
  }

  static Optional<SDFComb> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((SDFComb) vertex) : Optional.empty();
  }
}
