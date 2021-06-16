package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.Set;

public interface LocationRequirementPrototype extends RequirementPrototype {
  Set<Vertex> getProcessPort(ForSyDeModel model);

  Set<Vertex> getProcessingUnitPort(ForSyDeModel model);
}
