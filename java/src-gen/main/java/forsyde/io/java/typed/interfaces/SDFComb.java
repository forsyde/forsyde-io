package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public interface SDFComb extends VertexInterface, ForSyDeFunction {
  default Map<String, Integer> getConsumption() {
    return (java.util.Map<java.lang.String, java.lang.Integer>) getProperties().get("consumption").unwrap();
  }

  default Map<String, Integer> getProduction() {
    return (java.util.Map<java.lang.String, java.lang.Integer>) getProperties().get("production").unwrap();
  }

  default Optional<VertexInterface> getCombinatorPort(ForSyDeModel model) {
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("combinator")) {
        return Optional.of(e.getTarget());
      }
    }
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("combinator")) {
        return Optional.of(e.getSource());
      }
    }
    return Optional.empty();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.SDFComb);
  }
}
