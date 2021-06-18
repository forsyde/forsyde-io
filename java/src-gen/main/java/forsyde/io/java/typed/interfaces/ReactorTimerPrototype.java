package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.lang.Integer;
import java.util.Set;

public interface ReactorTimerPrototype extends ReactorElementPrototype {
  Integer getPeriodNumeratorPerSec();

  Integer getPeriodDenominatorPerSec();

  Integer getOffsetNumeratorPerSec();

  Integer getOffsetDenominatorPerSec();

  Set<ReactorElementPrototype> getOutputPort(ForSyDeModel model);
}
