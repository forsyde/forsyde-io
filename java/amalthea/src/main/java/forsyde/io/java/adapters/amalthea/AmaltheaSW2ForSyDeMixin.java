package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.execution.*;
import forsyde.io.java.typed.viewers.execution.Channel;
import forsyde.io.java.typed.viewers.execution.Task;
import forsyde.io.java.typed.viewers.impl.Executable;
import forsyde.io.java.typed.viewers.impl.ExecutableViewer;
import forsyde.io.java.typed.viewers.impl.InstrumentedExecutable;
import forsyde.io.java.typed.viewers.impl.InstrumentedExecutableViewer;
import org.eclipse.app4mc.amalthea.model.*;
import org.eclipse.app4mc.amalthea.model.PeriodicStimulus;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public interface AmaltheaSW2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromSWToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        fromLabelToVertex(amalthea, forSyDeSystemGraph);
        fromRunnableToVertex(amalthea, forSyDeSystemGraph);
        fromTaskToVertex(amalthea, forSyDeSystemGraph);
    }

    default void fromRunnableToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getRunnables().forEach(runnable -> {
            final Vertex runnableVertex = new Vertex(runnable.getName(), VertexTrait.IMPL_EXECUTABLE);
            forSyDeSystemGraph.addVertex(runnableVertex);
            if (runnable.getRunnableItems() != null) {
                runnable.getRunnableItems().forEach(item -> {
                    // if it is read or write
                    if (item instanceof LabelAccess) {
                        final LabelAccess labelAccess = (LabelAccess) item;
                        // assume that the label creation is completed
                        equivalent(((LabelAccess) item).getData()).ifPresent(labelVertex -> {
                            switch (labelAccess.getAccess()) {
                                case READ:
                                    forSyDeSystemGraph.connect(
                                            labelVertex,
                                            runnableVertex,
                                            null,
                                            ((LabelAccess) item).getData().getName(),
                                            EdgeTrait.EXECUTION_COMMUNICATIONEDGE
                                    );
                                case WRITE:
                                    forSyDeSystemGraph.connect(
                                            runnableVertex,
                                            labelVertex,
                                            ((LabelAccess) item).getData().getName(),
                                            EdgeTrait.EXECUTION_COMMUNICATIONEDGE
                                    );
                            }
                        });
                    }
                    // if it is an execution through "ticks"
                    else if (item instanceof Ticks) {
                        final Ticks ticks = (Ticks) item;
                        runnableVertex.addTraits(VertexTrait.IMPL_INSTRUMENTEDEXECUTABLE);
                        final InstrumentedExecutable instrumentedExecutable = new InstrumentedExecutableViewer(runnableVertex);
                        if (ticks.getDefault() != null) {
                            instrumentedExecutable.setOperationRequirements(
                                    Map.of("default", Map.of("tick", ticks.getDefault().getUpperBound()))
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
                                Map.of("default",
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
            addEquivalence(runnable, runnableVertex);
        });
    }

    default void fromTaskToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getTasks().forEach(task -> {
            final Vertex taskVertex = new Vertex(task.getName(), VertexTrait.EXECUTION_TASK);
            forSyDeSystemGraph.addVertex(taskVertex);
            final Task fTask = new TaskViewer(taskVertex);
            if (task.getStimuli() != null) {
                task.getStimuli().forEach(stimulus -> {
                    if (stimulus instanceof PeriodicStimulus) {
                        taskVertex.addTraits(VertexTrait.EXECUTION_PERIODICTASK);
                        equivalent(stimulus).ifPresent(stimulusVertex -> {
                            forSyDeSystemGraph.connect(stimulusVertex, taskVertex, "stimulated", "periodicStimulus", EdgeTrait.EXECUTION_EVENTEDGE);
                        });
                    }
                });
            }
            if (task.getActivityGraph() != null) {
                // do tree search due to possible nesting
                task.getActivityGraph().eAllContents().forEachRemaining(item -> {
                    if (item instanceof RunnableCall) {
                        final RunnableCall runnableCall = (RunnableCall) item;
                        equivalent(runnableCall.getRunnable()).ifPresent(runnableVertex -> {
                            forSyDeSystemGraph.connect(taskVertex, runnableVertex, "callSequence", EdgeTrait.EXECUTION_CONTAINMENTEDGE);
                        });
                    }
                });
            }
            addEquivalence(task, taskVertex);
        });
    }

    default void fromLabelToVertex(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        amalthea.getSwModel().getLabels().forEach(label -> {
            final Vertex channelVertex = new Vertex(label.getName(), VertexTrait.EXECUTION_CHANNEL);
            forSyDeSystemGraph.addVertex(channelVertex);
            final Channel channel = new ChannelViewer(channelVertex);
            channel.setMaxElems(1);
            // if the number of bits is not gigantic, it should be OK.
            // TODO: maybe think of a arbitrary size way to store this?
            channel.setElemSizeInBits((int) label.getSize().getNumberBits());
            addEquivalence(label, channelVertex);
        });
    }
}
