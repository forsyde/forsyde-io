package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface ForSyDe2AmaltheaHWAdapter extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromVertexesToHWModel(ForSyDeSystemGraph model, Amalthea target) {
        target.setHwModel(AmaltheaFactory.eINSTANCE.createHWModel());
        fromVertexesToModules(model, target);
        fromVertexesToStructures(model, target);
        fromEdgesToConnections(model, target);
    }

    default void fromVertexesToModules(ForSyDeSystemGraph model, Amalthea target) {
        model.vertexSet()
            .stream().filter(AbstractDigitalModule::conforms)
            .flatMap(v -> AbstractDigitalModule.safeCast(v).stream())
            .forEach(p -> {
                final String newId = p.getViewedVertex().getIdentifier().replace(".", "_");

                final FrequencyDomain freqDef = AmaltheaFactory.eINSTANCE.createFrequencyDomain();
                freqDef.setName(newId + "Freq");
                Frequency f = AmaltheaFactory.eINSTANCE.createFrequency();
                f.setValue(p.getNominalFrequencyInHertz());
                f.setUnit(FrequencyUnit.HZ);
                freqDef.setDefaultValue(f);
                if (!target.getHwModel().getDomains().contains(freqDef)) target.getHwModel().getDomains().add(freqDef);

                HwModule hwModule = null;

                if (GenericProcessingModule.conforms(p)) {
                    final GenericProcessingModule pu = GenericProcessingModule.safeCast(p).get();

                    // generate definition
                    ProcessingUnitDefinition puDef = AmaltheaFactory.eINSTANCE.createProcessingUnitDefinition();
                    puDef.setName(newId + "Def");
                    if (!target.getHwModel().getDefinitions().contains(puDef)) target.getHwModel().getDefinitions().add(puDef);

                    // generate element
                    ProcessingUnit amaltheaPu = AmaltheaFactory.eINSTANCE.createProcessingUnit();
                    amaltheaPu.setName(newId);
                    amaltheaPu.setDefinition(puDef);
                    amaltheaPu.setFrequencyDomain(freqDef);
                    addEquivalence(p.getViewedVertex(), amaltheaPu);
                    hwModule = amaltheaPu;
                }
                else if (GenericMemoryModule.conforms(p)) {
                    final GenericMemoryModule genericMemoryModule = GenericMemoryModule.safeCast(p).get();

                    // generate definition
                    MemoryDefinition memDef = AmaltheaFactory.eINSTANCE.createMemoryDefinition();
                    memDef.setName(newId + "Def");
                    if (!target.getHwModel().getDefinitions().contains(memDef)) target.getHwModel().getDefinitions().add(memDef);

                    DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                    dataSize.setUnit(DataSizeUnit.BIT);
                    dataSize.setValue(BigInteger.valueOf(genericMemoryModule.getMaxMemoryInBits()));
                    memDef.setSize(dataSize);

                    // generate element
                    Memory mem = AmaltheaFactory.eINSTANCE.createMemory();
                    mem.setName(newId);
                    mem.setDefinition(memDef);
                    mem.setFrequencyDomain(freqDef);
                    addEquivalence(p.getViewedVertex(), mem);
                    hwModule = mem;
                }
                else if (GenericDigitalInterconnect.conforms(p)) {
                    final GenericDigitalInterconnect genericDigitalInterconnect = GenericDigitalInterconnect.safeCast(p).get();

                    // generate definition
                    ConnectionHandlerDefinition commDef = AmaltheaFactory.eINSTANCE.createConnectionHandlerDefinition();
                    commDef.setName(newId + "Def");
                    if (!target.getHwModel().getDefinitions().contains(commDef)) target.getHwModel().getDefinitions().add(commDef);

                    DataRate dataRate = AmaltheaFactory.eINSTANCE.createDataRate();
                    dataRate.setUnit(DataRateUnit.BIT_PER_SECOND);
                    dataRate.setValue(BigInteger.valueOf(genericDigitalInterconnect.getMaxFlitSizeInBits())
                            .multiply(BigInteger.valueOf(genericDigitalInterconnect.getNominalFrequencyInHertz())));
                    commDef.setDataRate(dataRate);
                    commDef.setMaxConcurrentTransfers(genericDigitalInterconnect.getMaxConcurrentFlits());
                    if (RoundRobinInterconnect.conforms(genericDigitalInterconnect))
                        commDef.setPolicy(SchedPolicy.ROUND_ROBIN);

                    ConnectionHandler conn = AmaltheaFactory.eINSTANCE.createConnectionHandler();
                    conn.setName(newId);
                    conn.setDefinition(commDef);
                    conn.setFrequencyDomain(freqDef);
                    addEquivalence(p.getViewedVertex(), conn);
                    hwModule = conn;
                }
                else if (GenericCacheModule.conforms(p)) {
                    Cache cacheModule = AmaltheaFactory.eINSTANCE.createCache();
                    cacheModule.setName(newId);
                    cacheModule.setFrequencyDomain(freqDef);
                    addEquivalence(p.getViewedVertex(), cacheModule);
                    hwModule = cacheModule;
                }

                // take care of port information
                if (hwModule != null) {
                    for (String portName : p.getPorts()) {
                        final HwPort newPort = AmaltheaFactory.eINSTANCE.createHwPort();
                        newPort.setName(portName);
                        hwModule.getPorts().add(newPort);
                        SynthetizableDigitalPorts.safeCast(p).ifPresent(sdp -> {
                            newPort.setPortInterface(PortInterface.getByName(sdp.getPortProtocolAcronym()));
                            newPort.setPortType(sdp.getPortIsInitiator() ? PortType.INITIATOR : PortType.RESPONDER);
                            newPort.setBitWidth(sdp.getPortWidthInBits());
                        });
                    }
                }
            });

    }

    default void fromVertexesToStructures(ForSyDeSystemGraph model, Amalthea target) {
        final Set<AbstractStructure> platforms = model.vertexSet()
                .stream().filter(AbstractStructure::conforms)
                .map(v -> AbstractStructure.safeCast(v).get())
                .collect(Collectors.toSet());
        final Set<AbstractStructure> topPlatform = model.vertexSet()
                .stream().filter(AbstractStructure::conforms)
                .filter(v -> model.incomingEdgesOf(v).stream().noneMatch(e -> e.edgeTraits.contains(EdgeTrait.AbstractStructuralConnection)))
                .map(v -> AbstractStructure.safeCast(v).get())
                .collect(Collectors.toSet());
        for (AbstractStructure p : platforms) {
            HwStructure hwStructure = AmaltheaFactory.eINSTANCE.createHwStructure();
            hwStructure.setName(p.getViewedVertex().getIdentifier());
            addEquivalence(p.getViewedVertex(), hwStructure);
        }
        for (AbstractStructure p : topPlatform) {
            equivalent(p.getViewedVertex()).map(e -> (HwStructure) e).ifPresent(hwStructure -> {
                target.getHwModel().getStructures().add(hwStructure);
            });
        }
        for (AbstractStructure parent : platforms) {
            // get the generated equivalent
            equivalent(parent.getViewedVertex()).map(e -> (HwStructure) e).ifPresent(parentHwStructure -> {
                // get only the sub structures
                parent.getSubmodulesPort(model).stream().filter(AbstractStructure::conforms).forEach(child -> {
                    // get their equivalents
                    equivalent(child.getViewedVertex()).map(e -> (HwStructure) e).ifPresent(childHwStructure -> {
                        // do the deed
                        parentHwStructure.getStructures().add(childHwStructure);
                        // remove the 'namespace' from the child identifier
                        if (child.getViewedVertex().getIdentifier().startsWith(
                                parent.getViewedVertex().getIdentifier() + "."
                        )) {
                            final String newName = child.getViewedVertex().getIdentifier()
                                    .replace(parent.getViewedVertex().getIdentifier() + ".", "")
                                    .replace(".", "_");
                            childHwStructure.setName(newName);
                        }
                    });
                });
                parent.getSubmodulesPort(model).stream().filter(AbstractDigitalModule::conforms).forEach(module -> {
                    equivalent(module.getViewedVertex()).map(e -> (HwModule) e).ifPresent(hwModule -> {

                        // remove the 'namespace' from the child identifier
                        if (module.getViewedVertex().getIdentifier().startsWith(
                                parent.getViewedVertex().getIdentifier() + "."
                        )) {
                            final String newName = module.getViewedVertex().getIdentifier()
                                    .replace(parent.getViewedVertex().getIdentifier() + ".", "")
                                    .replace(".", "_");
                            hwModule.setName(newName);
                        }
                        parentHwStructure.getModules().add(hwModule);
                    });
                });
            });
        }
    }

    default void fromEdgesToConnections(ForSyDeSystemGraph model, Amalthea targetModel) {
        for (Edge e: model.edgeSet()) {
            if (e.edgeTraits.contains(EdgeTrait.AbstractPhysicalConnection) &&
                (AbstractStructure.conforms(e.getSource()) || AbstractDigitalModule.conforms(e.getSource())) &&
                (AbstractStructure.conforms(e.getTarget()) || AbstractDigitalModule.conforms(e.getTarget())) &&
                e.sourcePort.isPresent() && e.targetPort.isPresent()) {
                // the vertices are supposed to be generated
                final INamed source = equivalent(e.getSource()).get();
                final INamed target = equivalent(e.getTarget()).get();
                // get the minimum parent.
                HwStructure commonLeastParent = null;
                for (HwStructure sourceParent = (HwStructure) source.eContainer(); sourceParent != null;
                     sourceParent = (HwStructure) sourceParent.eContainer()) {
                    for (HwStructure targetParent = (HwStructure) target.eContainer(); targetParent != null;
                         targetParent = (HwStructure) targetParent.eContainer()) {
                            if (sourceParent.equals(targetParent)) commonLeastParent = sourceParent;
                            if (commonLeastParent != null) break;
                    }
                    if (commonLeastParent != null) break;
                }
                // create the connection
                HwPort sourcePort = source instanceof HwModule ?
                        ((HwModule) source).getPorts().stream().filter(p -> p.getName().equals(e.sourcePort.get())).findAny().get() :
                        ((HwStructure) source).getPorts().stream().filter(p -> p.getName().equals(e.sourcePort.get())).findAny().get();
                HwPort targetPort = target instanceof HwModule ?
                        ((HwModule) target).getPorts().stream().filter(p -> p.getName().equals(e.targetPort.get())).findAny().get() :
                        ((HwStructure) target).getPorts().stream().filter(p -> p.getName().equals(e.targetPort.get())).findAny().get();
                // look for source port
                /*if (source instanceof HwModule && e.getSourcePort().isPresent()) {
                    HwModule sourceModule = (HwModule) source;
                    // find the port or create a new one and do the plumbing
                    sourcePort = sourceModule.getPorts().stream().filter(p -> p.getName().equals(e.sourcePort))
                            .findAny().orElseGet(() -> {
                                final HwPort newPort = AmaltheaFactory.eINSTANCE.createHwPort();
                                newPort.setName(e.getSourcePort().get());
                                sourceModule.getPorts().add(newPort);
                                return newPort;
                            });
                }*/
                // look for target source port
                /*if (target instanceof HwModule && e.getTargetPort().isPresent()) {
                    HwModule targetModule = (HwModule) target;
                    Optional<HwPort> targetSearch = targetModule.getPorts().stream().filter(p -> p.getName().equals(e.targetPort)).findAny();
                    if (targetSearch.isEmpty()) {
                        targetPort = AmaltheaFactory.eINSTANCE.createHwPort();
                        targetPort.setName(e.getTargetPort().get());
                        targetModule.getPorts().add(targetPort);
                    } else {
                        targetPort = targetSearch.get();
                    }
                }*/
                // now to the parent
                HwConnection connection = AmaltheaFactory.eINSTANCE.createHwConnection();
                connection.setPort1(sourcePort);
                connection.setPort2(targetPort);
                connection.setName(source.getName() + "_" + sourcePort.getName() + "_to_" + source.getName() + "_" + targetPort.getName());
                commonLeastParent.getConnections().add(connection);
            }
        }
    }
}