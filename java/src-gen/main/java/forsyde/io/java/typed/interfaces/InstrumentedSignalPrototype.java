package forsyde.io.java.typed.interfaces;

import java.lang.Integer;

public interface InstrumentedSignalPrototype extends SignalPrototype, InstrumentedPrototype {
  Integer getMaxElemSizeBytes();

  Integer getMaxElemCount();
}
