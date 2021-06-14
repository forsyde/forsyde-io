package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.BooleanVertexProperty;
import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedProcessorTileAcessor {
  public static Optional<NumberVertexProperty> getMinFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("min_frequency_hz")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("min_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getMaxFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("max_frequency_hz")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<StringVertexProperty, NumberVertexProperty>> getMaxClockCyclesPerOp(
      Vertex vertex) {
    if (vertex.properties.containsKey("max_clock_cycles_per_op")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.StringVertexProperty, forsyde.io.java.core.NumberVertexProperty>) vertex.properties.get("max_clock_cycles_per_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<NumberVertexProperty> getMaxMemoryInternalBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_internal_bytes")) {
      return Optional.of((forsyde.io.java.core.NumberVertexProperty) vertex.properties.get("max_memory_internal_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getRequires(
      Vertex vertex) {
    return InstrumentedAcessor.getRequires(vertex);
  }

  public static Optional<Map<StringVertexProperty, Map<StringVertexProperty, NumberVertexProperty>>> getProvides(
      Vertex vertex) {
    return InstrumentedAcessor.getProvides(vertex);
  }

  public static Optional<List<StringVertexProperty>> getConfigurations(Vertex vertex) {
    return InstrumentedAcessor.getConfigurations(vertex);
  }

  public static Optional<BooleanVertexProperty> getCanBeExplored(Vertex vertex) {
    return AbstractProcessingComponentAcessor.getCanBeExplored(vertex);
  }
}
