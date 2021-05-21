package forsyde.io.java.core;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class VertexPropertyAcessor {
  public static Optional<Integer> getTime(Vertex vertex) {
    if (vertex.properties.containsKey("time")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("time"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getAprioriImportance(Vertex vertex) {
    if (vertex.properties.containsKey("apriori_importance")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("apriori_importance"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<Integer, String>> getTriggerTime(Vertex vertex) {
    if (vertex.properties.containsKey("trigger_time")) {
      return Optional.of((java.util.Map<java.lang.Integer, java.lang.String>)vertex.properties.get("trigger_time"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getMaxOperations(Vertex vertex) {
    if (vertex.properties.containsKey("max_operations")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>)vertex.properties.get("max_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxFloatOperations(Vertex vertex) {
    if (vertex.properties.containsKey("max_float_operations")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_float_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxIntOperations(Vertex vertex) {
    if (vertex.properties.containsKey("max_int_operations")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_int_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxBooleanOperations(Vertex vertex) {
    if (vertex.properties.containsKey("max_boolean_operations")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_boolean_operations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxMemorySizeInBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_size_in_bytes")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_memory_size_in_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getRequires(Vertex vertex) {
    if (vertex.properties.containsKey("requires")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>)vertex.properties.get("requires"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getProvides(Vertex vertex) {
    if (vertex.properties.containsKey("provides")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>)vertex.properties.get("provides"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<List<String>> getConfigurations(Vertex vertex) {
    if (vertex.properties.containsKey("configurations")) {
      return Optional.of((java.util.List<java.lang.String>)vertex.properties.get("configurations"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxElemSizeBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_size_bytes")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_elem_size_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxElemCount(Vertex vertex) {
    if (vertex.properties.containsKey("max_elem_count")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_elem_count"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getConsumption(Vertex vertex) {
    if (vertex.properties.containsKey("consumption")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>)vertex.properties.get("consumption"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Integer>> getProduction(Vertex vertex) {
    if (vertex.properties.containsKey("production")) {
      return Optional.of((java.util.Map<java.lang.String, java.lang.Integer>)vertex.properties.get("production"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getPeriodNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_numerator_per_sec")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("period_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getPeriodDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("period_denominator_per_sec")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("period_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getOffsetNumeratorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_numerator_per_sec")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("offset_numerator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getOffsetDenominatorPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("offset_denominator_per_sec")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("offset_denominator_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Boolean> getCanBeExplored(Vertex vertex) {
    if (vertex.properties.containsKey("can_be_explored")) {
      return Optional.of((java.lang.Boolean)vertex.properties.get("can_be_explored"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMinFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("min_frequency_hz")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("min_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxFrequencyHz(Vertex vertex) {
    if (vertex.properties.containsKey("max_frequency_hz")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_frequency_hz"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Map<String, Map<String, Integer>>> getMaxClockCyclesPerOp(Vertex vertex) {
    if (vertex.properties.containsKey("max_clock_cycles_per_op")) {
      return Optional.of((java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>)vertex.properties.get("max_clock_cycles_per_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxMemoryInternalBytes(Vertex vertex) {
    if (vertex.properties.containsKey("max_memory_internal_bytes")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_memory_internal_bytes"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getClockCyclesPerFloatOp(Vertex vertex) {
    if (vertex.properties.containsKey("clock_cycles_per_float_op")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("clock_cycles_per_float_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getClockCyclesPerIntegerOp(Vertex vertex) {
    if (vertex.properties.containsKey("clock_cycles_per_integer_op")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("clock_cycles_per_integer_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getClockCyclesPerBooleanOp(Vertex vertex) {
    if (vertex.properties.containsKey("clock_cycles_per_boolean_op")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("clock_cycles_per_boolean_op"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getMaxBandwithBytesPerSec(Vertex vertex) {
    if (vertex.properties.containsKey("max_bandwith_bytes_per_sec")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("max_bandwith_bytes_per_sec"));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Integer> getSlots(Vertex vertex) {
    if (vertex.properties.containsKey("slots")) {
      return Optional.of((java.lang.Integer)vertex.properties.get("slots"));
    } else {
      return Optional.empty();
    }
  }
}
