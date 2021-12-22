package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.*;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

public interface ForSyDe2AmaltheaMappingAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromEdgesToMappings(ForSyDeSystemGraph model, Amalthea amalthea) {
        amalthea.setMappingModel(AmaltheaFactory.eINSTANCE.createMappingModel());
//        fromVertexesToModules(model, target, cache);
//        fromVertexesToStructures(model, target, cache);
//        fromEdgesToConnections(model, target, cache);
        for (EdgeInfo e : model.edgeSet()) {
            final Vertex sourceV = model.getEdgeSource(e);
            final Vertex targetV = model.getEdgeTarget(e);
            if (e.edgeTraits.contains(EdgeTrait.AbstractAllocation)) {
                fromAllocationToMapping(model, amalthea, e);
            } else if (e.edgeTraits.contains(EdgeTrait.AbstractScheduling)) {
                // try to make scheduling between task adn task scheduler
                //Optional.ofNullable(cache.get(e.getSource()))
                equivalents(sourceV)
                .filter(v -> v instanceof Task)
                .map(v -> (Task) v).forEach(task -> {
                    equivalent(targetV)
                    .filter(v -> v instanceof OperatingSystem)
                    .map(v -> (OperatingSystem) v).stream()
                    .flatMap(v -> v.getTaskSchedulers().stream())
                    .forEach(taskScheduler -> {
                        final TaskAllocation taskAllocation = AmaltheaFactory.eINSTANCE.createTaskAllocation();
                        taskAllocation.setTask(task);
                        taskAllocation.setScheduler(taskScheduler);
                        amalthea.getMappingModel().getTaskAllocation().add(taskAllocation);
                    });
                });
                // now the other direction
                equivalents(targetV)
                .filter(v -> v instanceof Task)
                .map(v -> (Task) v).forEach(task -> {
                    equivalent(sourceV)
                    .filter(v -> v instanceof OperatingSystem)
                    .map(v -> (OperatingSystem) v).stream()
                    .flatMap(v -> v.getTaskSchedulers().stream())
                    .forEach(taskScheduler -> {
                        final TaskAllocation taskAllocation = AmaltheaFactory.eINSTANCE.createTaskAllocation();
                        taskAllocation.setTask(task);
                        taskAllocation.setScheduler(taskScheduler);
                        amalthea.getMappingModel().getTaskAllocation().add(taskAllocation);
                    });
                });

            } else if (e.edgeTraits.contains(EdgeTrait.AbstractMapping)) {
                // try to make mapping between elements and memory
                equivalents(sourceV)
                        .filter(v -> v instanceof AbstractMemoryElement)
                        .map(v -> (AbstractMemoryElement) v).forEach(elem -> {
                            equivalents(targetV)
                                    .filter(v -> v instanceof Memory)
                                    .map(v -> (Memory) v)
                                    .forEach(memory -> {
                                        final MemoryMapping memoryMapping = AmaltheaFactory.eINSTANCE.createMemoryMapping();
                                        memoryMapping.setMemory(memory);
                                        memoryMapping.setAbstractElement(elem);
                                        amalthea.getMappingModel().getMemoryMapping().add(memoryMapping);
                                    });
                        });
                // now the other direction
                equivalents(targetV)
                        .filter(v -> v instanceof AbstractMemoryElement)
                        .map(v -> (AbstractMemoryElement) v).forEach(elem -> {
                            equivalents(sourceV)
                                    .filter(v -> v instanceof Memory)
                                    .map(v -> (Memory) v)
                                    .forEach(memory -> {
                                        final MemoryMapping memoryMapping = AmaltheaFactory.eINSTANCE.createMemoryMapping();
                                        memoryMapping.setMemory(memory);
                                        memoryMapping.setAbstractElement(elem);
                                        amalthea.getMappingModel().getMemoryMapping().add(memoryMapping);
                                    });
                        });
            }
        }
    }

    default void fromAllocationToMapping(ForSyDeSystemGraph model, Amalthea amalthea, EdgeInfo e) {
        final Vertex source = model.getEdgeSource(e);
        final Vertex target = model.getEdgeTarget(e);
        if (FixedPriorityScheduler.conforms(source) || RoundRobinScheduler.conforms(source) ||
                TimeTriggeredScheduler.conforms(source) && GenericProcessingModule.conforms(target)) {
            equivalent(source).map(elem -> (OperatingSystem) elem).stream().flatMap(os -> os.getTaskSchedulers().stream())
                .forEach(taskScheduler -> {
                    equivalent(target).map(elem -> (ProcessingUnit) elem).ifPresent(targetPu -> {
                        final SchedulerAllocation schedulerAllocation = AmaltheaFactory.eINSTANCE.createSchedulerAllocation();
                        schedulerAllocation.setScheduler(taskScheduler);
                        schedulerAllocation.setExecutingPU(targetPu);
                        schedulerAllocation.getResponsibility().add(targetPu);
                        amalthea.getMappingModel().getSchedulerAllocation().add(schedulerAllocation);
                    });
                });
        }
        // opposite direction
        if (FixedPriorityScheduler.conforms(target) || RoundRobinScheduler.conforms(target) ||
                TimeTriggeredScheduler.conforms(target) && GenericProcessingModule.conforms(source)) {
            equivalent(target).map(elem -> (OperatingSystem) elem).stream().flatMap(os -> os.getTaskSchedulers().stream())
                    .forEach(taskScheduler -> {
                        equivalent(source).map(elem -> (ProcessingUnit) elem).ifPresent(targetPu -> {
                            final SchedulerAllocation schedulerAllocation = AmaltheaFactory.eINSTANCE.createSchedulerAllocation();
                            schedulerAllocation.setScheduler(taskScheduler);
                            schedulerAllocation.setExecutingPU(targetPu);
                            schedulerAllocation.getResponsibility().add(targetPu);
                            amalthea.getMappingModel().getSchedulerAllocation().add(schedulerAllocation);
                        });
                    });
        }
    }

}