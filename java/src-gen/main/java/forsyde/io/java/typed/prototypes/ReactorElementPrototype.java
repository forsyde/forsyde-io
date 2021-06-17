package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IReactorElement;
import java.lang.Boolean;

public final class ReactorElementPrototype extends Vertex implements IReactorElement {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ReactorElement);
  }
}
