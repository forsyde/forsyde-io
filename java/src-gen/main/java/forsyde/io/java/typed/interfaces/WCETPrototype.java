package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.lang.Integer;
import java.util.Set;

public interface WCETPrototype extends ExtraFunctionalPrototype {
  Integer getTime();

  Set<ForSyDeFunctionPrototype> getApplicationPort(ForSyDeModel model);

  Set<AbstractProcessingComponentPrototype> getPlatformPort(ForSyDeModel model);
}
