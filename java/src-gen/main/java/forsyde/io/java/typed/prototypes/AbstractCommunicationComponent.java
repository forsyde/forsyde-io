package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.AbstractCommunicationComponentPrototype;
import java.lang.Boolean;

public final class AbstractCommunicationComponent extends Vertex implements AbstractCommunicationComponentPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.AbstractCommunicationComponentTrait);
  }
}
