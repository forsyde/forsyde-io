package forsyde.io.bridge.sdf3.adapters.mixins;

import forsyde.io.bridge.sdf3.adapters.mixins.elems.Processor;
import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.bridge.sdf3.adapters.mixins.elems.Sdf3;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface SDFThree2ForSyDeMixin extends EquivalenceModel2ModelMixin<Object, Vertex> {

    default void fromActorsToVertexes(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getActor().forEach(a -> {
            var sdfActor = ForSyDeHierarchy.SDFActor.enforce(systemGraph, systemGraph.newVertex(a.getName()));
            ForSyDeHierarchy.Visualizable.enforce(sdfActor);
            final HashMap<String, Integer> consumption = new HashMap<>();
            final HashMap<String, Integer> production = new HashMap<>();
            a.getPort().forEach(port -> {
                sdfActor.getPorts().add(port.getName());
                if (port.getType().equals("in")) {
                    consumption.put(port.getName(), port.getRate().intValueExact());
                } else if (port.getType().equals("out")) {
                    production.put(port.getName(), port.getRate().intValueExact());
                }
            });
            sdfActor.consumption(consumption);
            sdfActor.production(production);
            addEquivalence(a, sdfActor.getViewedVertex());
        });
    }

    default void fromChannelsToSignalsAndPrefix(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            // initial channel if no initial token exists
            var sdfChannel = ForSyDeHierarchy.SDFChannel.enforce(systemGraph, systemGraph.newVertex(channel.getName()));
            ForSyDeHierarchy.Visualizable.enforce(sdfChannel);
            addEquivalence(channel, sdfChannel.getViewedVertex());
            // additional tokens and prefixes until the prefixing chain is over
            if (channel.getInitialTokens() != null && channel.getInitialTokens().intValueExact() > 0) {
                sdfChannel.numInitialTokens(channel.getInitialTokens().intValueExact());
            } else {
                sdfChannel.numInitialTokens(0);
            }
        });
    }

    default void fromChannelsToEdges(final Sdf3 sdf3, final SystemGraph systemGraph) {
        // equivalent of 3 for loops
        sdf3.getApplicationGraph().getSdf().getChannel()
                .forEach(channel -> {
                    equivalents(channel)
                            .flatMap(v -> ForSyDeHierarchy.SDFChannel.tryView(systemGraph, v).stream())
                            .forEach(sdfChannel -> {
                                sdf3.getApplicationGraph().getSdf().getActor().stream()
                                        .filter(srcActor -> srcActor.getName().equals(channel.getSrcActor()))
                                        .flatMap(this::equivalents)
                                        .forEach(srcActorV -> {
                                            // find the one without consumer, as it could be expanded with delays
                                            // beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.producer().isEmpty()) {
                                                sdfChannel.producer(channel.getSrcPort(),
                                                        ForSyDeHierarchy.SDFActor.enforce(systemGraph, srcActorV),
                                                        ForSyDeHierarchy.EdgeTraits.VisualConnection);
                                            }
                                        });
                                sdf3.getApplicationGraph().getSdf().getActor().stream()
                                        .filter(dstActor -> dstActor.getName().equals(channel.getDstActor()))
                                        .flatMap(this::equivalents)
                                        .forEach(dstActorV -> {
                                            // find the one without consumer, as it could be expanded with delays
                                            // beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.consumer().isEmpty()) {
                                                sdfChannel.consumer(channel.getDstPort(),
                                                        ForSyDeHierarchy.SDFActor.enforce(systemGraph, dstActorV),
                                                        ForSyDeHierarchy.EdgeTraits.VisualConnection);
                                            }
                                        });
                            });
                });
    }

    default void fromChannelPropertiesToSDFChannels(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getChannelProperties().forEach(channelProperties -> {
            systemGraph.queryVertex(channelProperties.getChannel())
                    .flatMap(v -> ForSyDeHierarchy.SDFChannel.tryView(systemGraph, v)).ifPresent(sdfChannel -> {
                        if (!channelProperties.getTokenSize().isEmpty()) {
                            var tokenizableDataBlock = ForSyDeHierarchy.BufferLike.enforce(sdfChannel);
                            var sz = channelProperties.getTokenSize().stream()
                                    .mapToLong(t -> t.getSz().longValueExact()).sum();
                            tokenizableDataBlock.elementSizeInBits(sz);
                        }
                        if (channelProperties.getBufferSize() != null) {
                            var tokenizableDataBlock = ForSyDeHierarchy.BoundedBufferLike.enforce(sdfChannel);
                            var max = channelProperties.getBufferSize().getSz().longValueExact();
                            tokenizableDataBlock
                                    .maxElements((int) max / tokenizableDataBlock.elementSizeInBits().intValue());
                        }
                    });
        });
    }

    default void fromActorPropertiesToSDFActor(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getActorProperties().forEach(actorProperties -> {
            systemGraph.queryVertex(actorProperties.getActor())
                    .flatMap(v -> ForSyDeHierarchy.SDFActor.tryView(systemGraph, v)).ifPresent(sdfActor -> {
                        var instrumentedExecutable = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(sdfActor);
                        final Map<String, Map<String, Long>> ops = actorProperties.getProcessor().stream()
                                .collect(Collectors.toMap(
                                        Processor::getType,
                                        p -> Map.of("all", p.getExecutionTime().getTime().longValueExact())));
                        instrumentedExecutable.computationalRequirements(ops);

                        final Map<String, Long> stateSizes = actorProperties.getProcessor().stream()
                                .collect(Collectors.toMap(Processor::getType,
                                        p -> p.getMemory() != null ? p.getMemory().getStateSize() != null
                                                ? p.getMemory().getStateSize().getMax() != null
                                                        ? p.getMemory().getStateSize().getMax().longValueExact()
                                                        : 0L
                                                : 0L : 0L));
                        instrumentedExecutable.maxSizeInBits(stateSizes);
                    });
        });
    }

}
