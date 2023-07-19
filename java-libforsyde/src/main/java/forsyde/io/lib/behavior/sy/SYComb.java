package forsyde.io.lib.behavior.sy;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.AlgorithmicEntity;
import forsyde.io.lib.behavior.MoCEntity;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An SY Combinator is an SY process that transforms an input signals into output signals.
 * Or better saying, for every discrete step, the SY Comb uses its defining functions to produce
 * output values from the input values in each step.
 *
 * In equational form, consider that we have a SYComb F with combinators f1, f2, f3, ..., fn
 * The output at synchronous every step k is:
 *
 * output(k) = fn(...f3(f2(f1(input(k)))...)
 *
 * or,
 *
 * output(k) = fn . . . . . f3 . f2 . f1 (input(k))
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SYComb extends SYProcess {

    @OutPort
    Set<AlgorithmicEntity> combinators();

    default List<AlgorithmicEntity> combinatorsOrdered() {
        if (getViewedVertex().hasProperty("__combinators_ordering__") && getViewedVertex().getProperty("__combinators_ordering__") instanceof List<?> list
        && combinators().stream().map(VertexViewer::getIdentifier).allMatch(list::contains)) {
            var ordered = new ArrayList<>(combinators());
            ordered.sort(Comparator.comparingInt(list::indexOf));
            return ordered;
        } else {
            var shortestPaths = new FloydWarshallShortestPaths<>(getViewedSystemGraph());
            var ordered = new ArrayList<>(combinators());
            ordered.sort((f1, f2) ->
                    (int) shortestPaths.getPathWeight(f1.getViewedVertex(), f2.getViewedVertex()) -
                            (int) shortestPaths.getPathWeight(f2.getViewedVertex(), f1.getViewedVertex()));
            getViewedVertex().putProperty("__combinators_orderings__", ordered.stream().map(VertexViewer::getIdentifier).collect(Collectors.toList()));
            return ordered;
        }
    }

}
