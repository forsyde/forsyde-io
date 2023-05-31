package forsyde.io.java.amalthea.adapters.mixins;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.platform.runtime.CyclicExecutiveScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.FixedPriorityScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.RoundRobinScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.TimeTriggeredScheduler;
import org.eclipse.app4mc.amalthea.model.*;
import org.eclipse.app4mc.amalthea.model.predefined.StandardSchedulers;

import java.util.stream.IntStream;

public interface ForSyDe2AmaltheaOSAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromVertexesToOSModel(SystemGraph model, Amalthea target) {
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
                taskScheduler.setName(newId + "_FPScheduler");
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
            CyclicExecutiveScheduler.safeCast(v).ifPresent(cyclicExecutiveScheduler -> {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setName(newId + "_CEScheduler");
                final SchedulerDefinition schedulerDefinition = getCyclicExecutiveSchedulerDefinition(target);
                taskScheduler.setDefinition(schedulerDefinition);
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            });
        }
    }

    default SchedulerDefinition getSchedulerDefinitionByName(Amalthea amalthea, String name) {
        return amalthea.getOsModel().getSchedulerDefinitions()
                .stream()
                .filter(scheDef -> scheDef.getName().equals(name))
                .findAny().orElseGet(() -> {
                    final SchedulerDefinition newDef = AmaltheaFactory.eINSTANCE.createSchedulerDefinition();
                    final StandardSchedulers.Algorithm algorithm = StandardSchedulers.getAlgorithm(name);
                    newDef.setName(algorithm.getAlgorithmName());
                    newDef.setDescription(algorithm.getDescription());
                    amalthea.getOsModel().getSchedulerDefinitions().add(newDef);
                    // for some very clunky manual adding for algorithms parameters...
                    IntStream.range(0, Math.min(algorithm.getAlgorithmParameters().size(), algorithm.getAlgorithmParameterNames().size()))
                            .forEach(i -> {
                                final SchedulingParameterDefinition schedulingParameterDefinition =
                                        AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                                schedulingParameterDefinition.setName(algorithm.getAlgorithmParameterNames().get(i));
                                // this line below is insane... but apparently ParameterType and Type are
                                // completely differente elements in the type hirearchy built for app4mc.
                                switch (algorithm.getAlgorithmParameters().get(i).getType()) {
                                    case INTEGER: schedulingParameterDefinition.setType(ParameterType.INTEGER);
                                    case BOOL: schedulingParameterDefinition.setType(ParameterType.BOOL);
                                    case STRING: schedulingParameterDefinition.setType(ParameterType.STRING);
                                    case FLOAT: schedulingParameterDefinition.setType(ParameterType.FLOAT);
                                    case TIME: schedulingParameterDefinition.setType(ParameterType.TIME);
                                }
//                              schedulingParameterDefinition.setType(
//                                      ParameterType.getByName(algorithm.getAlgorithmParameters().get(i).getType().getTypeName())
//                              );
                            });
                    // and now for the processes also.
                    IntStream.range(0, Math.min(algorithm.getProcessParameters().size(), algorithm.getProcessParameterNames().size()))
                            .forEach(i -> {
                                final SchedulingParameterDefinition schedulingParameterDefinition =
                                        AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                                schedulingParameterDefinition.setName(algorithm.getProcessParameterNames().get(i));
                                // this line below is insane... but apparently ParameterType and Type are
                                // completely differente elements in the type hirearchy built for app4mc.
                                switch (algorithm.getProcessParameters().get(i).getType()) {
                                    case INTEGER: schedulingParameterDefinition.setType(ParameterType.INTEGER);
                                    case BOOL: schedulingParameterDefinition.setType(ParameterType.BOOL);
                                    case STRING: schedulingParameterDefinition.setType(ParameterType.STRING);
                                    case FLOAT: schedulingParameterDefinition.setType(ParameterType.FLOAT);
                                    case TIME: schedulingParameterDefinition.setType(ParameterType.TIME);
                                }
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
                    amalthea.getOsModel().getSchedulerDefinitions().add(newDef);
                    newDef.setName("TimeTriggered");
                    newDef.setDescription("A time-table scheduler.");
                    // for some very clunky manual adding for algorithms parameters...
                    final SchedulingParameterDefinition timeTableDef = AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                    amalthea.getOsModel().getSchedulingParameterDefinitions().add(timeTableDef);
                    timeTableDef.setName("startTime");
                    timeTableDef.setType(ParameterType.TIME);
                    newDef.getProcessParameters().add(timeTableDef);
                    return newDef;
                });
    }

    default SchedulerDefinition getCyclicExecutiveSchedulerDefinition(Amalthea amalthea) {
        return amalthea.getOsModel().getSchedulerDefinitions()
                .stream()
                .filter(scheDef -> scheDef.getName().equals("CyclicExecutive"))
                .findAny().orElseGet(() -> {
                    final SchedulerDefinition newDef = AmaltheaFactory.eINSTANCE.createSchedulerDefinition();
                    amalthea.getOsModel().getSchedulerDefinitions().add(newDef);
                    newDef.setName("CyclicExecutive");
                    newDef.setDescription("A cyclic executive scheduler.");
                    // for some very clunky manual adding for algorithms parameters...
                    final SchedulingParameterDefinition cyclePositionParameter = AmaltheaFactory.eINSTANCE.createSchedulingParameterDefinition();
                    amalthea.getOsModel().getSchedulingParameterDefinitions().add(cyclePositionParameter);
                    cyclePositionParameter.setName("cyclePosition");
                    cyclePositionParameter.setType(ParameterType.TIME);
                    newDef.getProcessParameters().add(cyclePositionParameter);
                    return newDef;
                });
    }

}
