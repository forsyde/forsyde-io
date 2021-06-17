package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IInstrumentedSignal;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public final class InstrumentedSignalPrototype extends Vertex implements IInstrumentedSignal {
  public static Integer staticGetMaxElemSizeBytes(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_elem_size_bytes").unwrap();
  }

  public Integer getMaxElemSizeBytes() {
    return staticGetMaxElemSizeBytes(this);
  }

  public static Integer staticGetMaxElemCount(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_elem_count").unwrap();
  }

  public Integer getMaxElemCount() {
    return staticGetMaxElemCount(this);
  }

  public Map<String, Map<String, Integer>> getRequires() {
    return Instrumented.staticGetRequires(this);
  }

  public Map<String, Map<String, Integer>> getProvides() {
    return Instrumented.staticGetProvides(this);
  }

  public List<String> getConfigurations() {
    return Instrumented.staticGetConfigurations(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.InstrumentedSignal);
  }
}
