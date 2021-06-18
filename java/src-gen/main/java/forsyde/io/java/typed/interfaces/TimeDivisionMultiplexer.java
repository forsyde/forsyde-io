package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface TimeDivisionMultiplexer extends VertexInterface, AbstractCommunicationComponent {
  default String getSlots() {
    return (String) getProperties().get("slots").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.TimeDivisionMultiplexer);
  }

  static Optional<TimeDivisionMultiplexer> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((TimeDivisionMultiplexer) vertex) : Optional.empty();
  }
}
