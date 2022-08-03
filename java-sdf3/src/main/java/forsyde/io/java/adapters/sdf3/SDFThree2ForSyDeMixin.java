package forsyde.io.java.adapters.sdf3;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.adapters.sdf3.elems.Sdf3;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.*;
import forsyde.io.java.typed.viewers.visualization.Visualizable;

import java.util.HashMap;
import java.util.Map;

public interface SDFThree2ForSyDeMixin extends EquivalenceModel2ModelMixin<Object, Vertex> {


    default void fromActorsToVertexes(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
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

    default void fromChannelstoSignalsAndPrefix(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            // initial channel if no initial token exists
            final SDFChannel sdfChannel = SDFChannel.enforce(systemGraph.newVertex(channel.getName()));
            Visualizable.enforce(sdfChannel);
            // channelVertex.ports.add("producer");
            // channelVertex.ports.add("consumer");
            addEquivalence(channel, sdfChannel.getViewedVertex());
            // additional tokens and prefixes until the prefixing chain is over
            if (channel.getInitialTokens() != null && channel.getInitialTokens().intValueExact() > 0) {
                sdfChannel.setNumOfInitialTokens(channel.getInitialTokens().intValueExact());
                /*final Vertex extraPrefix = new Vertex(channel.getName() + "Prefix", VertexTrait.MOC_SDF_SDFDELAY);
                final Vertex delayedChannel = new Vertex(channel.getName() + "Delayed", VertexTrait.MOC_SDF_SDFCHANNEL);
                final SDFDelay sdfDelay = new SDFDelayViewer(extraPrefix);
                systemGraph.addVertex(extraPrefix);
                systemGraph.addVertex(delayedChannel);
                delayedChannel.ports.add("producer");
                delayedChannel.ports.add("consumer");
                extraPrefix.ports.add("delayFunction");
                extraPrefix.ports.add("notDelayedChannel");
                extraPrefix.ports.add("delayedChannel");
                sdfDelay.setDelayedTokens(channel.getInitialTokens().intValueExact());
                systemGraph.connect(channelVertex, extraPrefix, "consumer", "notDelayedChannel", EdgeTrait.MOC_SDF_SDFDATAEDGE);
                systemGraph.connect(extraPrefix, delayedChannel, "delayedChannel", "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE);
                addEquivalence(channel, extraPrefix);
                addEquivalence(channel, delayedChannel);*/
            } else {
                sdfChannel.setNumOfInitialTokens(0);
            }
            /*
            for (int i = 1; i < channel.getInitialTokens().intValueExact(); i++) {
                endV.ports.add(channel.getDstPort() + "_delay_" + (i - 1));
                final Vertex extraPrefix = new Vertex(channel.getName() + "_prefix_" + i);
                extraPrefix.addTraits(VertexTrait.MOC_SDF_SDFDELAY);
                extraPrefix.ports.add("delayFunction");
                extraPrefix.ports.add("notDelayedChannel");
                extraPrefix.ports.add("delayedChannel");
                extraPrefix.ports.add(channel.getDstPort() + "_delay_" + (i + -1));
                extraPrefix.ports.add(channel.getDstPort() + "_delay_" + i);
                addEquivalence(channel, extraPrefix);
                // connect signal and prefix
                systemGraph.connect(endV, extraPrefix, channel.getDstPort() + "_delay_" + (i + -1), channel.getDstPort() + "_delay_" + i, EdgeTrait.MOC_SDF_SDFDATAEDGE);

                // new signal
                final Vertex extraV = new Vertex(channel.getName() + "_delay_" + i);
                extraV.addTraits(VertexTrait.MOC_SDF_SDFCHANNEL);
                extraV.ports.add(channel.getDstPort() + "_delay_" + i);
                //connect again
                systemGraph.connect(extraPrefix, extraV, channel.getDstPort() + "_delay_" + i, channel.getDstPort() + "_delay_" + i, EdgeTrait.MOC_SDF_SDFDATAEDGE);
                addEquivalence(channel, extraV);

            }
            endV.ports.add(channel.getDstPort());
            */
        });
    }

    default void fromChannelsToEdges(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
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

}
