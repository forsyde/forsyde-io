package forsyde.io.lib.hierarchy.visualization;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.List;

@RegisterTrait(IForSyDeHierarchy.class)
public interface VisualizableWithProperties extends Visualizable {

    @Property
    List<String> visualizedPropertiesNames();
}
