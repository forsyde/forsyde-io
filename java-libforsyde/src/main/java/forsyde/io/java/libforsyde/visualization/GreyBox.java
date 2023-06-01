package forsyde.io.java.libforsyde.visualization;

import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.OutPort;
import forsyde.io.java.core.annotations.WithEdgeTrait;

import java.util.Set;

@GenerateViewer
public interface GreyBox {

    @OutPort
    @WithEdgeTrait(VisualContainment.class)
    Set<Visualizable> contained();
}
