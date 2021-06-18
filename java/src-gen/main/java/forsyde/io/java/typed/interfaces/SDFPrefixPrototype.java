package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.util.Optional;

public interface SDFPrefixPrototype extends ForSyDeFunctionPrototype {
  Optional<ForSyDeFunctionPrototype> getPrefixerPort(ForSyDeModel model);
}
