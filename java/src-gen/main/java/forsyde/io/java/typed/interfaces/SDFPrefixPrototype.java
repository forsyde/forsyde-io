package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.Optional;

public interface SDFPrefixPrototype extends ForSyDeFunctionPrototype {
  Optional<Vertex> getPrefixerPort(ForSyDeModel model);
}
