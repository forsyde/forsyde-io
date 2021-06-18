package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface TimeTriggeredScheduler extends VertexInterface, AbstractGrouping {
  default String getTriggerTime() {
    return (String) getProperties().get("trigger_time").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.TimeTriggeredScheduler);
  }

  static Optional<TimeTriggeredScheduler> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((TimeTriggeredScheduler) vertex) : Optional.empty();
  }
}
