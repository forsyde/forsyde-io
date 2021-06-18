package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.util.List;
import java.util.Optional;

public interface ReactorActorPrototype extends ReactorElementPrototype, ForSyDeFunctionPrototype {
  List<ReactorElementPrototype> getTriggersPort(ForSyDeModel model);

  Optional<ForSyDeFunctionPrototype> getReactionImplementationPort(ForSyDeModel model);
}
