package forsyde.io.lib.behavior.sdf;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.RegisterTrait;
import forsyde.io.lib.ForSyDeHierarchy;

import java.util.Map;

@RegisterTrait(ForSyDeHierarchy.class)
public interface SDFActor extends VertexViewer {

    Map<String, Integer> consumption();

    Map<String, Integer> production();

}
