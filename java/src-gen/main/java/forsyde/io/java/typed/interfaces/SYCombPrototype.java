package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.util.Optional;

public interface SYCombPrototype extends ForSyDeFunctionPrototype {
  Optional<ForSyDeFunctionPrototype> getCombinatorPort(ForSyDeModel model);
}
