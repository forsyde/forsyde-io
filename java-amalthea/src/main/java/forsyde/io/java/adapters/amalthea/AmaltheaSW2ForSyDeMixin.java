package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.execution.*;
import forsyde.io.java.typed.viewers.execution.PeriodicStimulus;
import forsyde.io.java.typed.viewers.execution.Task;
import forsyde.io.java.typed.viewers.impl.*;
import forsyde.io.java.typed.viewers.visualization.GreyBox;
import org.antlr.v4.codegen.model.Loop;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface AmaltheaSW2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromSWToForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getSwModel() != null) {
            convertActivations(amalthea, forSyDeSystemGraph);
            fromLabelToVertex(amalthea, forSyDeSystemGraph);
            fromChannelToVertex(amalthea, forSyDeSystemGraph);
            fromRunnableToVertex(amalthea, forSyDeSystemGraph);
            fromTaskToVertex(amalthea, forSyDeSystemGraph);
            fromSetEventsToEdges(amalthea, forSyDeSystemGraph);
            //convertProcessPrototype(amalthea, forSyDeSystemGraph);
        }
    }

    default void fromRunnableToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getRunnables().forEach(runnable -> {
            final Vertex runnableVertex = new Vertex(runnable.getName(), VertexTrait.VISUALIZATION_VISUALIZABLE);
            final Executable executable = Executable.enforce(runnableVertex);
            forSyDeSystemGraph.addVertex(runnableVertex);
            if (runnable.getRunnableItems() != null) {
                runnable.getRunnableItems().forEach(item -> {
                    // if it is read or write
                    if (item instanceof LabelAccess) {
                        final CommunicatingExecutable communicatingExecutable =
                                CommunicatingExecutable.enforce(runnableVertex);
                        final LabelAccess labelAccess = (LabelAccess) item;
                        // assume that the label creation is completed
                        equivalents(((LabelAccess) item).getData()).flatMap(v -> DataBlock.safeCast(v).stream())
                                .forEach(dataBlock -> {
                            final Label label = ((LabelAccess) item).getData();
                            runnableVertex.ports.add(((LabelAccess) item).getData().getName());
                            switch (labelAccess.getAccess()) {
                                case READ:
                                    final Map<String, Long> reads = communicatingExecutable.getPortDataReadSize();
                                    reads.put(label.getName(), label.getSize() != null ? label.getSize().getNumberBits() : 0);
                                    forSyDeSystemGraph.connect(
                                            dataBlock,
                                            executable,
                                            null,
                                            ((LabelAccess) item).getData().getName(),
                                            EdgeTrait.IMPL_DATAMOVEMENT,
                                            EdgeTrait.VISUALIZATION_VISUALCONNECTION
                                    );
                                    communicatingExecutable.setPortDataReadSize(reads);
                                    break;
                                case WRITE:
                                    final Map<String, Long> writes = communicatingExecutable.getPortDataWrittenSize();
                                    writes.put(label.getName(), label.getSize() != null ? label.getSize().getNumberBits() : 0);
                                    forSyDeSystemGraph.connect(
                                            executable,
                                            dataBlock,
                                            ((LabelAccess) item).getData().getName(),
                                            EdgeTrait.IMPL_DATAMOVEMENT,
                                            EdgeTrait.VISUALIZATION_VISUALCONNECTION
                                    );
                                    communicatingExecutable.setPortDataWrittenSize(writes);
                                    break;
                            }
                        });
                    }
                    // if it is recieving from a channel
                    else if (item instanceof ChannelReceive) {
                        final ChannelReceive channelReceive = (ChannelReceive) item;
                        final Channel aChannel = channelReceive.getData();
                        final CommunicatingExecutable communicatingExecutable =
                                CommunicatingExecutable.enforce(runnableVertex);
                        runnableVertex.ports.add(aChannel.getName());
                        equivalents(channelReceive.getData()).flatMap(v -> TokenizableDataBlock.safeCast(v).stream())
                                .forEach(tokenizableDataBlock -> {
                                    final Map<String, Long> reads = communicatingExecutable.getPortDataReadSize();
                                    reads.put(aChannel.getName(), channelReceive.getElements() == 0 ? aChannel.getSize().getNumberBits() : tokenizableDataBlock.getTokenSizeInBits() * channelReceive.getElements());
                                    communicatingExecutable.setPortDataReadSize(reads);
                                    forSyDeSystemGraph.connect(
                                            tokenizableDataBlock,
                                            executable,
                                            null,
                                            aChannel.getName(),
                                            EdgeTrait.IMPL_DATAMOVEMENT,
                                            EdgeTrait.VISUALIZATION_VISUALCONNECTION
                                    );
                                });
                    } else if (item instanceof ChannelSend) {
                        final ChannelSend channelSend = (ChannelSend) item;
                        final Channel aChannel = channelSend.getData();
                        final CommunicatingExecutable communicatingExecutable =
                                CommunicatingExecutable.enforce(runnableVertex);
                        runnableVertex.ports.add(aChannel.getName());
                        equivalents(channelSend.getData()).flatMap(v -> TokenizableDataBlock.safeCast(v).stream())
                                .forEach(tokenizableDataBlock -> {
                                    final Map<String, Long> writes = communicatingExecutable.getPortDataWrittenSize();
                                    writes.put(aChannel.getName(), channelSend.getElements() == 0 ? aChannel.getSize().getNumberBits() : tokenizableDataBlock.getTokenSizeInBits() * channelSend.getElements());
                                    communicatingExecutable.setPortDataWrittenSize(writes);
                                    forSyDeSystemGraph.connect(
                                            executable,
                                            tokenizableDataBlock,
                                            aChannel.getName(),
                                            EdgeTrait.IMPL_DATAMOVEMENT,
                                            EdgeTrait.VISUALIZATION_VISUALCONNECTION
                                    );
                                });
                    }
                    // if it is an execution through "ticks"
                    else if (item instanceof Ticks) {
                        final Ticks ticks = (Ticks) item;
                        final InstrumentedExecutable instrumentedExecutable = InstrumentedExecutable.enforce(runnableVertex);
                        if (ticks.getDefault() != null) {
                            instrumentedExecutable.setOperationRequirements(
                                    Map.of("defaultTicks", Map.of("tick", ticks.getDefault().getUpperBound()))
                            );
                        } else if (ticks.getExtended() != null) {
                            instrumentedExecutable.setOperationRequirements(
                                ticks.getExtended().stream().collect(Collectors.toMap(
                                        entry -> entry.getKey().getName(),
                                        entry -> Map.of("ticks", entry.getValue().getUpperBound())
                                ))
                            );
                        }
                    }
                    // finally, if it is the executio needs
                    else if (item instanceof ExecutionNeed) {
                        runnableVertex.addTraits(VertexTrait.IMPL_INSTRUMENTEDEXECUTABLE);
                        final InstrumentedExecutable instrumentedExecutable = new InstrumentedExecutableViewer(runnableVertex);
                        final ExecutionNeed executionNeed = (ExecutionNeed) item;
                        if (executionNeed.getNeeds() != null) {
                            instrumentedExecutable.setOperationRequirements(
                                Map.of("defaultNeeds",
                                    executionNeed.getNeeds().stream().collect(Collectors.toMap(
                                            entry -> entry.getKey(),
                                            entry -> entry.getValue().getUpperBound()
                                    ))
                                )
                            );
                        }
                    }
                });
            }
            if (runnable.getSize() != null) {
                runnableVertex.addTraits(VertexTrait.IMPL_INSTRUMENTEDEXECUTABLE);
                final InstrumentedExecutable instrumentedExecutable = new InstrumentedExecutableViewer(runnableVertex);
                instrumentedExecutable.setSizeInBits(runnable.getSize().getNumberBits());
            }
            // make sure that if its instrumented, it has some default value until
            // ForSyDe IO uses defaults values properly
            else if (InstrumentedExecutable.conforms(runnableVertex)) {
                final InstrumentedExecutable instrumentedExecutable = new InstrumentedExecutableViewer(runnableVertex);
                instrumentedExecutable.setSizeInBits(0L);
            }
            addEquivalence(runnable, runnableVertex);
        });
    }

    default void fromTaskToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getTasks().forEach(aTask -> {
            final Vertex taskVertex = new Vertex(aTask.getName());
            final GreyBox taskGreyBox = GreyBox.enforce(taskVertex);
            forSyDeSystemGraph.addVertex(taskVertex);
            addEquivalence(aTask, taskVertex);
            final LoopingTask task = LoopingTask.enforce(taskVertex);
            // first, go in the activity graph and figure out if the task awaits for something
            if (aTask.getActivityGraph() != null) {
                // do tree search due to possible nesting
                aTask.getActivityGraph().eAllContents().forEachRemaining(item -> {
                    if (item instanceof RunnableCall) {
                        final CommunicatingTask communicatingTask = CommunicatingTask.enforce(task);
                        final RunnableCall runnableCall = (RunnableCall) item;
                        equivalent(runnableCall.getRunnable()).ifPresent(runnableVertex -> {
                            forSyDeSystemGraph.connect(taskVertex, runnableVertex, "callSequence", EdgeTrait.EXECUTION_EXECUTIONEDGE);
                            forSyDeSystemGraph.connect(taskVertex, runnableVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                        });
                    } else if (item instanceof WaitEvent) {
                        final WaitEvent waitEvent = (WaitEvent) item;
                        if (waitEvent.getEventMask() != null && waitEvent.getEventMask().getEvents().size() > 0) {
                            final String eventNames = waitEvent.getEventMask().getEvents().stream().map(OsEvent::getName).collect(Collectors.joining("_"));
                            final Vertex precedence = new Vertex(task.getIdentifier() + "_Wait_" + eventNames);
                            final Stimulatable precedenceSti = Stimulatable.enforce(precedence);
                            forSyDeSystemGraph.addVertex(precedence);
                            addEquivalence(aTask, precedence);
                            precedenceSti.setHasORSemantics(waitEvent.getMaskType() == WaitEventType.OR);
                            if (waitEvent.getEventMask().getEvents().size() == 1) {
                                final Downsample downsample = Downsample.enforce(precedence);
                                forSyDeSystemGraph.connect(downsample, task, "activated", "activators", EdgeTrait.EXECUTION_EVENTEDGE);
                                if (waitEvent.getCounter() != null) {
                                    downsample.setInitialPredecessorSkips(waitEvent.getCounter().getOffset());
                                    downsample.setRepetitivePredecessorSkips(waitEvent.getCounter().getPrescaler());
                                }
                            }
                        }
                    }
//                    else if (item instanceof InterProcessTrigger) {
//                        final InterProcessTrigger interProcessTrigger = (InterProcessTrigger) item;
//                        equivalents(interProcessTrigger.getStimulus()).forEach(precedenceVertex -> {
//                            SimpleReactiveStimulus.safeCast(precedenceVertex).ifPresent(simpleReactiveStimulus -> {
//                                simpleReactiveStimulus.setPredecessorPort(forSyDeSystemGraph, fTask);
//                            });
//                            // if there are multiple incoming stimulus
//                            ReactiveANDStimulus.safeCast(precedenceVertex).ifPresent(reactiveANDStimulus -> {
//                                Set<Task> predecessors = reactiveANDStimulus.getPredecessorsPort(forSyDeSystemGraph);
//                                if (predecessors == null) {
//                                    predecessors = new HashSet<>();
//                                }
//                                predecessors.add(fTask);
//                                reactiveANDStimulus.setPredecessorsPort(forSyDeSystemGraph, predecessors);
//                            });
//                            //forSyDeSystemGraph.connect(taskVertex, precedenceVertex, null,"predecessor", EdgeTrait.EXECUTION_EVENTEDGE);
//                        });
//                    }
                });
            }
            if (aTask.getStimuli() != null) {
                aTask.getStimuli().forEach(stimulus -> {
                    equivalents(stimulus).flatMap(v -> PeriodicStimulus.safeCast(v).stream()).forEach(periodicStimulus -> {
                        forSyDeSystemGraph.connect(periodicStimulus, task, "activated", "activators", EdgeTrait.EXECUTION_EVENTEDGE);
                    });
//                    if (stimulus instanceof PeriodicStimulus) {
//                        taskVertex.addTraits(VertexTrait.EXECUTION_PERIODICTASK);
//                        taskVertex.ports.add("periodicStimulus");
//                        equivalent(stimulus).ifPresent(stimulusVertex -> {
//                            forSyDeSystemGraph.connect(stimulusVertex, taskVertex, "stimulated", "periodicStimulus", EdgeTrait.EXECUTION_EVENTEDGE);
//                        });
//                    } else if (stimulus instanceof InterProcessStimulus) {
//                        final InterProcessStimulus interProcessStimulus = (InterProcessStimulus) stimulus;
//                        final Task Task = Task.enforce(taskVertex);
//                        // all input reactions are AND semantics coming from InterProcessTrigger
//                        equivalents(interProcessStimulus).forEach(reactionVertex -> {
//                            ReactiveStimulus.safeCast(reactionVertex).ifPresent(reactiveStimulus -> {
//                                reactiveStimulus.setSuccessorPort(forSyDeSystemGraph, Task);
//                            });

                            //forSyDeSystemGraph.connect(reactionVertex, taskVertex, "sucessor", "reactiveStimulus", EdgeTrait.EXECUTION_EVENTEDGE);
//
//                            if (Task.getReactiveStimulusPort(forSyDeSystemGraph).size() > 1) {
//
//                            } else {
//
//                            }
//                            final List<Integer> reactiveGroups = Task.getReactiveANDGroups() == null ?
//                                new ArrayList<>() :
//                                Task.getReactiveANDGroups();
//                            reactiveGroups.add(0);
//                            Task.setReactiveANDGroups(reactiveGroups);
//                        });
//                    }
                });
            }
        });
    }

    default void fromSetEventsToEdges(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getTasks().forEach(aTask -> {
            if (aTask.getActivityGraph() != null) {
                equivalents(aTask).flatMap(v -> Task.safeCast(v).stream()).forEach(task -> {
                    // do tree search due to possible nesting
                    aTask.getActivityGraph().eAllContents().forEachRemaining(item -> {
                        if (item instanceof SetEvent) {
                            final SetEvent setEvent = (SetEvent) item;
                            if (setEvent.getEventMask() != null && setEvent.getProcess() != null && setEvent.getEventMask().getEvents().size() > 0) {
                                equivalents(setEvent.getProcess()).flatMap(v -> Downsample.safeCast(v).stream()).forEach(downsample -> {
                                    forSyDeSystemGraph.connect(task, downsample, "activated", "activators", EdgeTrait.EXECUTION_EVENTEDGE);
//                                    SimpleReactiveStimulus.safeCast(vertex).ifPresent(simpleReactiveStimulus -> {
//                                        simpleReactiveStimulus.setPredecessorPort(forSyDeSystemGraph, task);
//                                    });
//                                    MultiANDReactiveStimulus.safeCast(vertex).ifPresent(multiANDReactiveStimulus -> {
//                                        final Set<Task> cur = multiANDReactiveStimulus.getPredecessorsPort(forSyDeSystemGraph);
//                                        cur.add(task);
//                                        multiANDReactiveStimulus.setPredecessorsPort(forSyDeSystemGraph, cur);
//                                    });
                                });
                            }
                        }
                    });
                });
            }
        });
    }

    default void fromLabelToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getLabels().forEach(label -> {
            final Vertex channelVertex = new Vertex(label.getName(), VertexTrait.VISUALIZATION_VISUALIZABLE);
            final DataBlock dataBlock = DataBlock.enforce(channelVertex);
            forSyDeSystemGraph.addVertex(channelVertex);
            // if the number of bits is not gigantic, it should be OK.
            if (label.getSize() != null) {
                dataBlock.setMaxSizeInBits(label.getSize().getNumberBits());
            }
            addEquivalence(label, channelVertex);
        });
    }

    default void fromChannelToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getChannels().forEach(aChannel -> {
            final Vertex channelVertex = new Vertex(aChannel.getName(), VertexTrait.VISUALIZATION_VISUALIZABLE);
            forSyDeSystemGraph.addVertex(channelVertex);
            final TokenizableDataBlock tokenizableDataBlock = TokenizableDataBlock.enforce(channelVertex);
            // if the number of bits is not gigantic, it should be OK.
            if (aChannel.getSize() != null) {
                tokenizableDataBlock.setTokenSizeInBits(aChannel.getSize().getNumberBits() / aChannel.getMaxElements());
                tokenizableDataBlock.setMaxSizeInBits(aChannel.getSize().getNumberBits());
            } else {
                tokenizableDataBlock.setTokenSizeInBits(0L);
                tokenizableDataBlock.setMaxSizeInBits(0L);
            }
            addEquivalence(aChannel, channelVertex);
        });
    }

    default void convertActivations(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getActivations().forEach(activation -> {
            final Vertex timerVertex = new Vertex(activation.getName(), VertexTrait.EXECUTION_PERIODICSTIMULUS);
            final forsyde.io.java.typed.viewers.execution.PeriodicStimulus periodicStimulusVertex =
                    forsyde.io.java.typed.viewers.execution.PeriodicStimulus.enforce(timerVertex);
            forSyDeSystemGraph.addVertex(timerVertex);
            if (activation instanceof PeriodicActivation) {
                final PeriodicActivation periodicActivation = (PeriodicActivation) activation;
                if (periodicActivation.getRecurrence() != null) {
                    periodicStimulusVertex.setPeriodNumerator(periodicActivation.getRecurrence().getValue().longValue());
                    periodicStimulusVertex.setPeriodDenominator(fromTimeUnitToLong(periodicActivation.getRecurrence().getUnit()));
                } else {
                    periodicStimulusVertex.setPeriodNumerator(1L);
                    periodicStimulusVertex.setPeriodDenominator(1L);
                }
                if (periodicActivation.getOffset() != null) {
                    periodicStimulusVertex.setOffsetNumerator(periodicActivation.getOffset().getValue().longValue());
                    periodicStimulusVertex.setOffsetDenominator(fromTimeUnitToLong(periodicActivation.getOffset().getUnit()));
                } else {
                    periodicStimulusVertex.setOffsetNumerator(0L);
                    periodicStimulusVertex.setOffsetDenominator(1L);
                }
                addEquivalence(activation, timerVertex);
            }
        });
    }

