package forsyde.io.posix.synth;

import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.moc.sdf.SDFActor;
import forsyde.io.lib.hierarchy.behavior.moc.sy.SYDelay;
import forsyde.io.lib.hierarchy.behavior.moc.sy.SYMap;
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
 * This class represents the root of one POSIX runtime.
 * That is, of one programmable entry point, regardless if it is working
 * in SMP mode and controls multiple processing elements.
 */
public record POSIXFixedPriorityRoot(
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

    public Set<SDFActor> getScheduledActors() {
        var systemGraph = mainRuntime.getViewedSystemGraph();
        return systemGraph.incomingEdgesOf(mainRuntime).stream() // get all incoming edges
                .flatMap(v -> ForSyDeHierarchy.Scheduled.tryView(systemGraph, systemGraph.getEdgeSource(v)).stream())
                .filter(v -> v.runtimeHost().equals(mainRuntime)) // check if they are mapped to runtime
                .flatMap(v -> ForSyDeHierarchy.SDFActor.tryView(v).stream()) // try view as periodic task
                .collect(Collectors.toSet());
    }

    public Set<SYMap> getScheduledSYMaps() {
        var systemGraph = mainRuntime.getViewedSystemGraph();
        return systemGraph.incomingEdgesOf(mainRuntime).stream() // get all incoming edges
                .flatMap(v -> ForSyDeHierarchy.Scheduled.tryView(systemGraph, systemGraph.getEdgeSource(v)).stream())
                .filter(v -> v.runtimeHost().equals(mainRuntime)) // check if they are mapped to runtime
                .flatMap(v -> ForSyDeHierarchy.SYMap.tryView(v).stream()) // try view as periodic task
                .collect(Collectors.toSet());
    }

    public Set<SYDelay> getScheduledSYDelays() {
        var systemGraph = mainRuntime.getViewedSystemGraph();
        return systemGraph.incomingEdgesOf(mainRuntime).stream() // get all incoming edges
                .flatMap(v -> ForSyDeHierarchy.Scheduled.tryView(systemGraph, systemGraph.getEdgeSource(v)).stream())
                .filter(v -> v.runtimeHost().equals(mainRuntime)) // check if they are mapped to runtime
                .flatMap(v -> ForSyDeHierarchy.SYDelay.tryView(v).stream()) // try view as periodic task
                .collect(Collectors.toSet());
    }

    public PicoWriter getMainFileWriter() {
        var writer = new PicoWriter();
        var includesWriter = writer.createDeferredWriter();
        includesWriter.writeln("/* Standard includes. */");
        includesWriter.writeln("#include <stdlib.h>");
        includesWriter.writeln("#include <stdio.h>");
        includesWriter.writeln();
        includesWriter.writeln("/* Pthread inclusions. */");
        includesWriter.writeln("#include <pthread.h>");
        includesWriter.writeln();
        for (var p : getScheduledPeriodicTasks()) {
            writer.writeln("/* generated runnable function for %s */".formatted(p.getIdentifier()));
            writer.write("void* func_%s(".formatted(p.getIdentifier()));
            // put arguments here
            writer.writeln_r(") {");
            ForSyDeHierarchy.HasANSICImplementations.tryView(p).ifPresent(hasANSICImplementationsViewer -> {
                // get only generic code for now
                if (hasANSICImplementationsViewer.inlinedCodes().containsKey("generic")) {
                    writer.writeln(hasANSICImplementationsViewer.inlinedCodes().get("generic"));
                }
            });
            writer.writeln_l("}");
        }
        writer.writeln_r("int main(void) {");
        for (var p : getScheduledPeriodicTasks()) {
            for (int i = 0; i < p.periodNumerators().size(); i++) {
                writer.writeln("/* generated thread with period %d/%d and offset %d/%d */".formatted(p.periodNumerators().get(i), p.periodDenominators().get(i), p.offsetNumerators().get(i), p.offsetDenominators().get(i)));
                writer.writeln("pthread_t periodic_thread_%s__%d_%d__%d_%d;".formatted(p.getIdentifier(), p.periodNumerators().get(i), p.periodDenominators().get(i), p.offsetNumerators().get(i), p.offsetDenominators().get(i)));
            }
        }
        for (var actor: getScheduledActors()) {
            writer.writeln("/* generated SDF actor thread assuming self-timed blocking */");
            writer.writeln("pthread_t sdf_thread_%s;".formatted(actor.getIdentifier()));
        }
        writer.writeln("/* Finally finish the main. */");
        writer.writeln("exit(0);");
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
