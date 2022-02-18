package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.*;
import forsyde.io.java.typed.viewers.decision.Allocated;
import forsyde.io.java.typed.viewers.platform.*;
import forsyde.io.java.typed.viewers.platform.runtime.RoundRobinScheduler;
import forsyde.io.java.typed.viewers.visualization.GreyBox;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.util.*;
import java.util.stream.Collectors;

public interface AmaltheaHW2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromHWtoForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getHwModel() != null) {
            amalthea.getHwModel().getStructures().forEach(hwStructure -> {
                fromStructureToVertex(forSyDeSystemGraph, hwStructure);
                fromStructureToEdges(forSyDeSystemGraph, hwStructure);
            });
            connectModulesBetweenContainers(amalthea, forSyDeSystemGraph);
        }
    }

    default void fromPUIntoVertex(ProcessingUnit pu, Vertex v) {
        ProcessingUnitDefinition puDef = pu.getDefinition();
        v.addTraits(VertexTrait.PLATFORM_INSTRUMENTEDPROCESSINGMODULE);
        InstrumentedProcessingModule profPu = InstrumentedProcessingModule.safeCast(v).get();
        Map<String, Map<String, Double>> provisions = new HashMap<>();
        Map<String, Double> provisionsInner = new HashMap<>();
        for (HwFeature feature : puDef.getFeatures()) {
            final HwFeatureCategory hwFeatureCategory = feature.getContainingCategory();
            if (feature.getName().contains("instructionsPerCycle")
                    || feature.getName().contains("InstructionsPerCycle")
                    || feature.getName().contains("IPC")) {
                provisionsInner.put(hwFeatureCategory.getName(), feature.getValue());
            }
        }
        provisions.put("defaultNeeds", provisionsInner);
        provisions.put("defaultTicks", Map.of("tick", 1.0));
        profPu.setModalInstructionsPerCycle(provisions);
    }

    default void fromCUIntoVertex(ForSyDeSystemGraph forSyDeSystemGraph, ConnectionHandler connectionHandler, Vertex v) {
        switch (connectionHandler.getDefinition().getPolicy()) {
            case ROUND_ROBIN:
                final Vertex rrVertex = new Vertex(v.getIdentifier() + "Scheduler");
                final RoundRobinCommunicationModule roundRobinCommunicationModule = RoundRobinCommunicationModule.enforce(v);
                final RoundRobinScheduler roundRobinScheduler = RoundRobinScheduler.enforce(rrVertex);
                List<HwConnection> connections = connectionHandler.getPorts().stream().flatMap(p -> p.getConnections().stream())
                        .filter(p -> p.getPort1().getNamedContainer().equals(connectionHandler)).collect(Collectors.toList());
                HashMap<String, Integer> allocation = new HashMap<>();
                connections.forEach(p -> allocation.put(p.getPort2().getNamedContainer().getName(), 1));
                roundRobinCommunicationModule.setAllocatedWeights(allocation);
                roundRobinCommunicationModule.setTotalWeights(allocation.size());
                addEquivalence(connectionHandler, rrVertex);
                forSyDeSystemGraph.addVertex(rrVertex);
                forSyDeSystemGraph.connect(rrVertex, v, "allocationHost", EdgeTrait.DECISION_ABSTRACTALLOCATION);
                Visualizable.enforce(rrVertex);
                GreyBox.enforce(v);
                forSyDeSystemGraph.connect(v, rrVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                break;
            default:
                break;
        }
    }

    default void fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure) {
        fromStructureToVertex(model, structure, "");
    }

    default void fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure,
                                                      String prefix) {
        final Vertex structureVertex = new Vertex(prefix + structure.getName(), VertexTrait.PLATFORM_ABSTRACTSTRUCTURE);
        final GreyBox structureGreyBox = GreyBox.enforce(structureVertex);
        addEquivalence(structure, structureVertex);
        model.addVertex(structureVertex);
        structureVertex.ports.add("submodules");
        for (HwStructure childStructure : structure.getStructures()) {
            fromStructureToVertex(model, childStructure, prefix + structure.getName() + ".");
            equivalent(childStructure).ifPresent(childStructureVertex -> {
                model.connect(structureVertex, childStructureVertex, "submodules",
                        EdgeTrait.PLATFORM_STRUCTURALCONNECTION);
                model.connect(structureVertex, childStructureVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
            });
        }
        for (HwModule module : structure.getModules()) {
            final Vertex moduleVertex = new Vertex(prefix + structure.getName() + "." + module.getName());
            model.addVertex(moduleVertex);
            final DigitalModule digitalModule = DigitalModule.enforce(moduleVertex);
            final Visualizable visualizableModule = Visualizable.enforce(moduleVertex);
            digitalModule.setOperatingFrequencyInHertz(fromFrequencyToLong(module.getFrequencyDomain().getDefaultValue()));
            addEquivalence(module, moduleVertex);
            for (HwPort port : module.getPorts()) {
                moduleVertex.ports.add(port.getName());
            }
            if (module instanceof ProcessingUnit) {
                ProcessingUnit processingUnit = (ProcessingUnit) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_GENERICPROCESSINGMODULE);
                fromPUIntoVertex(processingUnit, moduleVertex);
            } else if (module instanceof Memory) {
                Memory memory = (Memory) module;
                final GenericMemoryModule genericMemoryModule = GenericMemoryModule.enforce(moduleVertex);
                genericMemoryModule.setSpaceInBits(memory.getDefinition().getSize().getNumberBits());
                //moduleVertex.putProperty("max_memory_in_bits", memory.getDefinition().getSize().getNumberBits());
            } else if (module instanceof Cache) {
                Cache cache = (Cache) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_GENERICCACHEMODULE);
            } else if (module instanceof ConnectionHandler) {
                ConnectionHandler connectionHandler = (ConnectionHandler) module;
                moduleVertex.addTraits(VertexTrait.PLATFORM_INSTRUMENTEDCOMMUNICATIONMODULE);
                final InstrumentedCommunicationModule interconnectVertex = new InstrumentedCommunicationModuleViewer(
                        moduleVertex);
                interconnectVertex.setMaxCyclesPerFlit(1);
                interconnectVertex.setMaxConcurrentFlits(connectionHandler.getDefinition().getMaxConcurrentTransfers());
                // burst size is always in B, it seems
                interconnectVertex
                        .setFlitSizeInBits((long) connectionHandler.getDefinition().getMaxBurstSize() * 8L);
                interconnectVertex.setInitialLatency(
                        connectionHandler.getDefinition().getReadLatency() != null ?
                        connectionHandler.getDefinition().getReadLatency().getUpperBound() :
                        0L);
                fromCUIntoVertex(model, connectionHandler, moduleVertex);
            }
            model.connect(structureVertex, moduleVertex, "submodules", EdgeTrait.PLATFORM_STRUCTURALCONNECTION);
            model.connect(structureVertex, moduleVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
        }
        for (HwPort port : structure.getPorts()) {
            structureVertex.ports.add(port.getName());
        }
    }

    default void fromStructureToEdges(ForSyDeSystemGraph model, HwStructure structure) {
        for (HwStructure childStructure : structure.getStructures()) {
            fromStructureToEdges(model, childStructure);
        }
        for (HwConnection connection : structure.getConnections()) {
            final Vertex sourceVertex = equivalent(connection.getPort1().getNamedContainer()).get();
            final Vertex targetVertex = equivalent(connection.getPort2().getNamedContainer()).get();
            model.connect(sourceVertex, targetVertex, connection.getPort1().getName(), connection.getPort2().getName(), EdgeTrait.PLATFORM_PHYSICALCONNECTION);
            model.connect(sourceVertex, targetVertex, connection.getPort1().getName(), connection.getPort2().getName(), EdgeTrait.VISUALIZATION_VISUALCONNECTION);
            // add the port information
            if (DigitalModule.conforms(sourceVertex) && !SynthetizableDigitalPorts.conforms(sourceVertex)) {
                sourceVertex.addTraits(VertexTrait.PLATFORM_SYNTHETIZABLEDIGITALPORTS);
                // add the properties if they dont exist.
                SynthetizableDigitalPorts.safeCast(sourceVertex).ifPresent(source -> {
                    source.setPortProtocolAcronym(new HashMap<>());
                    source.setPortIsInitiator(new HashMap<>());
                    source.setPortWidthInBits(new HashMap<>());
                });
            }
            if (DigitalModule.conforms(targetVertex) && !SynthetizableDigitalPorts.conforms(targetVertex)) {
                targetVertex.addTraits(VertexTrait.PLATFORM_SYNTHETIZABLEDIGITALPORTS);
                // add the properties if they dont exist.
                SynthetizableDigitalPorts.safeCast(targetVertex).ifPresent(target -> {
                    target.setPortProtocolAcronym(new HashMap<>());
                    target.setPortIsInitiator(new HashMap<>());
                    target.setPortWidthInBits(new HashMap<>());
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

    default void connectModulesBetweenContainers(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        forSyDeSystemGraph.vertexSet().stream()
        .filter(AbstractStructure::conforms)
        .map(v -> AbstractStructure.safeCast(v).get())
        .forEach(abstractStructure -> {
            for (EdgeInfo inInfo : forSyDeSystemGraph.incomingEdgesOf(abstractStructure.getViewedVertex())) {
                final Vertex inVertex = forSyDeSystemGraph.getEdgeSource(inInfo);
                for (EdgeInfo outInfo : forSyDeSystemGraph.outgoingEdgesOf(abstractStructure.getViewedVertex())) {
                    final Vertex outVertex = forSyDeSystemGraph.getEdgeTarget(outInfo);
                    if (inInfo.targetPort.equals(outInfo.sourcePort) && !AbstractStructure.conforms(inVertex)
                            && !AbstractStructure.conforms(outVertex)) {
                        final EdgeInfo edgeInfo = new EdgeInfo(inVertex.identifier, outVertex.identifier, inInfo.targetPort, outInfo.sourcePort);
                        edgeInfo.edgeTraits.addAll(inInfo.edgeTraits);
                        edgeInfo.edgeTraits.addAll(outInfo.edgeTraits);
                        forSyDeSystemGraph.addEdge(inVertex, outVertex, edgeInfo);
                    }
                }
            }
        });
    }

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
