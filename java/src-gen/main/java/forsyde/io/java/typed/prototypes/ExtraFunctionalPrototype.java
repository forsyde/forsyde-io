package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IExtraFunctional;
import java.lang.Boolean;

public final class ExtraFunctionalPrototype extends Vertex implements IExtraFunctional {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ExtraFunctional);
  }
}
