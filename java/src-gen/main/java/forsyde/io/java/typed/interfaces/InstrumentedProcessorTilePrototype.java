package forsyde.io.java.typed.interfaces;

import java.lang.Integer;
import java.lang.String;
import java.util.Map;

public interface InstrumentedProcessorTilePrototype extends AbstractProcessingComponentPrototype, InstrumentedPrototype {
  Integer getMinFrequencyHz();

  Integer getMaxFrequencyHz();

  Map<String, Integer> getMaxClockCyclesPerOp();

  Integer getMaxMemoryInternalBytes();
}
