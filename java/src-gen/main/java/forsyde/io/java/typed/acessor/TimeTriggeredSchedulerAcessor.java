package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.NumberVertexProperty;
import forsyde.io.java.core.StringVertexProperty;
import forsyde.io.java.core.Vertex;
import java.util.Map;
import java.util.Optional;

public abstract class TimeTriggeredSchedulerAcessor {
  public static Optional<Map<NumberVertexProperty, StringVertexProperty>> getTriggerTime(
      Vertex vertex) {
    if (vertex.properties.containsKey("trigger_time")) {
      return Optional.of((java.util.Map<forsyde.io.java.core.NumberVertexProperty, forsyde.io.java.core.StringVertexProperty>) vertex.properties.get("trigger_time"));
    } else {
      return Optional.empty();
    }
  }
}
