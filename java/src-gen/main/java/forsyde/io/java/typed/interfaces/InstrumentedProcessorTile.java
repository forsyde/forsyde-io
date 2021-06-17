package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface InstrumentedProcessorTile extends VertexInterface, AbstractProcessingComponent, Instrumented {
  default Integer getMinFrequencyHz() {
    return (java.lang.Integer) getProperties().get("min_frequency_hz").unwrap();
  }

  default Integer getMaxFrequencyHz() {
    return (java.lang.Integer) getProperties().get("max_frequency_hz").unwrap();
  }

  default Map<String, Integer> getMaxClockCyclesPerOp() {
    return (java.util.Map<java.lang.String, java.lang.Integer>) getProperties().get("max_clock_cycles_per_op").unwrap();
  }

  default Integer getMaxMemoryInternalBytes() {
    return (java.lang.Integer) getProperties().get("max_memory_internal_bytes").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedProcessorTile);
  }
}
