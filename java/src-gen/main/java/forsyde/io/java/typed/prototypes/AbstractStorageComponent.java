package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractStorageComponentPrototype;
import java.lang.Boolean;

public final class AbstractStorageComponent extends Vertex implements AbstractStorageComponentPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractStorageComponentTrait);
  }
}
