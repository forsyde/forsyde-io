package forsyde.io.java.validation;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SDFValidator implements SystemGraphValidation {

    @Override
    public Optional<String> validate(ForSyDeSystemGraph forSyDeSystemGraph) {
        final List<SDFActor> actors = forSyDeSystemGraph.vertexSet().stream().flatMap(v -> SDFActor.safeCast(v).stream()).collect(Collectors.toList());
        final List<SDFChannel> channels = forSyDeSystemGraph.vertexSet().stream().flatMap(v -> SDFChannel.safeCast(v).stream()).collect(Collectors.toList());
        // production and consumptio are disjoint
        for (SDFActor actor : actors) {
            Map<String, Boolean> visited = actor.getPorts().stream().collect(Collectors.toMap(p -> p, p -> false));
            // it is ok to cehck for null now since the channels are the decisive check
            if (actor.getConsumption() != null) {
                for (String p : actor.getConsumption().keySet()) {
                    if (visited.containsKey(p)) visited.put(p, true);
                    else
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for consumption");
                }
            }
            if (actor.getProduction() != null) {
                for (String p : actor.getProduction().keySet()) {
                    if (!visited.containsKey(p))
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for production");
                    else if (visited.get(p))
                        return Optional.of("SDF Actor " + actor.getIdentifier() + " has port " + p + " for consumption and production");
                }
            }
        }
        for (SDFChannel channel : channels) {
            final boolean consumerPortOk = channel.getConsumerPort(forSyDeSystemGraph).map(c -> {
                final Map<String, Integer> consumption = c.getConsumption();
                return forSyDeSystemGraph.getAllEdges(channel.getViewedVertex(), c.getViewedVertex()).stream()
                        .anyMatch(e -> e.targetPort.map(consumption::containsKey).orElse(false));
            }).orElse(false);
            final boolean producerPortOk = channel.getProducerPort(forSyDeSystemGraph).map(c -> {
                final Map<String, Integer> production = c.getProduction();
                return forSyDeSystemGraph.getAllEdges(c.getViewedVertex(), channel.getViewedVertex()).stream()
                        .anyMatch(e -> e.sourcePort.map(production::containsKey).orElse(false));
            }).orElse(false);
            if (!(producerPortOk || consumerPortOk)) {
                return Optional.of("SDF Channel " + channel.getIdentifier() + " must have at least a consumer or a producer connected");
            }
        }
        return Optional.empty();
    }
}
