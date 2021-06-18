package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.Optional;

public interface Instrumented extends VertexInterface {
  default String getRequires() {
    return (String) getProperties().get("requires").unwrap();
  }

  default String getProvides() {
    return (String) getProperties().get("provides").unwrap();
  }

  default String getConfigurations() {
    return (String) getProperties().get("configurations").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.Instrumented);
  }

  static Optional<Instrumented> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((Instrumented) vertex) : Optional.empty();
  }
}
