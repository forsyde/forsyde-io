package forsyde.io.lib.behavior.sdf;

import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.InPort;
import forsyde.io.java.core.annotations.OutPort;

@GenerateViewer
public interface SDFChannel {

    Integer numInitialTokens();
    @InPort
    SDFActor producer();

    @OutPort
    SDFActor consumer();
}
