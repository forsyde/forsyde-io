package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.validation.SystemGraphValidation;
import forsyde.io.lib.behavior.moc.sdf.SDFActor;
import forsyde.io.lib.behavior.moc.sdf.SDFChannel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SDFValidator implements SystemGraphValidation {

    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
        final List<SDFActor> actors = systemGraph.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).stream()).collect(Collectors.toList());
        final List<SDFChannel> channels = systemGraph.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.SDFChannel.tryView(systemGraph, v).stream()).collect(Collectors.toList());
        // production and consumptio are disjoint
        for (SDFActor actor : actors) {
            Map<String, Boolean> visited = actor.getPorts().stream().collect(Collectors.toMap(p -> p, p -> false));
            // it is ok to cehck for null now since the channels are the decisive check
            if (actor.consumption() != null) {
                for (String p : actor.consumption().keySet()) {
                    if (visited.containsKey(p)) visited.put(p, true);
                    else
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for consumption");
                }
            }
            if (actor.production() != null) {
                for (String p : actor.production().keySet()) {
                    if (!visited.containsKey(p))
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for production");
                    else if (visited.get(p))
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " has port " + p + " for consumption and production");
                }
            }
        }
        for (SDFChannel channel : channels) {
            final boolean consumerPortOk = channel.consumer().map(c -> {
                final Map<String, Integer> consumption = c.consumption();
                return systemGraph.getAllEdges(channel.getViewedVertex(), c.getViewedVertex()).stream()
                        .anyMatch(e -> e.getTargetPort().map(consumption::containsKey).orElse(false));
            }).orElse(true);
            if (!consumerPortOk) {
                return Optional.of("SDF Channel " + channel.getIdentifier() + " is not connected to a consumption port.");
            }
            final boolean producerPortOk = channel.producer().map(c -> {
                final Map<String, Integer> production = c.production();
                return systemGraph.getAllEdges(c.getViewedVertex(), channel.getViewedVertex()).stream()
                        .anyMatch(e -> e.getSourcePort().map(production::containsKey).orElse(false));
            }).orElse(true);
            if (!producerPortOk) {
                return Optional.of("SDF Channel " + channel.getIdentifier() + " is not connected to a production port.");
            }
        }
        return Optional.empty();
    }
}
