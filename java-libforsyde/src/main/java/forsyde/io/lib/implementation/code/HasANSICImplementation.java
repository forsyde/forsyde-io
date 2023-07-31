package forsyde.io.lib.implementation.code;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RegisterTrait(IForSyDeHierarchy.class)
public interface HasANSICImplementation extends VertexViewer {

    @Property
    String inlinedCode();

    @Property
    default List<String> inputArgumentPorts() {return new ArrayList<>();}

    @Property
    default List<String> outputArgumentPorts() {return new ArrayList<>();}

    @Property
    Optional<String> returnPort();
}
