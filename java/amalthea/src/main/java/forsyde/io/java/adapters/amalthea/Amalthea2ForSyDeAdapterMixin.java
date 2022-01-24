package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.platform.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
public interface Amalthea2ForSyDeAdapterMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromPUIntoVertex(ProcessingUnit pu, Vertex v) {
        ProcessingUnitDefinition puDef = pu.getDefinition();
        v.addTraits(VertexTrait.PLATFORM_INSTRUMENTEDPROCESSINGMODULE);
        InstrumentedProcessingModule profPu = InstrumentedProcessingModule.safeCast(v).get();
        Map<String, Map<String, Long>> provisions = new HashMap<>();
        Map<String, Long> provisionsInner = new HashMap<>();
        for (HwFeature feature : puDef.getFeatures()) {
            if (feature.getContainingCategory().getName().startsWith("operationDurationInCycles")) {
                provisionsInner.put(feature.getName(), Math.round(feature.getValue()));
            }
        }
        provisions.put(puDef.getName(), provisionsInner);
        profPu.setOperationDurationInCycles(provisions);
    }

    default void fromCUIntoVertex(ConnectionHandler connectionHandler, Vertex v) {
        switch (connectionHandler.getDefinition().getPolicy()) {
            case ROUND_ROBIN:
                v.addTraits(VertexTrait.PLATFORM_ROUNDROBINCOMMUNICATIONMODULE);
                final RoundRobinCommunicationModule rrVertex = new RoundRobinCommunicationModuleViewer(v);
                List<HwConnection> connections = connectionHandler.getPorts().stream().flatMap(p -> p.getConnections().stream())
                        .filter(p -> p.getPort1().getNamedContainer().equals(connectionHandler)).collect(Collectors.toList());
                HashMap<String, Integer> allocation = new HashMap<>();
                connections.forEach(p -> allocation.put(p.getPort2().getNamedContainer().getName(), 1));
                rrVertex.setAllocatedWeights(allocation);
                rrVertex.setTotalWeights(allocation.size());
                break;
            default:
                break;
        }
    }

    default Map<INamed, Vertex> fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure) {
        return fromStructureToVertex(model, structure, "");
    }

    default Map<INamed, Vertex> fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure,
                                                      String prefix) {
        final Vertex structureVertex = new Vertex(prefix + structure.getName(), VertexTrait.PLATFORM_ABSTRACTSTRUCTURE);
        final HashMap<INamed, Vertex> transformed = new HashMap<>();
        model.addVertex(structureVertex);
        transformed.put(structure, structureVertex);
        structureVertex.ports.add("submodules");
        for (HwStructure childStructure : structure.getStructures()) {
            transformed.putAll(fromStructureToVertex(model, childStructure, prefix + structure.getName() + "."));
            model.connect(structureVertex, transformed.get(childStructure), "submodules",
                    EdgeTrait.PLATFORM_STRUCTURALCONNECTION);
        }
        for (HwModule module : structure.getModules()) {
            final Vertex moduleVertex = new Vertex(prefix + structure.getName() + "." + module.getName());
            moduleVertex.putProperty("nominal_frequency_in_hertz",
                    fromFrequencyToLong(module.getFrequencyDomain().getDefaultValue()));
            transformed.put(module, moduleVertex);
            for (HwPort port : module.getPorts()) {
                moduleVertex.ports.add(port.getName());
            }
            if (module instanceof ProcessingUnit) {
                ProcessingUnit processingUnit = (ProcessingUnit) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_GENERICPROCESSINGMODULE);
                fromPUIntoVertex(processingUnit, moduleVertex);
            } else if (module instanceof Memory) {
                Memory memory = (Memory) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_GENERICMEMORYMODULE);
                moduleVertex.putProperty("max_memory_in_bits", memory.getDefinition().getSize().getNumberBits());
            } else if (module instanceof Cache) {
                Cache cache = (Cache) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_GENERICCACHEMODULE);
            } else if (module instanceof ConnectionHandler) {
                ConnectionHandler connectionHandler = (ConnectionHandler) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_INSTRUMENTEDCOMMUNICATIONMODULE);
                final InstrumentedCommunicationModule interconnectVertex = new InstrumentedCommunicationModuleViewer(
                        moduleVertex);
                interconnectVertex.setMaxConcurrentFlits(connectionHandler.getDefinition().getMaxConcurrentTransfers());
                // burst size is always in B, it seems
                interconnectVertex
                        .setFlitSizeInBits(connectionHandler.getDefinition().getMaxBurstSize() * 8);
                interconnectVertex.setInitialLatency(connectionHandler.getDefinition().getReadLatency().getLowerBound().intValue());
                fromCUIntoVertex(connectionHandler, moduleVertex);
            }
            model.addVertex(moduleVertex);
            model.connect(structureVertex, moduleVertex, "submodules", EdgeTrait.PLATFORM_STRUCTURALCONNECTION);
        }
        for (HwPort port : structure.getPorts()) {
            structureVertex.ports.add(port.getName());
        }
        return transformed;
    }

    default void fromStructureToEdges(ForSyDeSystemGraph model, HwStructure structure,
                                      Map<INamed, Vertex> transformed) {
        for (HwStructure childStructure : structure.getStructures()) {
            fromStructureToEdges(model, childStructure, transformed);
        }
        for (HwConnection connection : structure.getConnections()) {
            final Vertex sourceVertex = transformed.get(connection.getPort1().getNamedContainer());
            final Vertex targetVertex = transformed.get(connection.getPort2().getNamedContainer());
            model.connect(sourceVertex, targetVertex, connection.getPort1().getName(), connection.getPort2().getName(), EdgeTrait.PLATFORM_PHYSICALCONNECTION);
            // add the port information
            if (!SynthetizableDigitalPorts.conforms(sourceVertex)) {
                sourceVertex.addTraits(VertexTrait.PLATFORM_SYNTHETIZABLEDIGITALPORTS);
                // add the properties if they dont exist.
                SynthetizableDigitalPorts.safeCast(sourceVertex).ifPresent(source -> {
                    if (source.getPortProtocolAcronym() == null) {
                        source.setPortProtocolAcronym(new HashMap<>());
                    }
                    if (source.getPortIsInitiator() == null) {
                        source.setPortIsInitiator(new HashMap<>());
                    }
                    if (source.getPortWidthInBits() == null) {
                        source.setPortWidthInBits(new HashMap<>());
                    }
                });
            }
            if (!SynthetizableDigitalPorts.conforms(targetVertex)) {
                targetVertex.addTraits(VertexTrait.PLATFORM_SYNTHETIZABLEDIGITALPORTS);
                // add the properties if they dont exist.
                SynthetizableDigitalPorts.safeCast(targetVertex).ifPresent(target -> {
                    if (target.getPortProtocolAcronym() == null) {
                        target.setPortProtocolAcronym(new HashMap<>());
                    }
                    if (target.getPortIsInitiator() == null) {
                        target.setPortIsInitiator(new HashMap<>());
                    }
                    if (target.getPortWidthInBits() == null) {
                        target.setPortWidthInBits(new HashMap<>());
                    }
                });
            }
            SynthetizableDigitalPorts.safeCast(sourceVertex).ifPresent(source -> {
                source.getPortProtocolAcronym().put(connection.getPort1().getName(), connection.getPort1().getPortInterface().getLiteral());
                source.getPortWidthInBits().put(connection.getPort1().getName(), connection.getPort1().getBitWidth());
                source.getPortIsInitiator().put(connection.getPort1().getName(), connection.getPort1().getPortType().equals(PortType.INITIATOR));
            });
            SynthetizableDigitalPorts.safeCast(targetVertex).ifPresent(target -> {
                target.getPortProtocolAcronym().put(connection.getPort2().getName(), connection.getPort2().getPortInterface().getLiteral());
                target.getPortWidthInBits().put(connection.getPort2().getName(), connection.getPort2().getBitWidth());
                target.getPortIsInitiator().put(connection.getPort2().getName(), connection.getPort2().getPortType().equals(PortType.INITIATOR));
            });
        }
    }

    /*
    default void oSModelToBinding(Amalthea amalthea, ForSyDeSystemGraph model, Map<INamed, Vertex> transformed) {
        for (OperatingSystem os: amalthea.getOsModel().getOperatingSystems()) {
            for(TaskScheduler taskScheduler : os.getTaskSchedulers()) {
                final Vertex platformVertex = new Vertex(os.getName() + "." + taskScheduler.getName(), VertexTrait.PLATFORM_PLATFORMELEM);
                if (taskScheduler.getSchedulingAlgorithm() instanceof FixedPriorityPreemptive) {
                    platformVertex.addTraits(VertexTrait.PLATFORM_RUNTIME_FIXEDPRIORITYSCHEDULER);
                }
                transformed.put(os, platformVertex);
                transformed.put(taskScheduler, platformVertex);
                model.addVertex(platformVertex);
                for (SchedulerAllocation allocation : amalthea.getMappingModel().getSchedulerAllocation()) {
                    if (allocation.getScheduler().equals(taskScheduler)) {
                        final Vertex puVertex = transformed.get(allocation.getExecutingPU());
                        model.connect(platformVertex, puVertex, EdgeTrait.DECISION_ABSTRACTALLOCATION);
                    }
                }
            }
        }
    }
         */

    default Long fromFrequencyToLong(Frequency freq) {
        double multiplier = 1.0;
        switch (freq.getUnit()) {
            case HZ:
                multiplier = 1.0;
                break;
            case KHZ:
                multiplier = 1000.0;
                break;
            case MHZ:
                multiplier = 1000000.0;
                break;
            case GHZ:
                multiplier = 1000000000.0;
                break;
            default:
                break;
        }
        return Double.valueOf(freq.getValue() * multiplier).longValue();
    }
}