//    default void convertProcessPrototype(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
//        amalthea.getSwModel().getProcessPrototypes().forEach(processPrototype ->
//            processPrototype.getOrderPrecedenceSpec().forEach(orderPrecedenceSpec ->
//                equivalent(orderPrecedenceSpec.getOrigin()).ifPresent(originRunnableVertex ->
//                    equivalent(orderPrecedenceSpec.getTarget()).ifPresent(targetRunnableVertex -> {
//                        // first check if any of the created tasks contain this runnable
//                        // otherwise create a forsyde task just for it
//                        final Task originTask = forSyDeSystemGraph.incomingEdgesOf(originRunnableVertex).stream()
//                                .map(forSyDeSystemGraph::getEdgeSource)
//                                .filter(Task::conforms)
//                                .map(Task::enforce)
//                                .findAny()
//                                .orElseGet(() -> {
//                                    final Vertex srcTaskVertex = new Vertex(originRunnableVertex.identifier + "Task",
//                                            VertexTrait.EXECUTION_TASK);
//                                    final Task srcTaskViewer = Task.enforce(srcTaskVertex);
//                                    forSyDeSystemGraph.addVertex(srcTaskVertex);
//                                    forSyDeSystemGraph.connect(srcTaskVertex, originRunnableVertex, "callSequence", EdgeTrait.EXECUTION_CONTAINMENTEDGE);
//                                    return srcTaskViewer;
//                                });
//                        // first check if any of the created tasks contain this runnable
//                        // otherwise create a forsyde task just for it [same for target]
//                        final Task targetTask = forSyDeSystemGraph.incomingEdgesOf(targetRunnableVertex).stream()
//                                .map(forSyDeSystemGraph::getEdgeSource)
//                                .filter(Task::conforms)
//                                .map(Task::enforce)
//                                .findAny()
//                                .orElseGet(() -> {
//                                    final Vertex dstTaskVertex = new Vertex(targetRunnableVertex.identifier + "Task",
//                                            VertexTrait.EXECUTION_TASK);
//                                    final Task dstTaskViewer = Task.enforce(dstTaskVertex);
//                                    forSyDeSystemGraph.addVertex(dstTaskVertex);
//                                    forSyDeSystemGraph.connect(dstTaskVertex, targetRunnableVertex, "callSequence", EdgeTrait.EXECUTION_CONTAINMENTEDGE);
//                                    return dstTaskViewer;
//                                });
//                        addEquivalence(processPrototype, originTask.getViewedVertex());
//                        addEquivalence(processPrototype, targetTask.getViewedVertex());
//                        // then add the precedence if necessary
//                        if (!originTask.equals(targetTask)) {
//                            final Vertex precedenceConstraintVertex = new Vertex(
//                                    originRunnableVertex.getIdentifier() + "_to_" + targetRunnableVertex.getIdentifier(),
//                                    VertexTrait.EXECUTION_PRECEDENCECONSTRAINT);
//                            final PrecedenceConstraint precedenceConstraint = PrecedenceConstraint.enforce(precedenceConstraintVertex);
//                            forSyDeSystemGraph.addVertex(precedenceConstraintVertex);
//                            forSyDeSystemGraph.connect(precedenceConstraintVertex, originTask.getViewedVertex(), "predecessor",
//                                    EdgeTrait.EXECUTION_CONSTRAINTEDGE);
//                            forSyDeSystemGraph.connect(precedenceConstraintVertex, targetTask.getViewedVertex(), "sucessor",
//                                    EdgeTrait.EXECUTION_CONSTRAINTEDGE);
//                            addEquivalence(processPrototype, precedenceConstraintVertex);
//                        }
//                    })
//                )
//            )
//        );
//    }

    private long fromTimeUnitToLong(TimeUnit timeUnit) {
        switch (timeUnit) {
            case S: return 1L;
            case MS: return 1000L;
            case US: return 1000000L;
            case NS: return 1000000000L;
            //case 1000000000000: return TimeUnit.PS;
            default: return 1000000000000L;
        }
    }
}
