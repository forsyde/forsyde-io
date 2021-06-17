package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IInstrumentedProcessorTile;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public final class InstrumentedProcessorTilePrototype extends Vertex implements IInstrumentedProcessorTile {
  public static Integer staticGetMinFrequencyHz(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("min_frequency_hz").unwrap();
  }

  public Integer getMinFrequencyHz() {
    return staticGetMinFrequencyHz(this);
  }

  public static Integer staticGetMaxFrequencyHz(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_frequency_hz").unwrap();
  }

  public Integer getMaxFrequencyHz() {
    return staticGetMaxFrequencyHz(this);
  }

  public static Map<String, Integer> staticGetMaxClockCyclesPerOp(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("max_clock_cycles_per_op").unwrap();
  }

  public Map<String, Integer> getMaxClockCyclesPerOp() {
    return staticGetMaxClockCyclesPerOp(this);
  }

  public static Integer staticGetMaxMemoryInternalBytes(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("max_memory_internal_bytes").unwrap();
  }

  public Integer getMaxMemoryInternalBytes() {
    return staticGetMaxMemoryInternalBytes(this);
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

  public Boolean getCanBeExplored() {
    return AbstractProcessingComponent.staticGetCanBeExplored(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.InstrumentedProcessorTile);
  }
}
