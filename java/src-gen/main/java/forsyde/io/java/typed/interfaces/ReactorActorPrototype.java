package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import java.util.List;
import java.util.Optional;

public interface ReactorActorPrototype extends ReactorElementPrototype, ForSyDeFunctionPrototype {
  List<Vertex> getTriggersPort(ForSyDeModel model);

  Optional<Vertex> getReactionImplementationPort(ForSyDeModel model);
}
