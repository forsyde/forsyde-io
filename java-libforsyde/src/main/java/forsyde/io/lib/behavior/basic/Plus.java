package forsyde.io.lib.behavior.basic;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Set;
import java.util.stream.Collectors;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Plus extends BasicOperation {

    @InPort
    Set<VertexViewer> inputs();

    @OutPort
    Set<VertexViewer> outputs();

    default Set<BasicOperation> inputBasicOperations() {
        return inputs().stream().filter(op -> op instanceof BasicOperation).map(op -> (BasicOperation) op).collect(Collectors.toSet());
    }

    default Set<BasicOperation> outputBasicOperations() {
        return outputs().stream().filter(op -> op instanceof BasicOperation).map(op -> (BasicOperation) op).collect(Collectors.toSet());
    }

}