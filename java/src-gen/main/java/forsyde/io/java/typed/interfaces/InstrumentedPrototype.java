package forsyde.io.java.typed.interfaces;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

public interface InstrumentedPrototype {
  Map<String, Map<String, Integer>> getRequires();

  Map<String, Map<String, Integer>> getProvides();

  List<String> getConfigurations();
}
