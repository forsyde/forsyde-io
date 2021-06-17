package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IAbstractStorageComponent;
import java.lang.Boolean;

public final class AbstractStorageComponentPrototype extends Vertex implements IAbstractStorageComponent {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractStorageComponent);
  }
}
