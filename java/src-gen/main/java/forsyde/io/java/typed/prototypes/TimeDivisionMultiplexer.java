package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.TimeDivisionMultiplexerPrototype;
import java.lang.Boolean;
import java.lang.Integer;

public final class TimeDivisionMultiplexer extends Vertex implements TimeDivisionMultiplexerPrototype {
  public static Integer staticGetSlots(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("slots").unwrap();
  }

  public Integer getSlots() {
    return staticGetSlots(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.TimeDivisionMultiplexerTrait);
  }
}
