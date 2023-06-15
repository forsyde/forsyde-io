package forsyde.io.lib.visualization;

import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Visualizable extends VertexViewer {
}
