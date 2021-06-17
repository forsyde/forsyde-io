package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IAbstractCommunicationComponent;
import java.lang.Boolean;

public final class AbstractCommunicationComponentPrototype extends Vertex implements IAbstractCommunicationComponent {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractCommunicationComponent);
  }
}
