package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.util.Optional;

public interface AbstractOrdering extends VertexInterface, AbstractGrouping {
  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.AbstractOrdering);
  }

  static Optional<AbstractOrdering> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((AbstractOrdering) vertex) : Optional.empty();
  }
}
