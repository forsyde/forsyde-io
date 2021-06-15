package forsyde.io.java.typed.acessor;

import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public abstract class TimeTriggeredSchedulerAcessor {
  public static Optional<Map<Integer, String>> getTriggerTime(Vertex vertex) {
    if (vertex.properties.containsKey("trigger_time")) {
      return Optional.of((java.util.Map<java.lang.Integer, java.lang.String>) vertex.properties.get("trigger_time").unwrap());
    } else {
      return Optional.empty();
    }
  }
}
