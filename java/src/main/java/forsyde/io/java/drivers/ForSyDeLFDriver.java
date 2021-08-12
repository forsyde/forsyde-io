package forsyde.io.java.drivers;


import com.google.inject.Injector;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.LinguaFrancaSignalViewer;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.lflang.LFStandaloneSetup;
import org.lflang.lf.*;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ForSyDeLFDriver implements ForSyDeModelDriver {

    @Inject
    ResourceSet resourceSet;


    ForSyDeLFDriver() {
        final Injector injector = new LFStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet = injector.getInstance(ResourceSet.class);
    }

    @Override
    public ForSyDeModel loadModel(Path inPath) throws Exception {
        final Resource res = resourceSet.getResource(URI.createURI(inPath.toAbsolutePath().toString()), true);
        return fromLFtoForSyDe((Model) res.getContents().get(0));
    }

    @Override
    public ForSyDeModel loadModel(InputStream in) throws Exception {
        final Resource res = resourceSet.getResource(URI.createURI("inmemory.lf"), true);
        res.load(in, resourceSet.getLoadOptions());
        return fromLFtoForSyDe((Model) res.getContents().get(0));
    }

    @Override
    public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
        throw new Exception("Lingua Franca writing is not supported");
    }

    protected ForSyDeModel fromLFtoForSyDe(Model model) {
        final ForSyDeModel forSyDeModel = new ForSyDeModel();
        final Reactor mainReactor = model.getReactors().stream().filter(Reactor::isMain).findAny().orElseThrow();
        Instantiation mainInstance = LfFactory.eINSTANCE.createInstantiation();
        mainInstance.setName("Main");
        mainInstance.setReactorClass(mainReactor);
        processLFInstanceToForSyDeReactor(forSyDeModel, mainInstance, "");
        return forSyDeModel;
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeModel model, Timer timer) {
        return fromLFTimerToForsyDeTimer(model, timer, "", "");
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeModel model, Timer timer, String prefix) {
        return fromLFTimerToForsyDeTimer(model, timer, prefix, "");
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeModel model, Timer timer, String prefix, String suffix) {
        Vertex timerVertex = new Vertex(prefix +  timer.getName() + suffix, VertexTrait.LinguaFrancaTimer);
        model.addVertex(timerVertex);
        if (timer.getPeriod().getTime() != null) {
            timerVertex.putProperty("period_numerator_per_sec", timer.getPeriod().getTime().getInterval());
            timerVertex.putProperty("period_denominator_per_sec", fromLFTimeUnitToSecondsDenominator(timer.getPeriod().getTime().getUnit()));
        }
        if (timer.getOffset().getTime() != null) {
            timerVertex.putProperty("offset_numerator_per_sec", timer.getOffset().getTime().getInterval());
            timerVertex.putProperty("offset_denominator_per_sec", fromLFTimeUnitToSecondsDenominator(timer.getOffset().getTime().getUnit()));
        }
        return timerVertex;
    }

    protected Integer fromLFTimeUnitToSecondsDenominator(TimeUnit timeUnit) {
        switch (timeUnit) {
            case MSECS:
            case MSEC: return 1000;
            case NSECS:
            case NSEC: return 1000000000;
            case USECS:
            case USEC: return 1000000;
            default: return 1;
        }
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeModel model, Reaction reaction) {
        return processLFReactionToForSyDeReaction(model, reaction, "", "");
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeModel model, Reaction reaction, String prefix) {
        return processLFReactionToForSyDeReaction(model, reaction, prefix, "");
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeModel model, Reaction reaction, String prefix, String suffix) {
        Vertex reactionVertex = new Vertex(prefix + "reaction" + suffix, VertexTrait.LinguaFrancaReaction);
        model.addVertex(reactionVertex);
        for (TriggerRef triggerRef : reaction.getTriggers()) {
            if (triggerRef instanceof VarRef) {
                VarRef varRef = (VarRef) triggerRef;
                reactionVertex.ports.add(varRef.getVariable().getName());
            }
        }
        for (VarRef varRef : reaction.getEffects()) {
            reactionVertex.ports.add(varRef.getVariable().getName());
        }
        return reactionVertex;
    }

    protected Vertex processLFInstanceToForSyDeReactor(ForSyDeModel model, Instantiation instantiation, String prefix) {
        return processLFInstanceToForSyDeReactor(model, instantiation, prefix, "");
    }

    protected Vertex processLFInstanceToForSyDeReactor(ForSyDeModel model, Instantiation instantiation, String prefix, String suffix) {
        final Map<Timer, Vertex> timerToVertex = new HashMap<>();
        final Map<Instantiation, Vertex> childInstancesToVertex = new HashMap<>();
        final String instantiationName = prefix + instantiation.getName();
        final Vertex instantiationVertex = new Vertex(instantiationName, VertexTrait.LinguaFrancaReactor);
        instantiationVertex.ports.add("timers");
        instantiationVertex.ports.add("reactions");
        instantiationVertex.ports.add("children");
        model.addVertex(instantiationVertex);
        if (instantiation.getReactorClass() instanceof Reactor) {
            Reactor reactor = (Reactor) instantiation.getReactorClass();
            // first recurse through all children
            for (final Instantiation childInstantiation : reactor.getInstantiations()) {
                Vertex childInstanceVertex = processLFInstanceToForSyDeReactor(model, childInstantiation, instantiationName + ".");
                model.addVertex(childInstanceVertex);
                model.connect(instantiationVertex, childInstanceVertex, "children", EdgeTrait.LinguaFrancaContainment);
                childInstancesToVertex.put(childInstantiation, childInstanceVertex);
            }
            for (final Port port : reactor.getInputs()) {
                instantiationVertex.ports.add(port.getName());
            }
            for (final Port port : reactor.getOutputs()) {
                instantiationVertex.ports.add(port.getName());
            }
            for (final Timer timer : reactor.getTimers()) {
                final Vertex timerVertex = fromLFTimerToForsyDeTimer(model, timer, instantiationName + ".");
                model.connect(instantiationVertex, timerVertex, "timers", EdgeTrait.LinguaFrancaConnection);
                timerToVertex.put(timer, timerVertex);
            }
            for (int i = 0; i < reactor.getReactions().size(); i++) {
                final Reaction reaction = reactor.getReactions().get(i);
                final Vertex reactionVertex = processLFReactionToForSyDeReaction(model, reaction, instantiationName + ".", String.valueOf(i));
                model.connect(instantiationVertex, reactionVertex, "reactions", EdgeTrait.LinguaFrancaConnection);
                for (final TriggerRef triggerRef : reaction.getTriggers()) {
                    if (triggerRef instanceof VarRef) {
                        VarRef varRef = (VarRef) triggerRef;
                        if(varRef.getVariable() instanceof Port) {
                            final Port triggerPort = (Port) varRef.getVariable();
                            String triggerName = triggerPort.getName();
                            model.connect(instantiationVertex, reactionVertex, triggerName, triggerName, EdgeTrait.LinguaFrancaTrigger);
                        } else if (varRef.getVariable() instanceof Timer) {
                            final Timer triggerTimer = (Timer) varRef.getVariable();
                            Vertex timerVertex = timerToVertex.get(triggerTimer);
                            model.connect(timerVertex, reactionVertex, null, triggerTimer.getName(), EdgeTrait.LinguaFrancaTrigger);
                        }
                    }
                }
                for (final VarRef varRef : reaction.getEffects()) {
                    if(varRef.getVariable() instanceof Port) {
                        final Port triggerPort = (Port) varRef.getVariable();
                        String triggerName = triggerPort.getName();
                        model.connect(reactionVertex, instantiationVertex, triggerName, triggerName, EdgeTrait.LinguaFrancaTrigger);
                    } else if (varRef.getVariable() instanceof Timer) {
                        final Timer triggerTimer = (Timer) varRef.getVariable();
                        Vertex timerVertex = timerToVertex.get(triggerTimer);
                        model.connect(reactionVertex, timerVertex, null, triggerTimer.getName(), EdgeTrait.LinguaFrancaTrigger);
                    }
                }
            }
            // TODO makes this size work as well
            instantiationVertex.putProperty("state_names", reactor.getStateVars().stream().map(StateVar::getName).collect(Collectors.toList()));
            instantiationVertex.putProperty("state_sizes_in_bytes", reactor.getStateVars().stream().map(StateVar::getType)
                    .map(this::varTypeToSize).collect(Collectors.toList()));
            for (final Connection connection : reactor.getConnections()) {
                for (final VarRef inVarRef : connection.getLeftPorts()) {
                    for (final VarRef outVarRef : connection.getRightPorts()) {
                        final Vertex connectionSrc = inVarRef.getContainer() == null ?
                                instantiationVertex : childInstancesToVertex.get(inVarRef.getContainer());
                        final Vertex connectionDst = outVarRef.getContainer() == null ?
                                instantiationVertex : childInstancesToVertex.get(outVarRef.getContainer());
                        final String inPort = inVarRef.getVariable() instanceof Port ? ((Port) inVarRef.getVariable()).getName() : "";
                        final String outPort = outVarRef.getVariable() instanceof Port ? ((Port) outVarRef.getVariable()).getName() : "";
                        final Vertex signal = new Vertex(connectionSrc.identifier
                                + "." + inPort +  "-" + connectionDst.identifier + "." + outPort, VertexTrait.LinguaFrancaSignal);
                        model.addVertex(signal);
                        signal.ports.add("source_reaction");
                        signal.ports.add("target_reaction");
                        // TODO make this size work
                        signal.putProperty("size_in_bytes", 0L);
                        model.connect(connectionSrc, signal, inPort, "source_reaction", EdgeTrait.LinguaFrancaConnection);
                        model.connect(signal, connectionDst, "target_reaction", outPort, EdgeTrait.LinguaFrancaConnection);
                    }
                }
            }
        }
        return instantiationVertex;
    }

    protected Long varTypeToSize(Type type) {
        return 0L;
    }

}
