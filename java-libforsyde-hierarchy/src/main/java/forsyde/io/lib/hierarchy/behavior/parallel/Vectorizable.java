package forsyde.io.lib.hierarchy.behavior.parallel;

import forsyde.io.core.annotations.*;
import forsyde.io.lib.hierarchy.behavior.BehaviourCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.DataLikeCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A Vectorizable represents an array of vector of certain data.
 * Sometimes we might find in the literature (or functional programming languages) a vector
 * of functions or routines.
 * This is not what this trait is about.
 * This trait is concerned only with encapsulating parallelizable data; whatever they might be.
 * The underlying model with system graphs should already be enough to have many parallel processes
 * side-by-side without expressing them as a parametrizable range.
 *
 * If you DO NEED such parametrization, create a script in your favorite support library that will
 * generate the appropriate number of parallel processes.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface Vectorizable extends DataTypeLike {

    @Property
    default List<Integer> dimensions() {return new ArrayList<>();}

    @InPort
    @WithEdgeTrait(ParallelComputationEdge.class)
    ParallelSkeleton producer();

    @OutPort
    @WithEdgeTrait(ParallelComputationEdge.class)
    Set<ParallelSkeleton> consumers();

    @OutPort
    @WithEdgeTrait(DataLikeCompositionEdge.class)
    Optional<DataTypeLike> arrayItemType();

}
