package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.lang.Integer;
import java.util.Set;

public interface WCETPrototype extends ExtraFunctionalPrototype {
  Integer getTime();

  Set<Vertex> getApplicationPort(ForSyDeModel model);

  Set<Vertex> getPlatformPort(ForSyDeModel model);
}
