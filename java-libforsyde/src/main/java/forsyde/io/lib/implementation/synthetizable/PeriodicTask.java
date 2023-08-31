package forsyde.io.lib.implementation.synthetizable;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.execution.PeriodicStimulator;
import forsyde.io.lib.behavior.execution.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This simple refinement stores the amount of simply periodic
 * sub tasks that a task can generate given its incoming stimulators.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface PeriodicTask extends Task {

    @InPort
    Set<PeriodicStimulator> incomingStimulators();

    @Property
    default List<Long> periodNumerators() {
        if (hasORSemantics()) {
            return incomingStimulators().stream().map(PeriodicStimulator::periodNumerator).collect(Collectors.toList());
        } else {
            var l = new ArrayList<Long>();
            l.add(incomingStimulators().stream().mapToLong(PeriodicStimulator::periodNumerator).min().orElse(0));
            return l;
        }
    };

    @Property
    default List<Long> periodDenominators() {
        if (hasORSemantics()) {
            return incomingStimulators().stream().map(PeriodicStimulator::periodDenominator).collect(Collectors.toList());
        } else {
            var l = new ArrayList<Long>();
            l.add(incomingStimulators().stream().mapToLong(PeriodicStimulator::periodNumerator).min().orElse(0));
            return l;
        }
    };

    @Property
    default List<Long> offsetNumerators() {
        if (hasORSemantics()) {
            return incomingStimulators().stream().map(PeriodicStimulator::offsetNumerator).collect(Collectors.toList());
        } else {
            var l = new ArrayList<Long>();
            l.add(incomingStimulators().stream().mapToLong(PeriodicStimulator::periodNumerator).min().orElse(0));
            return l;
        }
    };

    @Property
    default List<Long> offsetDenominators() {
        if (hasORSemantics()) {
            return incomingStimulators().stream().map(PeriodicStimulator::offsetDenominator).collect(Collectors.toList());
        } else {
            var l = new ArrayList<Long>();
            l.add(incomingStimulators().stream().mapToLong(PeriodicStimulator::periodNumerator).min().orElse(0));
            return l;
        }
    };

}
