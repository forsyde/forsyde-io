package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface SYPrefix extends VertexInterface, ForSyDeFunction {
  default Optional<ForSyDeFunction> getPrefixerPort(ForSyDeModel model) {
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("prefixer") && e.getTarget() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction)  e.getTarget());
      }
    }
    for (EdgeInterface e: model.incomingEdgesOf(this)) {
      if (e.getTargetPort().orElse("").equals("prefixer") && e.getSource() instanceof ForSyDeFunction) {
        return Optional.of((ForSyDeFunction) e.getSource());
      }
    }
    return Optional.empty();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.SYPrefix);
  }

  static Optional<SYPrefix> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((SYPrefix) vertex) : Optional.empty();
  }
}
