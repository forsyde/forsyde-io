package forsyde.io.java.adapters;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaSignal;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaSignalViewer;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaTimer;
import org.lflang.TimeUnit;
import org.lflang.lf.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LinguaFrancaAdapter implements ModelAdapter<Model> {


    @Override
    public ForSyDeSystemGraph convert(Model inputModel) {
        return fromLFtoForSyDe(inputModel);
    }

    @Override
    public Model convert(ForSyDeSystemGraph inputModel) {
        return null;
    }

    protected ForSyDeSystemGraph fromLFtoForSyDe(Model model) {
        final ForSyDeSystemGraph forSyDeSystemGraph = new ForSyDeSystemGraph();
        final Reactor mainReactor = model.getReactors().stream().filter(Reactor::isMain).findAny().orElseThrow();
        Instantiation mainInstance = LfFactory.eINSTANCE.createInstantiation();
        mainInstance.setName("Main");
        mainInstance.setReactorClass(mainReactor);
        processLFInstanceToForSyDeReactor(forSyDeSystemGraph, mainInstance, "");
        return forSyDeSystemGraph;
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeSystemGraph model, Timer timer) {
        return fromLFTimerToForsyDeTimer(model, timer, "", "");
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeSystemGraph model, Timer timer, String prefix) {
        return fromLFTimerToForsyDeTimer(model, timer, prefix, "");
    }

    protected Vertex fromLFTimerToForsyDeTimer(ForSyDeSystemGraph model, Timer timer, String prefix, String suffix) {
        Vertex timerVertex = new Vertex(prefix +  timer.getName() + suffix);
        LinguaFrancaTimer linguaFrancaTimer = LinguaFrancaTimer.enforce(timerVertex);
        model.addVertex(timerVertex);
        if (timer.getPeriod().getTime() != null) {
            linguaFrancaTimer.setPeriodNumeratorInSec(timer.getPeriod().getTime().getInterval());
            linguaFrancaTimer.setPeriodDenominatorInSec(fromLFTimeUnitToSecondsDenominator(timer.getPeriod().getTime().getUnit()));
        }
        if (timer.getOffset().getTime() != null) {
            linguaFrancaTimer.setOffsetNumeratorInSec(timer.getOffset().getTime().getInterval());
            linguaFrancaTimer.setOffsetDenominatorInSec(fromLFTimeUnitToSecondsDenominator(timer.getOffset().getTime().getUnit()));
        }
        return timerVertex;
    }

    protected Integer fromLFTimeUnitToSecondsDenominator(String timeUnit) {
        if (timeUnit.equalsIgnoreCase(TimeUnit.MILLI.getCanonicalName())) return 1000;
        else if (timeUnit.equalsIgnoreCase(TimeUnit.MICRO.getCanonicalName())) return 1000000;
        else if (timeUnit.equalsIgnoreCase(TimeUnit.NANO.getCanonicalName())) return 1000000000;
        else return 1;
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeSystemGraph model, Reaction reaction) {
        return processLFReactionToForSyDeReaction(model, reaction, "", "");
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeSystemGraph model, Reaction reaction, String prefix) {
        return processLFReactionToForSyDeReaction(model, reaction, prefix, "");
    }

    protected Vertex processLFReactionToForSyDeReaction(ForSyDeSystemGraph model, Reaction reaction, String prefix, String suffix) {
        Vertex reactionVertex = new Vertex(prefix + "reaction" + suffix, VertexTrait.MOC_LINGUAFRANCA_LINGUAFRANCAREACTION);
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

    protected Vertex processLFInstanceToForSyDeReactor(ForSyDeSystemGraph model, Instantiation instantiation, String prefix) {
        return processLFInstanceToForSyDeReactor(model, instantiation, prefix, "");
    }

    protected Vertex processLFInstanceToForSyDeReactor(ForSyDeSystemGraph model, Instantiation instantiation, String prefix, String suffix) {
        final Map<Timer, Vertex> timerToVertex = new HashMap<>();
        final Map<Instantiation, Vertex> childInstancesToVertex = new HashMap<>();
        final String instantiationName = prefix + instantiation.getName();
        final Vertex instantiationVertex = new Vertex(instantiationName, VertexTrait.MOC_LINGUAFRANCA_LINGUAFRANCAREACTOR);
        instantiationVertex.ports.add("timers");
        instantiationVertex.ports.add("reactions");
        instantiationVertex.ports.add("children_reactors");
        model.addVertex(instantiationVertex);
        if (instantiation.getReactorClass() instanceof Reactor) {
            Reactor reactor = (Reactor) instantiation.getReactorClass();
            for (final Port port : reactor.getInputs()) {
                instantiationVertex.ports.add(port.getName());
            }
            for (final Port port : reactor.getOutputs()) {
                instantiationVertex.ports.add(port.getName());
            }
            // first recurse through all children and add the ordering
            // explicitly save the ordering of the children reactors
            Map<String, Integer> childrenReactorsOrdering = new HashMap<>();
            int childIdx = 0;
            for (final Instantiation childInstantiation : reactor.getInstantiations()) {
                Vertex childInstanceVertex = processLFInstanceToForSyDeReactor(model, childInstantiation, instantiationName + ".");
                model.addVertex(childInstanceVertex);
                model.connect(instantiationVertex, childInstanceVertex, "children_reactors", EdgeTrait.MOC_LINGUAFRANCA_CONTAINMENTEDGE);
                childrenReactorsOrdering.put(childInstanceVertex.identifier, childIdx);
                childInstancesToVertex.put(childInstantiation, childInstanceVertex);
                childIdx += 1;
            }
            for (final Timer timer : reactor.getTimers()) {
                final Vertex timerVertex = fromLFTimerToForsyDeTimer(model, timer, instantiationName + ".");
                model.connect(instantiationVertex, timerVertex, "timers", EdgeTrait.MOC_LINGUAFRANCA_CONTAINMENTEDGE);
                timerToVertex.put(timer, timerVertex);
            }
            Map<String, Integer> reactionsOrdering = new HashMap<>();
            for (int i = 0; i < reactor.getReactions().size(); i++) {
                final Reaction reaction = reactor.getReactions().get(i);
                final Vertex reactionVertex = processLFReactionToForSyDeReaction(model, reaction, instantiationName + ".", String.valueOf(i));
                model.connect(instantiationVertex, reactionVertex, "reactions", EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                reactionsOrdering.put(reactionVertex.identifier, i);
                reactionVertex.putProperty("size_in_bits", 0L);
                reactionVertex.ports.add("implementation");
                final Vertex functionVertex = new Vertex(reactionVertex.identifier + ".body", VertexTrait.IMPL_EXECUTABLE);
                model.addVertex(functionVertex);
                model.connect(reactionVertex, functionVertex, "implementation", EdgeTrait.MOC_LINGUAFRANCA_CONTAINMENTEDGE);
                functionVertex.putProperty("source_code", reaction.getCode().getBody() == null ? "" : reaction.getCode().getBody());
                for (final TriggerRef triggerRef : reaction.getTriggers()) {
                    if (triggerRef instanceof VarRef) {
                        VarRef varRef = (VarRef) triggerRef;
                        if(varRef.getVariable() instanceof Port) {
                            final Port triggerPort = (Port) varRef.getVariable();
                            String triggerName = triggerPort.getName();
                            reactionVertex.ports.add(triggerName);
                            model.connect(instantiationVertex, reactionVertex, triggerName, triggerName, EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                        } else if (varRef.getVariable() instanceof Timer) {
                            final Timer triggerTimer = (Timer) varRef.getVariable();
                            Vertex timerVertex = timerToVertex.get(triggerTimer);
                            reactionVertex.ports.add(triggerTimer.getName());
                            model.connect(timerVertex, reactionVertex, null, triggerTimer.getName(), EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                        }
                    }
                }
                for (final VarRef varRef : reaction.getEffects()) {
                    if(varRef.getVariable() instanceof Port) {
                        final Port triggerPort = (Port) varRef.getVariable();
                        String triggerName = triggerPort.getName();
                        reactionVertex.ports.add(triggerName);
                        model.connect(reactionVertex, instantiationVertex, triggerName, triggerName, EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                    } else if (varRef.getVariable() instanceof Timer) {
                        final Timer triggerTimer = (Timer) varRef.getVariable();
                        Vertex timerVertex = timerToVertex.get(triggerTimer);
                        reactionVertex.ports.add(triggerTimer.getName());
                        model.connect(reactionVertex, timerVertex, null, triggerTimer.getName(), EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                    }
                }
            }
            instantiationVertex.putProperty("__children_reactors_ordering__", childrenReactorsOrdering);
            instantiationVertex.putProperty("__reactions_ordering__", reactionsOrdering);
            instantiationVertex.putProperty("state_names", reactor.getStateVars().stream().map(StateVar::getName).collect(Collectors.toList()));
            instantiationVertex.putProperty("state_sizes_in_bits", reactor.getStateVars().stream().map(StateVar::getType)
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
                                + "." + inPort +  "-" + connectionDst.identifier + "." + outPort, VertexTrait.MOC_LINGUAFRANCA_LINGUAFRANCASIGNAL);
                        final LinguaFrancaSignal linguaFrancaSignal = new LinguaFrancaSignalViewer(signal);
                        final Long signalSize = inVarRef.getVariable() instanceof Port ? varTypeToSize(((Port) inVarRef.getVariable()).getType()) : 0L;
                        model.addVertex(signal);
                        signal.ports.add("source_reaction");
                        signal.ports.add("target_reaction");
                        linguaFrancaSignal.setSizeInBits(signalSize);
                        linguaFrancaSignal.setPropagationDelayInSecsNumerator(
                                connection.getDelay() != null ? (long) connection.getDelay().getInterval() : 0L
                        );
                        linguaFrancaSignal.setPropagationDelayInSecsDenominator(
                                connection.getDelay() != null ? (long) fromLFTimeUnitToSecondsDenominator(connection.getDelay().getUnit()) : 1L
                        );
                        // signal.putProperty("size_in_bits", signalSize);
                        model.connect(connectionSrc, signal, inPort, "source_reaction", EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                        model.connect(signal, connectionDst, "target_reaction", outPort, EdgeTrait.MOC_LINGUAFRANCA_EVENTEDGE);
                    }
                }
            }
        }
        return instantiationVertex;
    }

    protected Long varTypeToSize(Type type) {
        long bitMultiplier = 1L;
        switch (type.getId()) {
            case "short":
                bitMultiplier = 4L;
                break;
            case "byte":
            case "char":
                bitMultiplier = 8L;
                break;
            case "integer":
            case "unsigned int":
            case "unsigned integer":
            case "int":
                bitMultiplier = 16L;
                break;
            case "word":
            case "unsigned long":
            case "long":
            case "float":
                bitMultiplier = 32L;
                break;
            case "double":
            case "long long":
                bitMultiplier = 64L;
                break;
        }
        if (type.getArraySpec() == null || type.getArraySpec().isOfVariableLength()) {
            return bitMultiplier;
        } else {
            return type.getArraySpec().getLength() * bitMultiplier;
        }
    }

}
