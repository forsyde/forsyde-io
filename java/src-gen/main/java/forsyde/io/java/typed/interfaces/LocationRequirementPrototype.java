package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.util.Set;

public interface LocationRequirementPrototype extends RequirementPrototype {
  Set<ForSyDeFunctionPrototype> getForSyDeFunctionPort(ForSyDeModel model);

  Set<AbstractProcessingComponentPrototype> getProcessingUnitPort(ForSyDeModel model);
}
