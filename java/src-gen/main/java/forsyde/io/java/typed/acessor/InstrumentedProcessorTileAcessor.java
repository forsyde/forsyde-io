package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InstrumentedProcessorTileAcessor {
  public static Optional<Integer> getMinFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("min_frequency_hz")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("min_frequency_hz").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("max_frequency_hz")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_frequency_hz").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getMaxClockCyclesPerOp(Vertex vertex) {
    if (vertex.properties.containsKey("max_clock_cycles_per_op")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("max_clock_cycles_per_op").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxMemoryInternalBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_internal_bytes")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_memory_internal_bytes").unwrap());
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getRequires(Vertex vertex) {
    return InstrumentedAcessor.getRequires(vertex);
  }

  public static Optional<Map<String, Map<String, Integer>>> getProvides(Vertex vertex) {
    return InstrumentedAcessor.getProvides(vertex);
  }

  public static Optional<List<String>> getConfigurations(Vertex vertex) {
    return InstrumentedAcessor.getConfigurations(vertex);
  }

  public static Optional<Boolean> getCanBeExplored(Vertex vertex) {
    return AbstractProcessingComponentAcessor.getCanBeExplored(vertex);
  }
}
