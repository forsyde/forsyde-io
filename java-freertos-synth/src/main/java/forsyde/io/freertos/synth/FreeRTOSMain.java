package forsyde.io.freertos.synth;

import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.implementation.synthetizable.PeriodicTask;
import forsyde.io.lib.hierarchy.platform.runtime.FixedPriorityScheduledRuntime;
import org.ainslec.picocog.PicoWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents the root of one FreeRTOS runtime.
 * That is, of one programmable entry point, regardless if it is working
 * in SMP mode and controls multiple processing elements.
 */
public record FreeRTOSMain(
        FixedPriorityScheduledRuntime mainRuntime
) implements Synthetizable {

    public Set<PeriodicTask> getScheduledPeriodicTasks() {
        var systemGraph = mainRuntime.getViewedSystemGraph();
        return systemGraph.incomingEdgesOf(mainRuntime).stream() // get all incoming edges
                .flatMap(v -> ForSyDeHierarchy.Scheduled.tryView(systemGraph, systemGraph.getEdgeSource(v)).stream())
                .filter(v -> v.runtimeHost().equals(mainRuntime)) // check if they are mapped to runtime
                .flatMap(v -> ForSyDeHierarchy.PeriodicTask.tryView(v).stream()) // try view as periodic task
                .collect(Collectors.toSet());
    }

    public PicoWriter getMainFileWriter() {
        var writer = new PicoWriter();
        writer.writeln("/* Standard includes. */");
        writer.writeln("#include <stddef.h>");
        writer.writeln("#include <stdio.h>");
        writer.writeln("#include <string.h>");
        writer.writeln("/* Scheduler includes. */");
        writer.writeln("#include \"FreeRTOS.h\"");
        writer.writeln("#include \"task.h\"");
        writer.writeln("#include \"queue.h\"");
        writer.writeln_r("int main(void) {");
        writer.writeln("/* Finally start the scheduler. */");
        writer.writeln("vTaskStartScheduler();");
        writer.writeln_l("}");
        return writer;
    }

    @Override
    public Map<Path, String> getPathsAndContent() {
        var map = new HashMap<Path, String>();
        var path = Paths.get(mainRuntime.getIdentifier() + ".c");
        var mainWriter = getMainFileWriter();
        map.put(path, mainWriter.toString(0));
        return map;
    }
}
