package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.LabelSignalPrototype;
import java.lang.Boolean;

public final class LabelSignal extends Vertex implements LabelSignalPrototype {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.LabelSignalTrait);
  }
}
