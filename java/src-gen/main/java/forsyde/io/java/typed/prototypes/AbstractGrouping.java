package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractGroupingPrototype;
import java.lang.Boolean;

public final class AbstractGrouping extends Vertex implements AbstractGroupingPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractGroupingTrait);
  }
}
