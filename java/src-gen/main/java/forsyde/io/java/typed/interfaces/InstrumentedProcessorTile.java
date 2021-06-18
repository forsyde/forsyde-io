package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface InstrumentedProcessorTile extends VertexInterface, AbstractProcessingComponent, Instrumented {
  default String getMinFrequencyHz() {
    return (String) getProperties().get("min_frequency_hz").unwrap();
  }

  default String getMaxFrequencyHz() {
    return (String) getProperties().get("max_frequency_hz").unwrap();
  }

  default String getMaxClockCyclesPerOp() {
    return (String) getProperties().get("max_clock_cycles_per_op").unwrap();
  }

  default String getMaxMemoryInternalBytes() {
    return (String) getProperties().get("max_memory_internal_bytes").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.InstrumentedProcessorTile);
  }

  static Optional<InstrumentedProcessorTile> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((InstrumentedProcessorTile) vertex) : Optional.empty();
  }
}
