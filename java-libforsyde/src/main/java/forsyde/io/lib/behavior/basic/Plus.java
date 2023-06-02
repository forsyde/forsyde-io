package forsyde.io.lib.behavior.basic;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.InPort;
import forsyde.io.java.core.annotations.OutPort;
import forsyde.io.java.core.annotations.RegisterTrait;

import java.util.Set;
import java.util.stream.Collectors;

@GenerateViewer
@RegisterTrait(hierarchy = "LibForSyDe")
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