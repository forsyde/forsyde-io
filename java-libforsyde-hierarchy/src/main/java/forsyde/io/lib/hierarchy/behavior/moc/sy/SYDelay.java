package forsyde.io.lib.hierarchy.behavior.moc.sy;

import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.List;

/**
 * An SY Delay is an SY process creates a signal with one discrete step phaing.
 * Or better saying, for every discrete step, the created signal by SY Delay outputs the value of its inputs
 * signal from the previous discrete step.
 *
 * In equational form, consider that we have a SYDelay F connecting `input` to `output`.
 * The output at synchronous every step k is:
 *
 * output(k) = input(k-1)
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface SYDelay extends SYProcess {

    @InPort
    @WithEdgeTrait(SYNetworkEdge.class)
    SYSignal input();

    @OutPort
    @WithEdgeTrait(SYNetworkEdge.class)
    SYSignal delayed();

    @Override
    default List<String> outputPorts() {
        return List.of("delayed");
    };

    @Override
    default List<String> inputPorts() {
        return List.of("input");
    };

    /**
     * @return the delayed signal
     */
    default SYSignal output() {
        return delayed();
    };
}
