package forsyde.io.lib.implementation.functional;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Map;

@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedOperation extends VertexViewer {

    @Property
    Map<String, Map<String, Long>> computationalRequirements();

    @Property
    Map<String, Long> maxSizeInBits();
}
