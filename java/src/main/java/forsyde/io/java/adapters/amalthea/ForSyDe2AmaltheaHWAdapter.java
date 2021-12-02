package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

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
        final Set<AbstractDigitalModule> modules = model.vertexSet()
                .stream().filter(AbstractDigitalModule::conforms)
                .map(v -> AbstractDigitalModule.safeCast(v).get())
                .collect(Collectors.toSet());
        for (AbstractDigitalModule p : modules) {
            if (GenericProcessingModule.conforms(p)) {
                final GenericProcessingModule pu = GenericProcessingModule.safeCast(p).get();

                // generate definition
                ProcessingUnitDefinition puDef = AmaltheaFactory.eINSTANCE.createProcessingUnitDefinition();
                puDef.setName(p.getViewedVertex().getIdentifier() + "Def");
                if (!target.getHwModel().getDefinitions().contains(puDef)) target.getHwModel().getDefinitions().add(puDef);
                FrequencyDomain freqDef = AmaltheaFactory.eINSTANCE.createFrequencyDomain();
                freqDef.setName(p.getViewedVertex().getIdentifier() + "Freq");
                Frequency f = AmaltheaFactory.eINSTANCE.createFrequency();
                f.setValue(pu.getNominalFrequencyInHertz());
                f.setUnit(FrequencyUnit.HZ);
                freqDef.setDefaultValue(f);
                if (!target.getHwModel().getDomains().contains(freqDef)) target.getHwModel().getDomains().add(freqDef);

                // generate element
                ProcessingUnit amaltheaPu = AmaltheaFactory.eINSTANCE.createProcessingUnit();
                amaltheaPu.setName(p.getViewedVertex().getIdentifier());
                amaltheaPu.setDefinition(puDef);
                amaltheaPu.setFrequencyDomain(freqDef);
                addEquivalence(p.getViewedVertex(), amaltheaPu);
            }
            if (GenericMemoryModule.conforms(p)) {
                final GenericMemoryModule genericMemoryModule = GenericMemoryModule.safeCast(p).get();

                // generate definition
                MemoryDefinition memDef = AmaltheaFactory.eINSTANCE.createMemoryDefinition();
                memDef.setName(p.getViewedVertex().getIdentifier() + "Def");
                if (!target.getHwModel().getDefinitions().contains(memDef)) target.getHwModel().getDefinitions().add(memDef);
                FrequencyDomain freqDef = AmaltheaFactory.eINSTANCE.createFrequencyDomain();
                freqDef.setName(p.getViewedVertex().getIdentifier() + "Freq");
                Frequency f = AmaltheaFactory.eINSTANCE.createFrequency();
                f.setValue(genericMemoryModule.getNominalFrequencyInHertz());
                f.setUnit(FrequencyUnit.HZ);
                freqDef.setDefaultValue(f);
                if (!target.getHwModel().getDomains().contains(freqDef)) target.getHwModel().getDomains().add(freqDef);
                DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                dataSize.setUnit(DataSizeUnit.BIT);
                dataSize.setValue(BigInteger.valueOf(genericMemoryModule.getMaxMemoryInBits()));
                memDef.setSize(dataSize);

                // generate element
                Memory mem = AmaltheaFactory.eINSTANCE.createMemory();
                mem.setName(p.getViewedVertex().getIdentifier());
                mem.setDefinition(memDef);
                addEquivalence(p.getViewedVertex(), mem);
            }
            if (GenericDigitalInterconnect.conforms(p)) {
                final GenericDigitalInterconnect genericDigitalInterconnect = GenericDigitalInterconnect.safeCast(p).get();

                // generate definition
                ConnectionHandlerDefinition commDef = AmaltheaFactory.eINSTANCE.createConnectionHandlerDefinition();
                commDef.setName(p.getViewedVertex().getIdentifier() + "Def");
                if (!target.getHwModel().getDefinitions().contains(commDef)) target.getHwModel().getDefinitions().add(commDef);
                FrequencyDomain freqDef = AmaltheaFactory.eINSTANCE.createFrequencyDomain();
                freqDef.setName(p.getViewedVertex().getIdentifier() + "Freq");
                Frequency f = AmaltheaFactory.eINSTANCE.createFrequency();
                f.setValue(genericDigitalInterconnect.getNominalFrequencyInHertz());
                f.setUnit(FrequencyUnit.HZ);
                freqDef.setDefaultValue(f);
                if (!target.getHwModel().getDomains().contains(freqDef)) target.getHwModel().getDomains().add(freqDef);
                DataRate dataRate = AmaltheaFactory.eINSTANCE.createDataRate();
                dataRate.setUnit(DataRateUnit.BIT_PER_SECOND);
                dataRate.setValue(BigInteger.valueOf(genericDigitalInterconnect.getMaxFlitSizeInBits())
                        .multiply(BigInteger.valueOf(genericDigitalInterconnect.getNominalFrequencyInHertz())));
                commDef.setDataRate(dataRate);
                commDef.setMaxConcurrentTransfers(genericDigitalInterconnect.getMaxConcurrentFlits());
                if (RoundRobinInterconnect.conforms(genericDigitalInterconnect))
                    commDef.setPolicy(SchedPolicy.ROUND_ROBIN);

                ConnectionHandler conn = AmaltheaFactory.eINSTANCE.createConnectionHandler();
                conn.setName(p.getViewedVertex().getIdentifier());
                conn.setDefinition(commDef);
                addEquivalence(p.getViewedVertex(), conn);
            }
            if (GenericCacheModule.conforms(p)) {
                Cache cacheModule = AmaltheaFactory.eINSTANCE.createCache();
                cacheModule.setName(p.getViewedVertex().getIdentifier());
                addEquivalence(p.getViewedVertex(), cacheModule);
            }
        }
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
        final Set<AbstractDigitalModule> modules = model.vertexSet()
                .stream().filter(AbstractDigitalModule::conforms)
                .map(v -> AbstractDigitalModule.safeCast(v).get())
                .collect(Collectors.toSet());
        for (AbstractStructure parent : platforms) {
            equivalent(parent.getViewedVertex()).map(e -> (HwStructure) e).ifPresent(parentHwStructure -> {
                for (AbstractStructure child : platforms) {
                    if (model.hasConnection(parent, child, "submodules", null)) {
                        equivalent(child.getViewedVertex()).map(e -> (HwStructure) e).ifPresent(childHwStructure -> {
                            parentHwStructure.getStructures().add(childHwStructure);
                            // remove the 'namespace' from the child identifier
                            if (child.getViewedVertex().getIdentifier().contains(
                                    parent.getViewedVertex().getIdentifier() + "."
                            )) {
                                final String newName = child.getViewedVertex().getIdentifier()
                                        .replace(parent.getViewedVertex().getIdentifier() + ".", "");
                                childHwStructure.setName(newName);
                            }
                        });
                    }
                }
                for (AbstractDigitalModule module : modules) {
                    if (model.hasConnection(parent, module, "submodules", null)) {
                        equivalent(module.getViewedVertex()).map(e -> (HwModule) e).ifPresent(hwModule -> {
                            parentHwStructure.getModules().add(hwModule);
                        });
                    }
                }
            });
        }
    }

    default void fromEdgesToConnections(ForSyDeSystemGraph model, Amalthea targetModel) {
        for (Edge e: model.edgeSet()) {
            if (e.edgeTraits.contains(EdgeTrait.AbstractPhysicalConnection) &&
                (AbstractStructure.conforms(e.getSource()) || AbstractDigitalModule.conforms(e.getSource())) &&
                (AbstractStructure.conforms(e.getTarget()) || AbstractDigitalModule.conforms(e.getTarget()))) {
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
                HwPort sourcePort = null;
                HwPort targetPort = null;
                // look for source port
                // TODO: Consider also structure!!!!!
                if (source instanceof HwModule && e.getSourcePort().isPresent()) {
                    HwModule sourceModule = (HwModule) source;
                    Optional<HwPort> sourceSearch = sourceModule.getPorts().stream().filter(p -> p.getName().equals(e.sourcePort)).findAny();
                    if (sourceSearch.isEmpty()) {
                        sourcePort = AmaltheaFactory.eINSTANCE.createHwPort();
                        sourcePort.setName(e.getSourcePort().get());
                        sourceModule.getPorts().add(sourcePort);
                    } else {
                        sourcePort = sourceSearch.get();
                    }
                }
                // look for target source port
                if (target instanceof HwModule && e.getTargetPort().isPresent()) {
                    HwModule targetModule = (HwModule) target;
                    Optional<HwPort> targetSearch = targetModule.getPorts().stream().filter(p -> p.getName().equals(e.targetPort)).findAny();
                    if (targetSearch.isEmpty()) {
                        targetPort = AmaltheaFactory.eINSTANCE.createHwPort();
                        targetPort.setName(e.getTargetPort().get());
                        targetModule.getPorts().add(targetPort);
                    } else {
                        targetPort = targetSearch.get();
                    }
                }
                // now to the parent
                HwConnection connection = AmaltheaFactory.eINSTANCE.createHwConnection();
                connection.setPort1(sourcePort);
                connection.setPort2(targetPort);
                connection.setName(e.toIDString());
                commonLeastParent.getConnections().add(connection);
            }
        }
    }
}
