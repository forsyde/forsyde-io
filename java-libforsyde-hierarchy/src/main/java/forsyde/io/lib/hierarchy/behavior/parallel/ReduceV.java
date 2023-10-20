package forsyde.io.lib.hierarchy.behavior.parallel;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.behavior.BehaviourCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.List;
import java.util.Set;

/**
 * A ReduceV skeleton specifies that the output vector can be generated by aggregating
 * the first dimension of the input vector. Therefore, this skeleton is not really parallel,
 * unless the aggregation function is known to be associative.
 *
 * Like this: let v = [v[1], v[2], v[3], ..., v[n]] be the input vector and o be one "scalar";
 * then a ReduceV with kernels f1, f2, ..., fm guarantess that:
 *
 * f = (fm ... f3 . f2 . f1)
 * o = f(v[n], f(v[n-1], ...f(v[2], v[1])...))
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface ReduceV extends ParallelSkeleton {

    @Property
    String inputArray();

    @Property
    String outputScalar();

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Set<FunctionLikeEntity> kernels();

    @Override
    default List<String> outputPorts() {
        return List.of(outputScalar());
    };

    @Override
    default List<String> inputPorts() {
        return List.of(inputArray());
    };
}