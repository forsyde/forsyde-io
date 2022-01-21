package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaReaction;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaReactor;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaSignal;
import forsyde.io.java.typed.viewers.moc.linguafranca.LinguaFrancaTimer;
import org.eclipse.app4mc.amalthea.model.*;
import org.eclipse.app4mc.amalthea.model.Runnable;

import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;

public interface LF2AmaltheaAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default  void convertToSWModel(ForSyDeSystemGraph model, Amalthea amalthea) {
        if (amalthea.getSwModel() == null) amalthea.setSwModel(AmaltheaFactory.eINSTANCE.createSWModel());
        if (amalthea.getStimuliModel() == null) amalthea.setStimuliModel(AmaltheaFactory.eINSTANCE.createStimuliModel());

        convertToStimuli(model, amalthea);
        convertToReactors(model, amalthea);
        convertFromSignals(model, amalthea);
    }

    default  void convertToStimuli(ForSyDeSystemGraph model, Amalthea amalthea) {
        model.vertexSet()
        .stream().filter(LinguaFrancaTimer::conforms)
        .map(v -> LinguaFrancaTimer.safeCast(v).get())
        .forEach(timer -> {
            final Time time = AmaltheaFactory.eINSTANCE.createTime();
            time.setUnit(fromDenominatorToTimeUnit(timer.getPeriodDenominatorPerSec()));
            time.setValue(BigInteger.valueOf(timer.getPeriodNumeratorPerSec()));
            // try to find if the timer already exists or not, and if not, create it
            final PeriodicStimulus stimulus = amalthea.getStimuliModel().getStimuli().stream()
                    .filter(st -> st instanceof PeriodicStimulus)
                    .map(st -> (PeriodicStimulus) st)
                    .filter(periodicStimulus -> periodicStimulus.getRecurrence().getValue().equals(time.getValue()) &&
                            periodicStimulus.getRecurrence().getUnit().equals(time.getUnit()))
                    .findAny()
                    .orElseGet(() -> {
                        final PeriodicStimulus newStimulus = AmaltheaFactory.eINSTANCE.createPeriodicStimulus();
                        newStimulus.setRecurrence(time);
                        newStimulus.setName("PeriodicStimulusOf" + timer.getPeriodNumeratorPerSec().toString() + "_" + timer.getPeriodDenominatorPerSec().toString());
                        amalthea.getStimuliModel().getStimuli().add(newStimulus);
                        return newStimulus;
                    });
            /*final PeriodicStimulus stimulus = AmaltheaFactory.eINSTANCE.createPeriodicStimulus();
            final Time time = AmaltheaFactory.eINSTANCE.createTime();
            time.setUnit(TimeUnit.S);
            time.setValue(BigInteger.valueOf(timer.getPeriodNumeratorPerSec() / timer.getPeriodDenominatorPerSec()));
            stimulus.setRecurrence(time);
            stimulus.setName("PeriodicStimulusOf" + timer.getPeriodNumeratorPerSec().toString() + "_" + timer.getPeriodDenominatorPerSec().toString());
            int pos = amalthea.getStimuliModel().getStimuli().indexOf(stimulus);
            if (pos < 0) {
                pos = amalthea.getStimuliModel().getStimuli().size();
                amalthea.getStimuliModel().getStimuli().add(stimulus);
            }*/
            addEquivalence(timer.getViewedVertex(), stimulus);
        });
    }

    default  void convertToReactors(ForSyDeSystemGraph model, Amalthea amalthea) {
        final Set<LinguaFrancaReactor> reactors = model.vertexSet()
                .stream().filter(LinguaFrancaReactor::conforms)
                .map(v -> LinguaFrancaReactor.safeCast(v).get())
                .collect(Collectors.toSet());
        for (LinguaFrancaReactor reactor : reactors) {
            for (LinguaFrancaReaction reaction : reactor.getReactionsPort(model)) {
                final Task task = AmaltheaFactory.eINSTANCE.createTask();
                final ActivityGraph activityGraph = AmaltheaFactory.eINSTANCE.createActivityGraph();
                final RunnableCall activityGraphItem = AmaltheaFactory.eINSTANCE.createRunnableCall();
                final Runnable runnable = AmaltheaFactory.eINSTANCE.createRunnable();
                final String newId = reaction.getViewedVertex().getIdentifier().replace(".", "_");
                task.setName(newId);
                task.setPreemption(Preemption.NON_PREEMPTIVE);
                runnable.setName(newId + "Runnable");

                activityGraphItem.setRunnable(runnable);
                activityGraph.getItems().add(activityGraphItem);
                task.setActivityGraph(activityGraph);

                amalthea.getSwModel().getRunnables().add(runnable);
                amalthea.getSwModel().getTasks().add(task);

                addEquivalence(reaction.getViewedVertex(), task);
                addEquivalence(reaction.getViewedVertex(), runnable);

                reactor.getTimersPort(model).stream()
                .filter(t -> model.hasConnection(t, reaction) || model.hasConnection(reaction, t))
                .forEach(t -> {
                    equivalent(t.getViewedVertex()).map(o -> (Stimulus) o).ifPresent(stimulus -> {
                        task.getStimuli().add(stimulus);
                    });
                });
            }
        }
    }

    default  void convertFromSignals(ForSyDeSystemGraph model, Amalthea amalthea) {
        final Set<LinguaFrancaSignal> signals = model.vertexSet()
                .stream().filter(LinguaFrancaSignal::conforms)
                .map(v -> LinguaFrancaSignal.safeCast(v).get())
                .collect(Collectors.toSet());
        for (LinguaFrancaSignal signal : signals) {
            final Label label = AmaltheaFactory.eINSTANCE.createLabel();
            label.setConstant(false);
            final DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
            dataSize.setValue(BigInteger.valueOf(signal.getSizeInBits()));
            dataSize.setUnit(DataSizeUnit.BIT);
            final String newId = signal.getViewedVertex().getIdentifier().replace(".", "_").replace("-", "_");
            label.setSize(dataSize);
            label.setName(newId);
            amalthea.getSwModel().getLabels().add(label);

            addEquivalence(signal.getViewedVertex(), label);


            // the next piece of code assume the following pattern,
            // reaction -> reactor -> signal -> reactor -> reaction
            // all in the correct ports
            model.incomingEdgesOf(signal.getViewedVertex()).forEach(eIn -> {
                model.outgoingEdgesOf(signal.getViewedVertex()).forEach(eOut -> {
                    final Vertex source = model.getEdgeSource(eIn);
                    final Vertex target = model.getEdgeTarget(eOut);
                    // both source and target should be reactors
                    if (LinguaFrancaReactor.conforms(source) && LinguaFrancaReactor.conforms(target)) {
                        final LinguaFrancaReactor sourceReactor = LinguaFrancaReactor.safeCast(source).get();
                        final LinguaFrancaReactor targetReactor = LinguaFrancaReactor.safeCast(target).get();
                        sourceReactor.getReactionsPort(model).forEach(sourceReaction -> {
                            targetReactor.getReactionsPort(model).forEach(targetReaction -> {
                                // the connected reactions should have the same port too
                                if (eIn.getSourcePort().isPresent() && sourceReaction.getPorts().contains(eIn.getSourcePort().get()) &&
                                        eOut.getTargetPort().isPresent() && targetReaction.getPorts().contains(eOut.getTargetPort().get())) {
                                    // make the appropriate castings
                                    equivalents(sourceReaction.getViewedVertex()).filter(e -> e instanceof Task).map(e -> (Task) e)
                                    .forEach(sourceTask -> {
                                        equivalents(targetReaction.getViewedVertex()).filter(e -> e instanceof Task).map(e -> (Task) e)
                                        .forEach(targetTask -> {
                                            final Runnable sourceRunnable = ((RunnableCall) sourceTask.getActivityGraph().getItems().get(0)).getRunnable();
                                            final Runnable targetRunnable = ((RunnableCall) targetTask.getActivityGraph().getItems().get(0)).getRunnable();

                                            // finally make the label read and writes
                                            final LabelAccess sourceLabelAccess = AmaltheaFactory.eINSTANCE.createLabelAccess();
                                            final LabelAccess targetLabelAccess = AmaltheaFactory.eINSTANCE.createLabelAccess();
                                            sourceLabelAccess.setAccess(LabelAccessEnum.WRITE);
                                            sourceLabelAccess.setData(label);
                                            targetLabelAccess.setAccess(LabelAccessEnum.READ);
                                            targetLabelAccess.setData(label);

                                            if (sourceRunnable.getActivityGraph() == null) sourceRunnable.setActivityGraph(AmaltheaFactory.eINSTANCE.createActivityGraph());
                                            if (targetRunnable.getActivityGraph() == null) targetRunnable.setActivityGraph(AmaltheaFactory.eINSTANCE.createActivityGraph());

                                            sourceRunnable.getActivityGraph().getItems().add(sourceLabelAccess);
                                            targetRunnable.getActivityGraph().getItems().add(targetLabelAccess);
                                        });
                                    });
                                }
                            });
                        });
                    }
                });
            });
        }
    }

    private TimeUnit fromDenominatorToTimeUnit(Integer denominator) {
        switch (denominator) {
            case 1: return TimeUnit.S;
            case 1000: return TimeUnit.MS;
            case 1000000: return TimeUnit.US;
            case 1000000000: return TimeUnit.NS;
            //case 1000000000000: return TimeUnit.PS;
            default: return TimeUnit.PS;
        }
    }

}
