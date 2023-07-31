package forsyde.io.core.validation;

import forsyde.io.core.SystemGraph;

import java.util.Optional;

public class SDFValidator implements SystemGraphValidation {

    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
//        final List<SDFActor> actors = systemGraph.vertexSet().stream().flatMap(v -> SDFActor.safeCast(v).stream()).collect(Collectors.toList());
//        final List<SDFChannel> channels = systemGraph.vertexSet().stream().flatMap(v -> SDFChannel.safeCast(v).stream()).collect(Collectors.toList());
//        // production and consumptio are disjoint
//        for (SDFActor actor : actors) {
//            Map<String, Boolean> visited = actor.getPorts().stream().collect(Collectors.toMap(p -> p, p -> false));
//            // it is ok to cehck for null now since the channels are the decisive check
//            if (actor.getConsumption() != null) {
//                for (String p : actor.getConsumption().keySet()) {
//                    if (visited.containsKey(p)) visited.put(p, true);
//                    else
//                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for consumption");
//                }
//            }
//            if (actor.getProduction() != null) {
//                for (String p : actor.getProduction().keySet()) {
//                    if (!visited.containsKey(p))
//                        return Optional.of("SDF Actor " + actor.getIdentifier() + " does not contain port " + p + " for production");
//                    else if (visited.get(p))
//                        return Optional.of("SDF Actor " + actor.getIdentifier() + " has port " + p + " for consumption and production");
//                }
//            }
//        }
//        for (SDFChannel channel : channels) {
//            final boolean consumerPortOk = channel.getConsumerPort(systemGraph).map(c -> {
//                final Map<String, Integer> consumption = c.getConsumption();
//                return systemGraph.getAllEdges(channel.getViewedVertex(), c.getViewedVertex()).stream()
//                        .anyMatch(e -> e.getTargetPort().map(consumption::containsKey).orElse(false));
//            }).orElse(true);
//            if (!consumerPortOk) {
//                return Optional.of("SDF Channel " + channel.getIdentifier() + " is not connected to a consumption port.");
//            }
//            final boolean producerPortOk = channel.getProducerPort(systemGraph).map(c -> {
//                final Map<String, Integer> production = c.getProduction();
//                return systemGraph.getAllEdges(c.getViewedVertex(), channel.getViewedVertex()).stream()
//                        .anyMatch(e -> e.getSourcePort().map(production::containsKey).orElse(false));
//            }).orElse(true);
//            if (!producerPortOk) {
//                return Optional.of("SDF Channel " + channel.getIdentifier() + " is not connected to a production port.");
//            }
//        }
        return Optional.empty();
    }
}
