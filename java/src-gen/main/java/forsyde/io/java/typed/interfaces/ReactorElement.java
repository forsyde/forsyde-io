package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface ReactorElement extends VertexInterface {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ReactorElement);
  }

  static Optional<ReactorElement> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((ReactorElement) vertex) : Optional.empty();
  }
}
