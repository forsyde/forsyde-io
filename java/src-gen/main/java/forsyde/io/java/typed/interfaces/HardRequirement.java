package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface HardRequirement extends VertexInterface, Requirement {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.HardRequirement);
  }

  static Optional<HardRequirement> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((HardRequirement) vertex) : Optional.empty();
  }
}
