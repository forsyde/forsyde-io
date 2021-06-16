package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ReactorElementPrototype;
import java.lang.Boolean;

public final class ReactorElement extends Vertex implements ReactorElementPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ReactorElementTrait);
  }
}
