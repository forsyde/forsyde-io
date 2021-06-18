package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.lang.Integer;
import java.util.Set;

public interface WCCTPrototype extends ExtraFunctionalPrototype {
  Integer getTime();

  Set<ForSyDeFunctionPrototype> getSignalPort(ForSyDeModel model);

  Set<AbstractCommunicationComponentPrototype> getPlatformElementPort(ForSyDeModel model);
}
