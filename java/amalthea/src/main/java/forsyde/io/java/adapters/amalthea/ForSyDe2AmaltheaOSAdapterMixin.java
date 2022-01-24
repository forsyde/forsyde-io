package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.platform.runtime.FixedPriorityScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.RoundRobinScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.TimeTriggeredScheduler;
import org.eclipse.app4mc.amalthea.model.*;
import org.eclipse.app4mc.amalthea.model.predefined.StandardSchedulers;

import java.util.stream.IntStream;

public interface ForSyDe2AmaltheaOSAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromVertexesToOSModel(ForSyDeSystemGraph model, Amalthea target) {
        target.setOsModel(AmaltheaFactory.eINSTANCE.createOSModel());
//        fromVertexesToModules(model, target, cache);
//        fromVertexesToStructures(model, target, cache);
//        fromEdgesToConnections(model, target, cache);
        for (Vertex v : model.vertexSet()) {
            final String newId = v.getIdentifier().replace(".", "_");
            if (FixedPriorityScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                // try to get a scheduler def or create the standard one
                final SchedulerDefinition schedulerDefinition = getSchedulerDefinitionByName(target, "FixedPriorityPreemptive");
                // taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createPriorityBasedRoundRobin());
                taskScheduler.setDefinition(schedulerDefinition);
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (RoundRobinScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setName(newId + "_RRScheduler");
                final SchedulerDefinition schedulerDefinition = getSchedulerDefinitionByName(target, "PriorityBasedRoundRobin");
                taskScheduler.setDefinition(schedulerDefinition);
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (TimeTriggeredScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setName(newId + "_TTScheduler");
                final SchedulerDefinition schedulerDefinition = getTimeTriggeredSchedulerDefinition(target);
                taskScheduler.setDefinition(schedulerDefinition);
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            }
        }
    }

    default SchedulerDefinition getSchedulerDefinitionByName(Amalthea amalthea, String name) {
        return amalthea.getOsModel().getSchedulerDefinitions()
                .stream()
                .filter(scheDef -> scheDef.getName().equals(name))
                .findAny().orElseGet(() -> {
                    final SchedulerDefinition newDef = AmaltheaFactory.eINSTANCE.createSchedulerDefinition();
                    final StandardSchedulers.Algorithm algorithm = StandardSchedulers.getAlgorithm(name);
                    newDef.setName(algorithm.name());
                    newDef.setDescription(algorithm.getDescription());
                    // for some very clunky manual adding for algorithms parameters...
                    IntStream.range(0, Math.min(algorithm.getAlgorithmParameters().size(), algorithm.getAlgorithmParameterNames().size()))
                            .forEach(i -> {
                                final SchedulingParameterDefinition schedulingParameterDefinition =
                                        AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                                schedulingParameterDefinition.setName(algorithm.getAlgorithmParameterNames().get(i));
                                // this line below is insane... but apparently ParameterType and Type are
                                // completely differente elements in the type hirearchy built for app4mc.
                                schedulingParameterDefinition.setType(
                                        ParameterType.valueOf(algorithm.getAlgorithmParameters().get(i).getType().getTypeName())
                                );
                            });
                    // and now for the processes also.
                    IntStream.range(0, Math.min(algorithm.getProcessParameters().size(), algorithm.getProcessParameterNames().size()))
                            .forEach(i -> {
                                final SchedulingParameterDefinition schedulingParameterDefinition =
                                        AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                                schedulingParameterDefinition.setName(algorithm.getProcessParameterNames().get(i));
                                // this line below is insane... but apparently ParameterType and Type are
                                // completely differente elements in the type hirearchy built for app4mc.
                                schedulingParameterDefinition.setType(
                                        ParameterType.valueOf(algorithm.getProcessParameters().get(i).getType().getTypeName())
                                );
                            });
                    return newDef;
                });
    }

    default SchedulerDefinition getTimeTriggeredSchedulerDefinition(Amalthea amalthea) {
        return amalthea.getOsModel().getSchedulerDefinitions()
                .stream()
                .filter(scheDef -> scheDef.getName().equals("TimeTriggered"))
                .findAny().orElseGet(() -> {
                    final SchedulerDefinition newDef = AmaltheaFactory.eINSTANCE.createSchedulerDefinition();
                    newDef.setName("TimeTriggered");
                    newDef.setDescription("A time-table scheduler.");
                    // for some very clunky manual adding for algorithms parameters...
                    final SchedulingParameterDefinition timeTableDef = AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                    timeTableDef.setName("startTime");
                    timeTableDef.setType(ParameterType.TIME);
                    newDef.getProcessParameters().add(timeTableDef);
                    return newDef;
                });
    }

}
