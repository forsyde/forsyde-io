package forsyde.io.java.typed.interfaces;

import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface TimeTriggeredSchedulerPrototype extends AbstractGroupingPrototype {
  Map<Integer, String> getTriggerTime();
}
