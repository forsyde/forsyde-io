package forsyde.io.java.sdf3.adapters.mixins;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.sdf3.adapters.mixins.elems.Processor;
import forsyde.io.java.sdf3.adapters.mixins.elems.Sdf3;
import forsyde.io.java.typed.viewers.impl.InstrumentedExecutable;
import forsyde.io.java.typed.viewers.impl.TokenizableDataBlock;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannelViewer;
import forsyde.io.java.typed.viewers.visualization.Visualizable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface SDFThree2ForSyDeMixin extends EquivalenceModel2ModelMixin<Object, Vertex> {


    default void fromActorsToVertexes(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getActor().forEach(a -> {
            final SDFActor sdfActor = SDFActor.enforce(systemGraph.newVertex(a.getName()));
            Visualizable.enforce(sdfActor);
            final HashMap<String, Integer> consumption = new HashMap<>();
            final HashMap<String, Integer> production = new HashMap<>();
            a.getPort().forEach(port -> {
                sdfActor.getPorts().add(port.getName());
                if (port.getType().equals("in"))
                    consumption.put(port.getName(), port.getRate().intValueExact());
                else if (port.getType().equals("out"))
                    production.put(port.getName(), port.getRate().intValueExact());
            });
            sdfActor.setConsumption(consumption);
            sdfActor.setProduction(production);
            addEquivalence(a, sdfActor.getViewedVertex());
        });
    }

    default void fromChannelstoSignalsAndPrefix(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            // initial channel if no initial token exists
            final SDFChannel sdfChannel = SDFChannel.enforce(systemGraph.newVertex(channel.getName()));
            Visualizable.enforce(sdfChannel);
            addEquivalence(channel, sdfChannel.getViewedVertex());
            // additional tokens and prefixes until the prefixing chain is over
            if (channel.getInitialTokens() != null && channel.getInitialTokens().intValueExact() > 0) {
                sdfChannel.setNumOfInitialTokens(channel.getInitialTokens().intValueExact());
            } else {
                sdfChannel.setNumOfInitialTokens(0);
            }
        });
    }

    default void fromChannelsToEdges(final Sdf3 sdf3, final SystemGraph systemGraph) {
        // equivalent of 3 for loops
        sdf3.getApplicationGraph().getSdf().getChannel()
                .forEach(channel -> {
                    equivalents(channel)
                            .filter(SDFChannel::conforms)
                            .map(SDFChannelViewer::new)
                            .forEach(sdfChannel -> {
                                sdf3.getApplicationGraph().getSdf().getActor().stream()
                                        .filter(srcActor -> srcActor.getName().equals(channel.getSrcActor()))
                                        .flatMap(this::equivalents)
                                        .forEach(srcActorV -> {
                                            // find the one without consumer, as it could be expanded with delays beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.getConsumerPort(systemGraph).isEmpty()) {
                                                systemGraph.connect(srcActorV, sdfChannel.getViewedVertex(), channel.getSrcPort(), "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE, EdgeTrait.VISUALIZATION_VISUALCONNECTION);
                                            }
                                        });
                                sdf3.getApplicationGraph().getSdf().getActor().stream()
                                        .filter(dstActor -> dstActor.getName().equals(channel.getDstActor()))
                                        .flatMap(this::equivalents)
                                        .forEach(dstActorV -> {
                                            // find the one without consumer, as it could be expanded with delays beforehand.
                                            // find the equivalent signal to connect. Should not NPE.
                                            if (sdfChannel.getConsumerPort(systemGraph).isEmpty()) {
                                                systemGraph.connect(sdfChannel.getViewedVertex(), dstActorV, "consumer", channel.getDstPort(), EdgeTrait.MOC_SDF_SDFDATAEDGE, EdgeTrait.VISUALIZATION_VISUALCONNECTION);
                                            }
                                        });
                            });
                });
    }

    default void fromChannelPropertiesToSDFChannels(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getChannelProperties().forEach(channelProperties -> {
            systemGraph.queryVertex(channelProperties.getChannel()).flatMap(SDFChannel::safeCast).ifPresent(sdfChannel -> {
                final TokenizableDataBlock tokenizableDataBlock = TokenizableDataBlock.enforce(sdfChannel);
                tokenizableDataBlock.setTokenSizeInBits(channelProperties.getTokenSize().stream().mapToLong(t -> t.getSz().longValueExact()).sum());
                if (channelProperties.getBufferSize() != null) {
                    tokenizableDataBlock.setMaxSizeInBits(channelProperties.getBufferSize().getSz().longValueExact());
                }
            });
        });
    }

    default void fromActorPropertiesToSDFActor(final Sdf3 sdf3, final SystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdfProperties().getActorProperties().forEach(actorProperties -> {
            systemGraph.queryVertex(actorProperties.getActor()).flatMap(SDFActor::safeCast).ifPresent(sdfActor -> {
                final InstrumentedExecutable instrumentedExecutable = InstrumentedExecutable.enforce(sdfActor);
                final Map<String, Map<String, Long>> ops = actorProperties.getProcessor().stream().collect(Collectors.toMap(
                        Processor::getType,
                        p -> Map.of("all", p.getExecutionTime().getTime().longValueExact())
                ));
                instrumentedExecutable.setOperationRequirements(ops);

                final Long stateSize = actorProperties.getProcessor().stream().map(Processor::getMemory)
                        .mapToLong(m -> m != null ? m.getStateSize() != null ? m.getStateSize().getMax() != null ?
                                m.getStateSize().getMax().longValueExact() : 0L : 0L : 0L).sum();
                instrumentedExecutable.setSizeInBits(stateSize);
            });
        });
    }

}
