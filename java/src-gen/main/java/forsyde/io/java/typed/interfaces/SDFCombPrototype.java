package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.ForSyDeModel;
import java.lang.Integer;
import java.lang.String;
import java.util.Map;
import java.util.Optional;

public interface SDFCombPrototype extends ForSyDeFunctionPrototype {
  Map<String, Integer> getConsumption();

  Map<String, Integer> getProduction();

  Optional<ForSyDeFunctionPrototype> getCombinatorPort(ForSyDeModel model);
}
