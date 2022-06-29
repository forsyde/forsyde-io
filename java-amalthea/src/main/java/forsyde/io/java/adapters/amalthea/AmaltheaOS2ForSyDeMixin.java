package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.decision.Allocated;
import forsyde.io.java.typed.viewers.platform.GenericProcessingModule;
import forsyde.io.java.typed.viewers.platform.runtime.FixedPriorityScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.StaticCyclicScheduler;
import forsyde.io.java.typed.viewers.visualization.GreyBox;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AmaltheaOS2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromOSToForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getOsModel() != null) {
            fromOSModelToBinding(amalthea, forSyDeSystemGraph);
        }
    }

    default void fromOSModelToBinding(Amalthea amalthea, ForSyDeSystemGraph model) {
        for (OperatingSystem os: amalthea.getOsModel().getOperatingSystems()) {
            for(TaskScheduler taskScheduler : os.getTaskSchedulers()) {
                final Vertex runtimeVertex = new Vertex(os.getName() + "." + taskScheduler.getName(), VertexTrait.PLATFORM_PLATFORMELEM);
                final Visualizable runtimeVisualizable = Visualizable.enforce(runtimeVertex);
                // this is very messy, but it is required as app4mc encapsualted everything
                // delightfully in a Value type that apparently does not play nice with Java types.
                final Boolean isPreemptive = taskScheduler.getSchedulingParameters()
                        .stream().anyMatch(p ->
                                p.getKey() != null && p.getKey().getName().equals("preemptive") &&
                                p.getValue() != null && p.getValue() instanceof BooleanObject && ((BooleanObject) p.getValue()).isValue() ||
                                (p.getKey().getDefaultValue() != null && p.getKey().getDefaultValue() instanceof BooleanObject &&
                                        ((BooleanObject) p.getKey().getDefaultValue()).isValue()));
                // simply check if any parameter is named 'priority'.
                final Boolean hasPriority = taskScheduler.getDefinition().getProcessParameters()
                        .stream().anyMatch(p ->
                                p.getName().equals("priority"));
                final Boolean isNamedStaticCyclic = taskScheduler.getDefinition().getName().contains("StaticCyclic");
                final Boolean isNamedFixedPriority = taskScheduler.getDefinition().getName().contains("FixedPriority");
                if (isNamedFixedPriority && hasPriority) {
                    final FixedPriorityScheduler fixedPriorityScheduler = FixedPriorityScheduler.enforce(runtimeVertex);
                    fixedPriorityScheduler.setPreemptive(isPreemptive);
                } else if (isNamedStaticCyclic) {
                    final StaticCyclicScheduler staticCyclicScheduler = StaticCyclicScheduler.enforce(runtimeVertex);
                }
                addEquivalence(os, runtimeVertex);
                addEquivalence(taskScheduler, runtimeVertex);
                model.addVertex(runtimeVertex);
                for (SchedulerAllocation allocation : amalthea.getMappingModel().getSchedulerAllocation()) {
                    if (allocation.getScheduler().equals(taskScheduler)) {
                        equivalent(allocation.getExecutingPU()).ifPresent(puVertex -> {
                            GenericProcessingModule.safeCast(puVertex).ifPresent(pu -> {
                                final Allocated allocated = Allocated.enforce(runtimeVertex);
                                allocated.setAllocationHostsPort(model, Set.of(pu));
                                GreyBox.enforce(puVertex);
                                model.connect(puVertex, runtimeVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                            });
                        });
                    }
                }
            }
        }
    }
}
