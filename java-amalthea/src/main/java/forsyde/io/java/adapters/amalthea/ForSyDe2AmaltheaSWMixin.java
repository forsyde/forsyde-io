package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.execution.PeriodicTask;
import forsyde.io.java.typed.viewers.execution.ReactiveStimulus;
import forsyde.io.java.typed.viewers.execution.ReactiveTask;
import forsyde.io.java.typed.viewers.execution.Task;
import forsyde.io.java.typed.viewers.impl.DataBlock;
import forsyde.io.java.typed.viewers.impl.Executable;
import forsyde.io.java.typed.viewers.impl.InstrumentedExecutable;
import forsyde.io.java.typed.viewers.impl.TokenizableDataBlock;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.*;

import java.math.BigInteger;

public interface ForSyDe2AmaltheaSWMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromForSyDeToSW(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        amalthea.setSwModel(AmaltheaFactory.eINSTANCE.createSWModel());
        fromVertexToLabelAndChannels(forSyDeSystemGraph, amalthea);
        fromVertexToOSEvents(forSyDeSystemGraph, amalthea);
        fromVertexToRunnables(forSyDeSystemGraph, amalthea);
        fromVertexToTasks(forSyDeSystemGraph, amalthea);
        fromEdgesToReadsAndWrites(forSyDeSystemGraph, amalthea);
        fromEdgesToOSWaitsAndSignals(forSyDeSystemGraph, amalthea);
    }

    default void fromVertexToLabelAndChannels(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.vertexSet().forEach(vertex -> {
            // decide if it is a label or a channel
            TokenizableDataBlock.safeCast(vertex).ifPresentOrElse(tokenizableDataBlock -> {
                final org.eclipse.app4mc.amalthea.model.Channel aChannel = AmaltheaFactory.eINSTANCE.createChannel();
                aChannel.setName(vertex.getIdentifier());

                final DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                dataSize.setValue(BigInteger.valueOf(tokenizableDataBlock.getTokenSize()));
                dataSize.setUnit(DataSizeUnit.BIT);
                aChannel.setSize(dataSize);

                aChannel.setMaxElements((int)(Math.floorDiv(tokenizableDataBlock.getMaxSize(), tokenizableDataBlock.getTokenSize()) + 1));
                amalthea.getSwModel().getChannels().add(aChannel);
                addEquivalence(vertex, aChannel);
            }, () -> {
                DataBlock.safeCast(vertex).ifPresent(dataBlock -> {
                    final Label label = AmaltheaFactory.eINSTANCE.createLabel();
                    label.setName(vertex.getIdentifier());

                    final DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                    dataSize.setValue(BigInteger.valueOf(dataBlock.getMaxSize()));
                    dataSize.setUnit(DataSizeUnit.BIT);
                    label.setSize(dataSize);

                    amalthea.getSwModel().getLabels().add(label);
                    addEquivalence(vertex, label);
                });
            });
        });
    }

    default void fromVertexToOSEvents(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.vertexSet().forEach(vertex -> {
            ReactiveStimulus.safeCast(vertex).ifPresent(reactiveStimulus -> {
                final OsEvent osEvent = AmaltheaFactory.eINSTANCE.createOsEvent();
                osEvent.setName(reactiveStimulus.getIdentifier());
                addEquivalence(vertex, osEvent);
                amalthea.getSwModel().getEvents().add(osEvent);
            });
        });
    }

    default void fromVertexToRunnables(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.vertexSet().forEach(vertex -> {
            Executable.safeCast(vertex).ifPresent(executable -> {
                final Runnable runnable = AmaltheaFactory.eINSTANCE.createRunnable();
                runnable.setName(executable.getIdentifier());

                // check if it is instrumented and do more conversions
                InstrumentedExecutable.safeCast(executable).ifPresent(instrumentedExecutable -> {
                    final ExecutionNeed executionNeed = AmaltheaFactory.eINSTANCE.createExecutionNeed();
                    instrumentedExecutable.getOperationRequirements().forEach((opGroupName, opSet) -> {
                        opSet.forEach((opName, opVal) -> {
                            final DiscreteValueConstant discreteValueDeviation = AmaltheaFactory.eINSTANCE.createDiscreteValueConstant();
                            discreteValueDeviation.setValue(opVal);
                            executionNeed.getNeeds().put(opName, discreteValueDeviation);
                        });
                    });
                    runnable.getRunnableItems().add(executionNeed);
                });

                // add to the model
                amalthea.getSwModel().getRunnables().add(runnable);
                addEquivalence(vertex, runnable);
            });

        });
    }

    default void fromVertexToTasks(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.vertexSet().forEach(vertex -> {
            Task.safeCast(vertex).ifPresent(task -> {
                final org.eclipse.app4mc.amalthea.model.Task aTask = AmaltheaFactory.eINSTANCE.createTask();
                aTask.setName(task.getIdentifier());

                // create connections to the other runnables
                task.getCallSequencePort(forSyDeSystemGraph).forEach(executable -> {
                    equivalents(executable.getViewedVertex()).filter(e -> e instanceof Runnable).map(e -> (Runnable) e).forEach(runnable -> {
                        final RunnableCall runnableCall = AmaltheaFactory.eINSTANCE.createRunnableCall();
                        runnableCall.setRunnable(runnable);
                        if (aTask.getActivityGraph() == null)
                            aTask.setActivityGraph(AmaltheaFactory.eINSTANCE.createActivityGraph());
                        aTask.getActivityGraph().getItems().add(runnableCall);
                    });
                });

                // check for periodic stimulus
                PeriodicTask.safeCast(task).flatMap(periodicTask -> periodicTask.getPeriodicStimulusPort(forSyDeSystemGraph)).ifPresent(periodicStimulus -> {
                    equivalents(periodicStimulus.getViewedVertex()).filter(e -> e instanceof PeriodicStimulus).map(e -> (PeriodicStimulus) e).forEach(aPeriodicStimulus -> {
                        aTask.getStimuli().add(aPeriodicStimulus);
                    });
                });

                // now check for reactive stimulus
                ReactiveTask.safeCast(task).stream().flatMap(reactiveTask -> reactiveTask.getReactiveStimulusPort(forSyDeSystemGraph).stream()).forEach(reactiveStimulus -> {
                    equivalents(reactiveStimulus.getViewedVertex()).filter(e -> e instanceof InterProcessStimulus).map(e -> (InterProcessStimulus) e).forEach(interProcessStimulus -> {
                        aTask.getStimuli().add(interProcessStimulus);
                    });
                });

                // add to the model
                amalthea.getSwModel().getTasks().add(aTask);
                addEquivalence(vertex, aTask);
            });

        });
    }

    default void fromEdgesToReadsAndWrites(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.IMPL_DATAMOVEMENT)).forEach(e -> {
            final Vertex src = forSyDeSystemGraph.getEdgeSource(e);
            final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
            // first from label to runnable
            equivalents(src).filter(v -> v instanceof Label).map(l -> (Label) l).forEach(label -> {
                equivalents(dst).filter(v -> v instanceof Runnable).map(r -> (Runnable) r).forEach(runnable -> {
                    final LabelAccess labelAccess = AmaltheaFactory.eINSTANCE.createLabelAccess();
                    labelAccess.setAccess(LabelAccessEnum.READ);
                    labelAccess.setData(label);
                    // always add read to the beginning
                    runnable.getRunnableItems().add(0, labelAccess);
                });
            });
            // now from runnable to label
            equivalents(src).filter(v -> v instanceof Runnable).map(l -> (Runnable) l).forEach(runnable -> {
                equivalents(dst).filter(v -> v instanceof Label).map(r -> (Label) r).forEach(label -> {
                    final LabelAccess labelAccess = AmaltheaFactory.eINSTANCE.createLabelAccess();
                    labelAccess.setAccess(LabelAccessEnum.WRITE);
                    labelAccess.setData(label);
                    // always add write to the end
                    runnable.getRunnableItems().add(labelAccess);
                });
            });
            // TODO: fix when the runnables consume and take more than 1 element
            // same but for channels
            equivalents(src).filter(v -> v instanceof Channel).map(l -> (Channel) l).forEach(channel -> {
                equivalents(dst).filter(v -> v instanceof Runnable).map(r -> (Runnable) r).forEach(runnable -> {
                    final ChannelReceive channelReceive = AmaltheaFactory.eINSTANCE.createChannelReceive();
                    channelReceive.setData(channel);
                    channelReceive.setElements(1);
                    // always add read to the beginning
                    runnable.getRunnableItems().add(0, channelReceive);
                });
            });
            // now from runnable to label
            equivalents(src).filter(v -> v instanceof Runnable).map(l -> (Runnable) l).forEach(runnable -> {
                equivalents(dst).filter(v -> v instanceof Channel).map(r -> (Channel) r).forEach(channel -> {
                    final ChannelSend channelSend = AmaltheaFactory.eINSTANCE.createChannelSend();
                    channelSend.setData(channel);
                    channelSend.setElements(1);
                    // always add write to the end
                    runnable.getRunnableItems().add(channelSend);
                });
            });
        });
    }

    default void fromEdgesToOSWaitsAndSignals(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.EXECUTION_EVENTEDGE)).forEach(e -> {
            final Vertex src = forSyDeSystemGraph.getEdgeSource(e);
            final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
            // TODO: fix the AND semantics of incoming events
            // first from event to runnable
            equivalents(src).filter(v -> v instanceof OsEvent).map(l -> (OsEvent) l).forEach(osEvent -> {
                equivalents(dst).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(task -> {
                    final WaitEvent waitEvent = AmaltheaFactory.eINSTANCE.createWaitEvent();
                    final ClearEvent clearEvent = AmaltheaFactory.eINSTANCE.createClearEvent();
                    final EventMask eventMask = AmaltheaFactory.eINSTANCE.createEventMask();
                    eventMask.getEvents().add(osEvent);
                    waitEvent.setEventMask(eventMask);
                    waitEvent.setMaskType(WaitEventType.AND);
                    waitEvent.setWaitingBehaviour(WaitingBehaviour.PASSIVE);
                    clearEvent.setEventMask(eventMask);
                    // always add read to the beginning
                    task.getActivityGraph().getItems().add(0, waitEvent);
                    task.getActivityGraph().getItems().add(1, clearEvent);
                });
            });
            // now from runnable to event
            equivalents(src).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(l -> (org.eclipse.app4mc.amalthea.model.Task) l).forEach(task -> {
                equivalents(dst).filter(v -> v instanceof OsEvent).map(r -> (OsEvent) r).forEach(osEvent -> {
                    final SetEvent setEvent = AmaltheaFactory.eINSTANCE.createSetEvent();
                    final EventMask eventMask = AmaltheaFactory.eINSTANCE.createEventMask();
                    eventMask.getEvents().add(osEvent);
                    setEvent.setEventMask(eventMask);
                    // always add read to the beginning
                    task.getActivityGraph().getItems().add(setEvent);
                });
            });
        });
    }

    private long fromTimeUnitToLong(TimeUnit timeUnit) {
        switch (timeUnit) {
            case S:
                return 1L;
            case MS:
                return 1000L;
            case US:
                return 1000000L;
            case NS:
                return 1000000000L;
            //case 1000000000000: return TimeUnit.PS;
            default:
                return 1000000000000L;
        }
    }
}
