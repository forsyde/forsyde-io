package forsyde.io.java.typed.interfaces;

import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface InstrumentedFunctionPrototype extends ForSyDeFunctionPrototype {
  Map<String, Integer> getMaxOperations();

  Integer getMaxMemorySizeInBytes();
}
