package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ITimeTriggeredScheduler;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public final class TimeTriggeredSchedulerPrototype extends Vertex implements ITimeTriggeredScheduler {
  public static Map<Integer, String> staticGetTriggerTime(Vertex vertex) {
    return (java.util.Map<java.lang.Integer, java.lang.String>) vertex.properties.get("trigger_time").unwrap();
  }

  public Map<Integer, String> getTriggerTime() {
    return staticGetTriggerTime(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.TimeTriggeredScheduler);
  }
}
