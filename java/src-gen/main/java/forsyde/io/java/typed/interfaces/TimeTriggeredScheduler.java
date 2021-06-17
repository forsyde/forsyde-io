package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface TimeTriggeredScheduler extends VertexInterface, AbstractGrouping {
  default Map<Integer, String> getTriggerTime() {
    return (java.util.Map<java.lang.Integer, java.lang.String>) getProperties().get("trigger_time").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.TimeTriggeredScheduler);
  }
}
