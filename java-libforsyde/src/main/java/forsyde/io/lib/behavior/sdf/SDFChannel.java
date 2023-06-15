package forsyde.io.lib.behavior.sdf;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.InPort;
import forsyde.io.java.core.annotations.OutPort;
import forsyde.io.java.core.annotations.Property;
import forsyde.io.java.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFChannel extends VertexViewer {

    @Property
    default Integer numInitialTokens() {return 0;};
    @InPort
    SDFActor producer();

    @OutPort
    SDFActor consumer();

}
