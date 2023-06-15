package forsyde.io.lib.behavior.sdf;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.InPort;
import forsyde.io.java.core.annotations.OutPort;

//@RegisterTrait(ForSyDeHierarchy.class)
public interface SDFChannel extends VertexViewer {

    Integer numInitialTokens();
    @InPort
    SDFActor producer();

    @OutPort
    SDFActor consumer();
}
