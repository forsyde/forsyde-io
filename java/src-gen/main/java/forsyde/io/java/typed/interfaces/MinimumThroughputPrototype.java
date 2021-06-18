package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.lang.Integer;
import java.util.Set;

public interface MinimumThroughputPrototype extends GoalPrototype {
  Integer getAprioriImportance();

  Set<ForSyDeFunctionPrototype> getApplicationPort(ForSyDeModel model);
}
