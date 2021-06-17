package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public interface Instrumented extends VertexInterface {
  default Map<String, Map<String, Integer>> getRequires() {
    return (java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) getProperties().get("requires").unwrap();
  }

  default Map<String, Map<String, Integer>> getProvides() {
    return (java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) getProperties().get("provides").unwrap();
  }

  default List<String> getConfigurations() {
    return (java.util.List<java.lang.String>) getProperties().get("configurations").unwrap();
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.Instrumented);
  }
}
