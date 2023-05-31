package forsyde.io.java.amalthea.adapters.mixins;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.execution.*;
import forsyde.io.java.typed.viewers.execution.Task;
import forsyde.io.java.typed.viewers.impl.*;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ForSyDe2AmaltheaSWMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromForSyDeToSW(SystemGraph systemGraph, Amalthea amalthea) {
        amalthea.setSwModel(AmaltheaFactory.eINSTANCE.createSWModel());
        fromVertexToLabelAndChannels(systemGraph, amalthea);
        fromVertexToRunnables(systemGraph, amalthea);
        fromVertexToTasks(systemGraph, amalthea);
        fromEdgesToReadsAndWrites(systemGraph, amalthea);
        fromVertexAndEdgesToOsEvents(systemGraph, amalthea);
    }

    default void fromVertexToLabelAndChannels(SystemGraph systemGraph, Amalthea amalthea) {
        systemGraph.vertexSet().forEach(vertex -> {
            // decide if it is a label or a channel
            TokenizableDataBlock.safeCast(vertex).ifPresentOrElse(tokenizableDataBlock -> {
                final org.eclipse.app4mc.amalthea.model.Channel aChannel = AmaltheaFactory.eINSTANCE.createChannel();
                aChannel.setName(vertex.getIdentifier());

                final DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                dataSize.setValue(BigInteger.valueOf(tokenizableDataBlock.getTokenSizeInBits()));
                dataSize.setUnit(DataSizeUnit.BIT);
                aChannel.setSize(dataSize);

                aChannel.setMaxElements((int)(Math.floorDiv(tokenizableDataBlock.getMaxSizeInBits(), tokenizableDataBlock.getTokenSizeInBits()) + 1));
                amalthea.getSwModel().getChannels().add(aChannel);
                addEquivalence(vertex, aChannel);
            }, () -> {
                DataBlock.safeCast(vertex).ifPresent(dataBlock -> {
                    final Label label = AmaltheaFactory.eINSTANCE.createLabel();
                    label.setName(vertex.getIdentifier());

                    final DataSize dataSize = AmaltheaFactory.eINSTANCE.createDataSize();
                    dataSize.setValue(BigInteger.valueOf(dataBlock.getMaxSizeInBits()));
                    dataSize.setUnit(DataSizeUnit.BIT);
                    label.setSize(dataSize);

                    amalthea.getSwModel().getLabels().add(label);
                    addEquivalence(vertex, label);
                });
            });
        });
    }

    default void fromVertexAndEdgesToOsEvents(SystemGraph systemGraph, Amalthea amalthea) {
        // create a completed event for every task
        systemGraph.vertexSet().stream().flatMap(v -> Task.safeCast(v).stream()).forEach(task -> {
            final OsEvent osEvent = AmaltheaFactory.eINSTANCE.createOsEvent();
            osEvent.setName(task.getIdentifier() + "Completed");
            addEquivalence(task.getViewedVertex(), osEvent);
        });
        // now gatter all incoming triggers
        systemGraph.vertexSet().stream().flatMap(v -> Task.safeCast(v).stream()).forEach(task -> {
            equivalents(task.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task)
                    .map(v -> (org.eclipse.app4mc.amalthea.model.Task) v).forEach(aTask -> {
                        if (!task.getHasORSemantics()) {
                            // now gatter all incoming direct triggers
                            final List<OsEvent> incomingTaskCompleteEvents = systemGraph.incomingEdgesOf(task.getViewedVertex()).stream()
                                    .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                    .map(e -> equivalents(systemGraph.getEdgeSource(e)))
                                    .filter(o -> o instanceof OsEvent)
                                    .map(o -> (OsEvent) o)
                                    .collect(Collectors.toList());
                            // all incoming triggers through a downsample
                            final List<Downsample> incomingDownsample = systemGraph.incomingEdgesOf(task.getViewedVertex()).stream()
                                    .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                    .map(systemGraph::getEdgeSource)
                                    .flatMap(v -> Downsample.safeCast(v).stream())
                                    .collect(Collectors.toList());
                            final Map<Downsample, List<OsEvent>> downsampledOsEvents = incomingDownsample.stream().collect(Collectors.toMap(
                                    d -> d,
                                    d -> systemGraph.incomingEdgesOf(d.getViewedVertex()).stream()
                                            .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                            .map(e -> equivalents(systemGraph.getEdgeSource(e)))
                                            .filter(o -> o instanceof OsEvent)
                                            .map(o -> (OsEvent) o)
                                            .collect(Collectors.toList())
                            ));

                            final ClearEvent clearEvent = AmaltheaFactory.eINSTANCE.createClearEvent();
                            final WaitEvent waitEvent = AmaltheaFactory.eINSTANCE.createWaitEvent();
                            final EventMask eventMaskForClear = AmaltheaFactory.eINSTANCE.createEventMask();

                            eventMaskForClear.getEvents().addAll(incomingTaskCompleteEvents);
                            waitEvent.setMaskType(WaitEventType.AND);
                            waitEvent.setEventMask(eventMaskForClear);
                            waitEvent.setWaitingBehaviour(WaitingBehaviour.PASSIVE);
                            clearEvent.setEventMask(eventMaskForClear);
                            aTask.getActivityGraph().getItems().add(0, waitEvent);
                            aTask.getActivityGraph().getItems().add(1, clearEvent);

                            // also add the second level triggering, with counters
                            downsampledOsEvents.forEach((d, l) -> {
                                final ClearEvent clearEventDownsampled = AmaltheaFactory.eINSTANCE.createClearEvent();
                                final WaitEvent waitEventDownsampled = AmaltheaFactory.eINSTANCE.createWaitEvent();
                                final EventMask eventMaskForClearDownsampled = AmaltheaFactory.eINSTANCE.createEventMask();
                                final Counter counter = AmaltheaFactory.eINSTANCE.createCounter();
                                counter.setPrescaler(d.getRepetitivePredecessorSkips());
                                counter.setOffset(d.getInitialPredecessorSkips());

                                eventMaskForClearDownsampled.getEvents().addAll(l);
                                waitEventDownsampled.setMaskType(WaitEventType.AND);
                                waitEventDownsampled.setEventMask(eventMaskForClearDownsampled);
                                waitEventDownsampled.setWaitingBehaviour(WaitingBehaviour.PASSIVE);
                                waitEventDownsampled.setCounter(counter);
                                clearEventDownsampled.setEventMask(eventMaskForClearDownsampled);
                                aTask.getActivityGraph().getItems().add(0, waitEvent);
                                aTask.getActivityGraph().getItems().add(1, clearEvent);
                            });

                            // now for the outgoing section
                            final OsEvent finishedEvent = equivalents(task.getViewedVertex())
                                    .filter(o -> o instanceof OsEvent)
                                    .map(o -> (OsEvent) o)
                                    .findFirst().get();
                            final List<Upsample> outgoingUpsample = systemGraph.outgoingEdgesOf(task.getViewedVertex()).stream()
                                    .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                    .map(systemGraph::getEdgeTarget)
                                    .flatMap(v -> Upsample.safeCast(v).stream())
                                    .collect(Collectors.toList());
                            // now gatter all outgoing direct triggers
                            systemGraph.outgoingEdgesOf(task.getViewedVertex()).stream()
                                    .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                    .map(e -> equivalents(systemGraph.getEdgeTarget(e)))
                                    .filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task)
                                    .map(v -> (org.eclipse.app4mc.amalthea.model.Task) v)
                                    .forEach(dstTask -> {
                                        final SetEvent setEvent = AmaltheaFactory.eINSTANCE.createSetEvent();
                                        final EventMask eventMaskForSet = AmaltheaFactory.eINSTANCE.createEventMask();
                                        setEvent.setProcess(dstTask);
                                        eventMaskForSet.getEvents().add(finishedEvent);
                                        setEvent.setEventMask(eventMaskForSet);
                                        aTask.getActivityGraph().getItems().add(setEvent);
                                    });
                            // finally with upsampling
                            outgoingUpsample.forEach(upsample -> {
                                systemGraph.outgoingEdgesOf(upsample.getViewedVertex()).stream()
                                        .filter(e -> e.getSourcePort().equals("activated") && e.getTargetPort().equals("activators"))
                                        .map(e -> equivalents(systemGraph.getEdgeTarget(e)))
                                        .filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task)
                                        .map(v -> (org.eclipse.app4mc.amalthea.model.Task) v)
                                        .forEach(dstTask -> {
                                            final SetEvent setEvent = AmaltheaFactory.eINSTANCE.createSetEvent();
                                            final EventMask eventMaskForSet = AmaltheaFactory.eINSTANCE.createEventMask();
                                            final Counter counter = AmaltheaFactory.eINSTANCE.createCounter();
                                            counter.setPrescaler(upsample.getRepetitivePredecessorHolds());
                                            counter.setOffset(upsample.getInitialPredecessorHolds());
                                            setEvent.setProcess(dstTask);
                                            eventMaskForSet.getEvents().add(finishedEvent);
                                            setEvent.setEventMask(eventMaskForSet);
                                            aTask.getActivityGraph().getItems().add(setEvent);
                                        });
                            });

                        }
                    });
        });
        /*forSyDeSystemGraph.vertexSet().forEach(vertex -> {
            Downsample.safeCast(vertex).ifPresent(downsample -> {
                downsample.getActivatedPort(forSyDeSystemGraph).ifPresent(sucessor -> {
                    final ClearEvent clearEvent = AmaltheaFactory.eINSTANCE.createClearEvent();
                    final WaitEvent waitEvent = AmaltheaFactory.eINSTANCE.createWaitEvent();
                    final EventMask eventMaskForClear = AmaltheaFactory.eINSTANCE.createEventMask();
                    final EventMask eventMaskForWait = AmaltheaFactory.eINSTANCE.createEventMask();
                    final List<OsEvent> events = MultiANDReactiveStimulus.conforms(downsample) ?
                            MultiANDReactiveStimulus.safeCast(downsample).stream().flatMap(multiANDReactiveStimulus ->
                                multiANDReactiveStimulus.getPredecessorsPort(forSyDeSystemGraph).stream().map(predecessor -> {
                                    final OsEvent osEvent = AmaltheaFactory.eINSTANCE.createOsEvent();
                                    osEvent.setName(sucessor.getIdentifier() + "_Wait_" + predecessor.getIdentifier());
                                    addEquivalence(vertex, osEvent);
                                    amalthea.getSwModel().getEvents().add(osEvent);

                                    // put the predecessor code as we create them to avoid dealing with names later
                                    equivalents(predecessor.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(task -> {
                                        final SetEvent setEvent = AmaltheaFactory.eINSTANCE.createSetEvent();
                                        final EventMask eventMask = AmaltheaFactory.eINSTANCE.createEventMask();
                                        eventMask.getEvents().add(osEvent);
                                        setEvent.setEventMask(eventMask);
                                        // always add read to the beginning
                                        task.getActivityGraph().getItems().add(setEvent);
                                        equivalents(sucessor.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(setEvent::setProcess);
                                    });

                                    return osEvent;
                                })
                            ).collect(Collectors.toList()) : SimpleReactiveStimulus.safeCast(downsample).flatMap(simpleReactiveStimulus -> simpleReactiveStimulus.getPredecessorPort(forSyDeSystemGraph))
                            .map(predecessor -> {
                                final OsEvent osEvent = AmaltheaFactory.eINSTANCE.createOsEvent();
                                osEvent.setName(sucessor.getIdentifier() + "_Wait_" + predecessor.getIdentifier());
                                addEquivalence(vertex, osEvent);
                                amalthea.getSwModel().getEvents().add(osEvent);

                                // put the predecessor code as we create them to avoid dealing with names later
                                equivalents(predecessor.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(task -> {
                                    final SetEvent setEvent = AmaltheaFactory.eINSTANCE.createSetEvent();
                                    final EventMask eventMask = AmaltheaFactory.eINSTANCE.createEventMask();
                                    eventMask.getEvents().add(osEvent);
                                    setEvent.setEventMask(eventMask);
                                    // always add read to the beginning
                                    task.getActivityGraph().getItems().add(setEvent);
                                    equivalents(sucessor.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(setEvent::setProcess);
                                });

                                return osEvent;
                            }).stream().collect(Collectors.toList());

                    eventMaskForClear.getEvents().addAll(events);
                    eventMaskForWait.getEvents().addAll(events);
                    waitEvent.setEventMask(eventMaskForWait);
                    waitEvent.setMaskType(WaitEventType.AND);
                    waitEvent.setWaitingBehaviour(WaitingBehaviour.PASSIVE);
                    clearEvent.setEventMask(eventMaskForClear);
                    // always add read to the beginning
                    equivalents(sucessor.getViewedVertex()).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(task -> {
                        task.getActivityGraph().getItems().add(0, waitEvent);
                        task.getActivityGraph().getItems().add(1, clearEvent);
                    });
                });
            });
        });*/
    }

    default void fromVertexToRunnables(SystemGraph systemGraph, Amalthea amalthea) {
        systemGraph.vertexSet().forEach(vertex -> {
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

    default void fromVertexToTasks(SystemGraph systemGraph, Amalthea amalthea) {
        systemGraph.vertexSet().forEach(vertex -> {
            LoopingTask.safeCast(vertex).ifPresent(task -> {
                final org.eclipse.app4mc.amalthea.model.Task aTask = AmaltheaFactory.eINSTANCE.createTask();
                aTask.setName(task.getIdentifier());

                // create connections to the other runnables
                task.getLoopSequencePort(systemGraph).forEach(executable -> {
                    equivalents(executable.getViewedVertex()).filter(e -> e instanceof Runnable).map(e -> (Runnable) e).forEach(runnable -> {
                        final RunnableCall runnableCall = AmaltheaFactory.eINSTANCE.createRunnableCall();
                        runnableCall.setRunnable(runnable);
                        if (aTask.getActivityGraph() == null)
                            aTask.setActivityGraph(AmaltheaFactory.eINSTANCE.createActivityGraph());
                        aTask.getActivityGraph().getItems().add(runnableCall);
                    });
                });

                // check for periodic stimulus
//                PeriodicTask.safeCast(task).flatMap(periodicTask -> periodicTask.getPeriodicStimulusPort(forSyDeSystemGraph)).ifPresent(periodicStimulus -> {
//                    equivalents(periodicStimulus.getViewedVertex()).filter(e -> e instanceof PeriodicStimulus).map(e -> (PeriodicStimulus) e).forEach(aPeriodicStimulus -> {
//                        aTask.getStimuli().add(aPeriodicStimulus);
//                    });
//                });

                // now check for reactive stimulus
//                ReactiveTask.safeCast(task).stream().flatMap(reactiveTask -> reactiveTask.getReactiveStimulusPort(forSyDeSystemGraph).stream()).forEach(reactiveStimulus -> {
//                    equivalents(reactiveStimulus.getViewedVertex()).filter(e -> e instanceof InterProcessStimulus).map(e -> (InterProcessStimulus) e).forEach(interProcessStimulus -> {
//                        aTask.getStimuli().add(interProcessStimulus);
//                    });
//                });

                // add to the model
                amalthea.getSwModel().getTasks().add(aTask);
                addEquivalence(vertex, aTask);
            });

        });
    }

    default void fromEdgesToReadsAndWrites(SystemGraph systemGraph, Amalthea amalthea) {
        systemGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.IMPL_DATAMOVEMENT)).forEach(e -> {
            final Vertex src = systemGraph.getEdgeSource(e);
            final Vertex dst = systemGraph.getEdgeTarget(e);
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
            // same but for channels
            equivalents(src).filter(v -> v instanceof Channel).map(l -> (Channel) l).forEach(channel -> {
                equivalents(dst).filter(v -> v instanceof Runnable).map(r -> (Runnable) r).forEach(runnable -> {
                    final ChannelReceive channelReceive = AmaltheaFactory.eINSTANCE.createChannelReceive();
                    channelReceive.setData(channel);
                    final int elems = CommunicatingExecutable.safeCast(dst).map(communicatingExecutable -> communicatingExecutable.getPortDataReadSize().get(src.getIdentifier()).intValue()).orElse(1);
                    channelReceive.setElements(elems);
                    // always add read to the beginning
                    runnable.getRunnableItems().add(0, channelReceive);
                });
            });
            // now from runnable to label
            equivalents(src).filter(v -> v instanceof Runnable).map(l -> (Runnable) l).forEach(runnable -> {
                equivalents(dst).filter(v -> v instanceof Channel).map(r -> (Channel) r).forEach(channel -> {
                    final ChannelSend channelSend = AmaltheaFactory.eINSTANCE.createChannelSend();
                    channelSend.setData(channel);
                    final int elems = CommunicatingExecutable.safeCast(dst).map(communicatingExecutable -> communicatingExecutable.getPortDataWrittenSize().get(dst.getIdentifier()).intValue()).orElse(1);
                    channelSend.setElements(elems);
                    // always add write to the end
                    runnable.getRunnableItems().add(channelSend);
                });
            });
        });
    }

    /*default void fromVertexAndEdgesToOsEvents(ForSyDeSystemGraph forSyDeSystemGraph, Amalthea amalthea) {
        forSyDeSystemGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.EXECUTION_EVENTEDGE)).forEach(e -> {
            final Vertex src = forSyDeSystemGraph.getEdgeSource(e);
            final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
            // TODO: fix the AND semantics of incoming events
            // first from event to runnable
            equivalents(src).filter(v -> v instanceof OsEvent).map(l -> (OsEvent) l).forEach(osEvent -> {
                equivalents(dst).filter(v -> v instanceof org.eclipse.app4mc.amalthea.model.Task).map(r -> (org.eclipse.app4mc.amalthea.model.Task) r).forEach(task -> {
                    final ClearEvent clearEvent = AmaltheaFactory.eINSTANCE.createClearEvent();
                    final WaitEvent waitEvent = AmaltheaFactory.eINSTANCE.createWaitEvent();
                    final EventMask eventMaskForClear = AmaltheaFactory.eINSTANCE.createEventMask();
                    final EventMask eventMaskForWait = AmaltheaFactory.eINSTANCE.createEventMask();
                    eventMaskForClear.getEvents().add(osEvent);
                    eventMaskForWait.getEvents().add(osEvent);
                    waitEvent.setEventMask(eventMaskForWait);
                    waitEvent.setMaskType(WaitEventType.AND);
                    waitEvent.setWaitingBehaviour(WaitingBehaviour.PASSIVE);
                    clearEvent.setEventMask(eventMaskForClear);
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
                    ReactiveStimulus.safeCast(dst).flatMap(reactiveStimulus -> reactiveStimulus.getSuccessorPort(forSyDeSystemGraph)).ifPresent(dstTask -> {
                        equivalents(dstTask.getViewedVertex()).filter(v -> v instanceof Process).map(v -> (Process) v).forEach(setEvent::setProcess);
                    });
                });
            });
        });
    }*/

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
