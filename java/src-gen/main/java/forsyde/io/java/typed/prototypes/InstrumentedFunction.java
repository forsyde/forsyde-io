package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.InstrumentedFunctionPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public final class InstrumentedFunction extends Vertex implements InstrumentedFunctionPrototype {
  public static Map<String, Integer> staticGetMaxOperations(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("max_operations").unwrap();
  }

  public Map<String, Integer> getMaxOperations() {
    return staticGetMaxOperations(this);
  }

  public static Integer staticGetMaxMemorySizeInBytes(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_memory_size_in_bytes").unwrap();
  }

  public Integer getMaxMemorySizeInBytes() {
    return staticGetMaxMemorySizeInBytes(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.InstrumentedFunctionTrait);
  }
}
