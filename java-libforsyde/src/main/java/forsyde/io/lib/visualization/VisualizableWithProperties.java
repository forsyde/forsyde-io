package forsyde.io.lib.visualization;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.visualization.Visualizable;

import java.util.List;

@RegisterTrait(IForSyDeHierarchy.class)
public interface VisualizableWithProperties extends Visualizable {

    @Property
    List<String> visualizedPropertiesNames();
}
