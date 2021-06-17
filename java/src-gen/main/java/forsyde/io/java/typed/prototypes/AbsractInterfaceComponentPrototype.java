package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IAbsractInterfaceComponent;
import java.lang.Boolean;

public final class AbsractInterfaceComponentPrototype extends Vertex implements IAbsractInterfaceComponent {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbsractInterfaceComponent);
  }
}
