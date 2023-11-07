package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

public class EnforceBasicDataTypeConstraintsInference implements SystemGraphInference {
    @Override
    public void infer(SystemGraph systemGraph) {
        for (var v :systemGraph.vertexSet()) {
            ForSyDeHierarchy.IEEE754Binary32FloatingPoint.tryView(systemGraph, v).ifPresent(fp -> fp.numberOfBits(32));
            ForSyDeHierarchy.IEEE754Binary64FloatingPoint.tryView(systemGraph, v).ifPresent(fp -> fp.numberOfBits(64));
        }
    }

    @Override
    public String getName() {
        return "EnforceBasicDataTypeConstraintsInference";
    }
}
