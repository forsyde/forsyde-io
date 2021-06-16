package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.Set;

public interface WCCTPrototype extends ExtraFunctionalPrototype {
  Integer getTime();

  Set<Vertex> getSignalPort(ForSyDeModel model);

  Set<Vertex> getPlatformElementPort(ForSyDeModel model);
}
