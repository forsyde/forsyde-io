package forsyde.io.lib.behavior;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.List;

@RegisterTrait(IForSyDeHierarchy.class)
public interface FunctionLikeEntity extends VertexViewer {

    @Property
    List<String> outputPorts();

    @Property
    List<String> inputPorts();
}
