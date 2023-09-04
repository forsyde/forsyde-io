package forsyde.io.lib.behavior.parallel;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.OpaqueTrait;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.BehaviourEntity;
import forsyde.io.lib.behavior.BehaviourCompositionEdge;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * A MapV skeleton specifies that all the entries of the output vector can be computed
 * independently from each other, using each of the input vector entries.
 *
 * Like this: let v = [v[1], v[2], v[3], ..., v[n]] be the input vector and o be an equivalent output vector;
 * then a MapV with kernels f1, f2, ..., fm guarantess that:
 *
 * o[i] = fm(...f3(f2(f1(v[i])))...)
 *
 * or,
 *
 * o[i] = (fm ... f3 . f2 . f1) (v[i])
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MapV extends ParallelSkeleton {

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Set<BehaviourEntity> kernels();

    @Property
    List<String> outputPorts();

    @Property
    List<String> inputPorts();

    /**
     * Convenience method to connect a new vectorizable input.
     */
    default MapV addInput(String portName, Vectorizable vectorizable, EdgeTrait... extraTraits) {
        inputPorts().add(portName);
        getViewedSystemGraph().connect(vectorizable.getViewedVertex(), getViewedVertex(), "consumers", portName, new OpaqueTrait("forsyde::io::lib::behavior::parallel::ParallelComputationEdge"));
        if (extraTraits.length > 0) {
            getViewedSystemGraph().connect(vectorizable.getViewedVertex(), getViewedVertex(), "consumers", portName, extraTraits);
        }
        return this;
    }

    /**
     * Convenience method to connect a new vectorizable output.
     *
     * Be careful: a vectorizable admits onle ONE producer. If you by accident connect the output of more than two algorithmic
     * skeletons to the same vectorizable element, the model is considered ambiguous.
     */
    default MapV addOutput(String portName, Vectorizable vectorizable, EdgeTrait... extraTraits) {
        outputPorts().add(portName);
        getViewedSystemGraph().connect(getViewedVertex(), vectorizable.getViewedVertex(), "producer", portName, new OpaqueTrait("forsyde::io::lib::behavior::parallel::ParallelComputationEdge"));
        if (extraTraits.length > 0) {
            getViewedSystemGraph().connect(getViewedVertex(), vectorizable.getViewedVertex(), "producer", portName, extraTraits);
        }
        return this;
    }

    /**
     * Convenience method for the vectorizable outputs.
     * This list is immutable, if you wish to add another output port,
     * do so with:
     *
     * mapv.addOutput(portName, vectorizableElement)
     */
//    default Map<String, Vectorizable> outputs() {
//        return getViewedSystemGraph().outgoingEdgesOf(getViewedVertex()).stream()
//                .filter(e -> e.getSourcePort().map(x -> outputPorts().contains(x)).orElse(false))
//                .filter(e -> getViewedSystemGraph().getEdgeTarget(e).hasTrait(new OpaqueTrait("forsyde::io::lib::behavior::parallel::Vectorizable")))
//                .collect(Collectors.toMap(
//                        e -> e.getSourcePort().get(),
//                        e -> ForSyDeHierarchy.Vectorizable.tryView(getViewedSystemGraph(), getViewedSystemGraph().getEdgeTarget(e)).get()
//                ));
//    }

    /**
     * Convenience method for the vectorizable inputs.
     * This list is immutable, if you wish to add another input port,
     * do so with:
     *
     * mapv.addInput(portName, vectorizableElement)
     */
//    default Map<String, Vectorizable> inputs() {
//        return getViewedSystemGraph().incomingEdgesOf(getViewedVertex()).stream()
//                .filter(e -> e.getSourcePort().map(x -> inputPorts().contains(x)).orElse(false))
//                .filter(e -> ForSyDeHierarchy.Vectorizable.tryView(getViewedSystemGraph(), getViewedSystemGraph().getEdgeSource(e)).isPresent())
//                .collect(Collectors.toMap(
//                        e -> e.getSourcePort().get(),
//                        e -> ForSyDeHierarchy.Vectorizable.tryView(getViewedSystemGraph(), getViewedSystemGraph().getEdgeSource(e)).get()
//                ));
//    }

}
