package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IAbstractGrouping;
import java.lang.Boolean;

public final class AbstractGroupingPrototype extends Vertex implements IAbstractGrouping {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractGrouping);
  }
}
