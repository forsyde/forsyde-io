package forsyde.io.core.inference;

import forsyde.io.core.SystemGraph;

/**
 * This inference populates tasks that have executables but no communication information.
 * In this way, the communication of the task abstracts the data being sent and recieved
 * byt the children abstractions
 */
public class PopulateTaskCommunications implements SystemGraphInference {

    @Override
    public void infer(final SystemGraph systemGraph) {
//        systemGraph.vertexSet().stream().flatMap(v -> LoopingTask.safeCast(v).stream()).forEach(task -> {
//            final Map<String, Long> writeMap = new HashMap<>();
//            final Map<String, Long> readMap = new HashMap<>();
//            systemGraph.vertexSet().stream().flatMap(v -> DataBlock.safeCast(v).stream()).forEach(dataBlock -> {
//                // this code goes along the task executable to find the label communications.
//                // basically it computes the task -> datablock based on all task -> exec -> datablock paths
//                final Long writtenData = Stream.concat(task.getInitSequencePort(systemGraph).stream(), task.getLoopSequencePort(systemGraph).stream())
//                        .flatMap(exec -> CommunicatingExecutable.safeCast(exec).stream())
//                        .filter(cexec -> systemGraph.hasConnection(cexec, dataBlock))
//                        .mapToLong(cexec ->
//                                // now go through the edges to get the port going out and the amount of data written
//                                systemGraph.getAllEdges(cexec.getViewedVertex(), dataBlock.getViewedVertex()).stream()
//                                        .flatMap(e -> e.getSourcePort().stream())
//                                        .mapToLong(outPort -> cexec.getPortDataWrittenSize().getOrDefault(outPort, dataBlock.getMaxSizeInBits()))
//                                        .sum()
//                        ).sum();
//                if (writtenData > 0L) {
//                    systemGraph.connect(task, dataBlock, dataBlock.getIdentifier(), EdgeTrait.IMPL_DATAMOVEMENT);
//                    writeMap.put(dataBlock.getIdentifier(), writtenData);
//                }
//
//                final Long readData = Stream.concat(task.getInitSequencePort(systemGraph).stream(), task.getLoopSequencePort(systemGraph).stream())
//                        .flatMap(exec -> CommunicatingExecutable.safeCast(exec).stream())
//                        .filter(cexec -> systemGraph.hasConnection(dataBlock, cexec))
//                        .mapToLong(cexec ->
//                                // now go through the edges to get the port going out and the amount of data written
//                                systemGraph.getAllEdges(dataBlock.getViewedVertex(), cexec.getViewedVertex()).stream()
//                                        .flatMap(e -> e.getTargetPort().stream())
//                                        .mapToLong(outPort -> cexec.getPortDataReadSize().getOrDefault(outPort, dataBlock.getMaxSizeInBits()))
//                                        .sum()
//                        ).sum();
//
//                if (readData > 0L) {
//                    systemGraph.connect(dataBlock, task, null, dataBlock.getIdentifier(), EdgeTrait.IMPL_DATAMOVEMENT);
//                    readMap.put(dataBlock.getIdentifier(), readData);
//                }
//            });
//            if (!writeMap.isEmpty() || !readMap.isEmpty()) {
//                final CommunicatingTask communicatingTask = CommunicatingTask.enforce(task);
//                communicatingTask.setPortDataReadSize(readMap);
//                communicatingTask.setPortDataWrittenSize(writeMap);
//            }
//        });
    }

    @Override
    public String getName() {
        return "PopulateTaskCommunications";
    }
}
