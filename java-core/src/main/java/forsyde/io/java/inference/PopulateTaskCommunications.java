package forsyde.io.java.inference;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.typed.viewers.execution.CommunicatingTask;
import forsyde.io.java.typed.viewers.execution.LoopingTask;
import forsyde.io.java.typed.viewers.impl.CommunicatingExecutable;
import forsyde.io.java.typed.viewers.impl.DataBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This inference populates tasks that have executables but no communication information.
 * In this way, the communication of the task abstracts the data being sent and recieved
 * byt the children abstractions
 */
public class PopulateTaskCommunications implements SystemGraphInference {

    @Override
    public void infer(ForSyDeSystemGraph forSyDeSystemGraph) {
        forSyDeSystemGraph.vertexSet().stream().flatMap(v -> LoopingTask.safeCast(v).stream()).forEach(task -> {
            final Map<String, Long> writeMap = new HashMap<>();
            final Map<String, Long> readMap = new HashMap<>();
            forSyDeSystemGraph.vertexSet().stream().flatMap(v -> DataBlock.safeCast(v).stream()).forEach(dataBlock -> {
                // this code goes along the task executable to find the label communications.
                // basically it computes the task -> datablock based on all task -> exec -> datablock paths
                final Long writtenData = Stream.concat(task.getInitSequencePort(forSyDeSystemGraph).stream(), task.getLoopSequencePort(forSyDeSystemGraph).stream())
                        .flatMap(exec -> CommunicatingExecutable.safeCast(exec).stream())
                        .filter(cexec -> forSyDeSystemGraph.hasConnection(cexec, dataBlock))
                        .mapToLong(cexec ->
                                // now go through the edges to get the port going out and the amount of data written
                                forSyDeSystemGraph.getAllEdges(cexec.getViewedVertex(), dataBlock.getViewedVertex()).stream()
                                        .flatMap(e -> e.getSourcePort().stream())
                                        .mapToLong(outPort -> cexec.getPortDataWrittenSize().getOrDefault(outPort, dataBlock.getMaxSizeInBits()))
                                        .sum()
                        ).sum();
                if (writtenData > 0L) {
                    forSyDeSystemGraph.connect(task, dataBlock, dataBlock.getIdentifier(), EdgeTrait.IMPL_DATAMOVEMENT);
                    writeMap.put(dataBlock.getIdentifier(), writtenData);
                }

                final Long readData = Stream.concat(task.getInitSequencePort(forSyDeSystemGraph).stream(), task.getLoopSequencePort(forSyDeSystemGraph).stream())
                        .flatMap(exec -> CommunicatingExecutable.safeCast(exec).stream())
                        .filter(cexec -> forSyDeSystemGraph.hasConnection(dataBlock, cexec))
                        .mapToLong(cexec ->
                                // now go through the edges to get the port going out and the amount of data written
                                forSyDeSystemGraph.getAllEdges(dataBlock.getViewedVertex(), cexec.getViewedVertex()).stream()
                                        .flatMap(e -> e.getTargetPort().stream())
                                        .mapToLong(outPort -> cexec.getPortDataReadSize().getOrDefault(outPort, dataBlock.getMaxSizeInBits()))
                                        .sum()
                        ).sum();

                if (readData > 0L) {
                    forSyDeSystemGraph.connect(dataBlock, task, null, dataBlock.getIdentifier(), EdgeTrait.IMPL_DATAMOVEMENT);
                    readMap.put(dataBlock.getIdentifier(), readData);
                }
            });
            if (!writeMap.isEmpty() || !readMap.isEmpty()) {
                final CommunicatingTask communicatingTask = CommunicatingTask.enforce(task);
                communicatingTask.setPortDataReadSize(readMap);
                communicatingTask.setPortDataWrittenSize(writeMap);
            }
        });
    }

    @Override
    public String getName() {
        return "PopulateTaskCommunications";
    }
}
