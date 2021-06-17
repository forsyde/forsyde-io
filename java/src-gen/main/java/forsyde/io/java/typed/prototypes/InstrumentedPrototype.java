package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.IInstrumented;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public final class InstrumentedPrototype extends Vertex implements IInstrumented {
  public static Map<String, Map<String, Integer>> staticGetRequires(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("requires").unwrap();
  }

  public Map<String, Map<String, Integer>> getRequires() {
    return staticGetRequires(this);
  }

  public static Map<String, Map<String, Integer>> staticGetProvides(Vertex vertex) {
    return (java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Integer>>) vertex.properties.get("provides").unwrap();
  }

  public Map<String, Map<String, Integer>> getProvides() {
    return staticGetProvides(this);
  }

  public static List<String> staticGetConfigurations(Vertex vertex) {
    return (java.util.List<java.lang.String>) vertex.properties.get("configurations").unwrap();
  }

  public List<String> getConfigurations() {
    return staticGetConfigurations(this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.Instrumented);
  }
}
