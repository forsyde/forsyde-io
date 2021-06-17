package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IBufferSignal;
import java.lang.Boolean;

public final class BufferSignalPrototype extends Vertex implements IBufferSignal {
  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.BufferSignal);
  }
}
