package forsyde.io.bridge.sdf3.adapters.mixins;

import forsyde.io.bridge.sdf3.adapters.mixins.elems.Processor;
import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.core.EdgeTrait;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.bridge.sdf3.adapters.mixins.elems.Sdf3;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.lib.behavior.sdf.SDFActor;
import forsyde.io.lib.behavior.sdf.SDFActorViewer;
import forsyde.io.lib.behavior.sdf.SDFChannelViewer;
import forsyde.io.lib.visualization.Visualizable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface SDFThree2ForSyDeMixin extends EquivalenceModel2ModelMixin<Object, Vertex> {


    default void fromActorsToVertexes(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getActor().forEach(a -> {
            var sdfActor = SDFActorViewer.enforce(systemGraph, systemGraph.newVertex(a.getName()));
            ForSyDeHierarchy.Visualizable.enforce(sdfActor);
            final HashMap<String, Integer> consumption = new HashMap<>();
            final HashMap<String, Integer> production = new HashMap<>();
            a.getPort().forEach(port -> {
                sdfActor.getPorts().add(port.getName());
                if (port.getType().equals("in"))
                    consumption.put(port.getName(), port.getRate().intValueExact());
                else if (port.getType().equals("out"))
                    production.put(port.getName(), port.getRate().intValueExact());
            });
            sdfActor.consumption(consumption);
            sdfActor.production(production);
            addEquivalence(a, sdfActor.getViewedVertex());
        });
    }

    default void fromChannelstoSignalsAndPrefix(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            // initial channel if no initial token exists
            var sdfChannel = SDFChannelViewer.enforce(systemGraph, systemGraph.newVertex(channel.getName()));
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
                                            // find the one without consumer, as it could be expanded with delays beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.producer().isEmpty()) {
                                                sdfChannel.producer(ForSyDeHierarchy.SDFActor.enforce(systemGraph, srcActorV));
                                                systemGraph.connect(srcActorV, sdfChannel.getViewedVertex(), channel.getSrcPort(), "producer", ForSyDeHierarchy.EdgeTraits.VisualConnection);
                                            }
                                        });
                                sdf3.getApplicationGraph().getSdf().getActor().stream()
                                        .filter(dstActor -> dstActor.getName().equals(channel.getDstActor()))
                                        .flatMap(this::equivalents)
                                        .forEach(dstActorV -> {
                                            // find the one without consumer, as it could be expanded with delays beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.consumer().isEmpty()) {
                                                sdfChannel.consumer(ForSyDeHierarchy.SDFActor.enforce(systemGraph, dstActorV));
                                                systemGraph.connect(sdfChannel.getViewedVertex(), dstActorV, "consumer", channel.getDstPort(), ForSyDeHierarchy.EdgeTraits.VisualConnection);
                                            }
                                        });
                            });
                });
    }

    default void fromChannelPropertiesToSDFChannels(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getChannelProperties().forEach(channelProperties -> {
            systemGraph.queryVertex(channelProperties.getChannel()).flatMap(v -> ForSyDeHierarchy.SDFChannel.tryView(systemGraph, v)).ifPresent(sdfChannel -> {
                if (channelProperties.getBufferSize() != null) {
                    var tokenizableDataBlock = ForSyDeHierarchy.ArrayBufferLike.enforce(sdfChannel);
                    var max = channelProperties.getBufferSize().getSz().longValueExact();
                    tokenizableDataBlock.maxBufferSize(max);
                    var sz = channelProperties.getTokenSize().stream().mapToLong(t -> t.getSz().longValueExact()).sum();
                    tokenizableDataBlock.maxElements((int) max / (int) sz);
                }
            });
        });
    }

    default void fromActorPropertiesToSDFActor(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getActorProperties().forEach(actorProperties -> {
            systemGraph.queryVertex(actorProperties.getActor()).flatMap(v -> ForSyDeHierarchy.SDFActor.tryView(systemGraph, v)).ifPresent(sdfActor -> {
                var instrumentedExecutable = ForSyDeHierarchy.InstrumentedOperation.enforce(sdfActor);
                final Map<String, Map<String, Long>> ops = actorProperties.getProcessor().stream().collect(Collectors.toMap(
                        Processor::getType,
                        p -> Map.of("all", p.getExecutionTime().getTime().longValueExact())
                ));
                instrumentedExecutable.computationalRequirements(ops);

                final Long stateSize = actorProperties.getProcessor().stream().map(Processor::getMemory)
                        .mapToLong(m -> m != null ? m.getStateSize() != null ? m.getStateSize().getMax() != null ?
                                m.getStateSize().getMax().longValueExact() : 0L : 0L : 0L).sum();
                instrumentedExecutable.maxSizeInBits(Map.of("all", stateSize));
            });
        });
    }

}
