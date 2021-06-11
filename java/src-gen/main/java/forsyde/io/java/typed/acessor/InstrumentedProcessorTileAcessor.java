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
      return Optional.of((java.lang.Integer) vertex.properties.get("min_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("max_frequency_hz")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getMaxClockCyclesPerOp(Vertex vertex) {
    if (vertex.properties.containsKey("max_clock_cycles_per_op")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>) vertex.properties.get("max_clock_cycles_per_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxMemoryInternalBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_internal_bytes")) {
      return Optional.of((java.lang.Integer) vertex.properties.get("max_memory_internal_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getRequires(Vertex vertex) {
    if (vertex.properties.containsKey("requires")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("requires"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getProvides(Vertex vertex) {
    if (vertex.properties.containsKey("provides")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("provides"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<List<String>> getConfigurations(Vertex vertex) {
    if (vertex.properties.containsKey("configurations")) {
      return Optional.of((java.util.List<java.lang.String>) vertex.properties.get("configurations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Boolean> getCanBeExplored(Vertex vertex) {
    if (vertex.properties.containsKey("can_be_explored")) {
      return Optional.of((java.lang.Boolean) vertex.properties.get("can_be_explored"));
    } else {
      return Optional.empty();
    }
  }
}
