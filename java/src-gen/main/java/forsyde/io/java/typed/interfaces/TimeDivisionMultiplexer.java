package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;

public interface TimeDivisionMultiplexer extends VertexInterface, AbstractCommunicationComponent {
  default Integer getSlots() {
    return (java.lang.Integer) getProperties().get("slots").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.TimeDivisionMultiplexer);
  }
}
