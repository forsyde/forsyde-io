package forsyde.io.lib.visualization;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface Visualizable extends VertexViewer {
}
