package forsyde.io.java.adapters.sdf3;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.adapters.sdf3.elems.Sdf3;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.SDFComb;
import forsyde.io.java.typed.viewers.SDFSignal;

import java.util.HashMap;

public interface SDFThree2ForSyDeMixin extends EquivalenceModel2ModelMixin<Object, Vertex> {

    default void fromActorsToVertexes(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getActor().forEach(a -> {
            final Vertex v = new Vertex(a.getName());
            v.addTraits(VertexTrait.SDFComb);
            final SDFComb sdfComb = SDFComb.safeCast(v).get();
            final HashMap<String, Integer> consumption = new HashMap<>();
            final HashMap<String, Integer> production = new HashMap<>();
            a.getPort().forEach(port -> {
                sdfComb.getPorts().add(port.getName());
                if (port.getType().equals("in"))
                    consumption.put(port.getName(), port.getRate().intValueExact());
                else if (port.getType().equals("out"))
                    production.put(port.getName(), port.getRate().intValueExact());
            });
            sdfComb.setConsumption(consumption);
            sdfComb.setProduction(production);
            addEquivalence(a, sdfComb.getViewedVertex());
        });
    }

    default void fromChannelstoSignalsAndPrefix(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            // initial channel if no initial token exists
            final Vertex v = new Vertex(channel.getName());
            v.addTraits(VertexTrait.SDFSignal);
            v.ports.add(channel.getSrcPort());
            final SDFSignal sdfSignal = SDFSignal.safeCast(v).get();
            addEquivalence(channel, v);
            // additional tokens and prefixes until the prefixing chain is over
            Vertex endV = v;
            for (int i = 1; i < channel.getInitialTokens().intValueExact(); i++) {
                endV.ports.add(channel.getDstPort() + "_delay_" + (i - 1));
                final Vertex extraPrefix = new Vertex(channel.getName() + "_prefix_" + i);
                extraPrefix.addTraits(VertexTrait.SDFPrefix);
                extraPrefix.ports.add(channel.getDstPort() + "_delay_" + (i + -1));
                extraPrefix.ports.add(channel.getDstPort() + "_delay_" + i);
                addEquivalence(channel, extraPrefix);
                // connect signal and prefix
                systemGraph.connect(endV, extraPrefix, channel.getDstPort() + "_delay_" + (i + -1), channel.getDstPort() + "_delay_" + i, EdgeTrait.ModelOfComputationEdge);

                // new signal
                final Vertex extraV = new Vertex(channel.getName() + "_delay_" + i);
                extraV.addTraits(VertexTrait.SDFSignal);
                extraV.ports.add(channel.getDstPort() + "_delay_" + i);
                //connect again
                systemGraph.connect(extraPrefix, extraV, channel.getDstPort() + "_delay_" + i, channel.getDstPort() + "_delay_" + i, EdgeTrait.ModelOfComputationEdge);
                addEquivalence(channel, extraV);

            }
            endV.ports.add(channel.getDstPort());
        });
    }

    default void fromChannelsToEdges(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        // equivalent of 3 for loops
        sdf3.getApplicationGraph().getSdf().getChannel().forEach(channel -> {
            sdf3.getApplicationGraph().getSdf().getActor().stream().filter(srcActor -> srcActor.getName().equals(channel.getSrcActor()))
            .forEach(srcActor -> {
                // find the equivalent signal to connect
                equivalents(channel).filter(sig -> sig.ports.contains(channel.getSrcPort())).forEach(sigV -> {
                    // and the equivalent vertex
                    equivalent(srcActor).ifPresent(srcV -> {
                        systemGraph.connect(srcV, sigV, channel.getSrcPort(), channel.getSrcPort(), EdgeTrait.ModelOfComputationEdge);
                    });
                });
            });
            // now the target
            sdf3.getApplicationGraph().getSdf().getActor().stream().filter(dstActor -> dstActor.getName().equals(channel.getDstActor()))
            .forEach(dstActor -> {
                // find the equivalent signal to connect
                equivalents(channel).filter(sig -> sig.ports.contains(channel.getSrcPort())).forEach(sigV -> {
                    // and the equivalent vertex
                    equivalent(dstActor).ifPresent(dstV -> {
                        systemGraph.connect(sigV, dstV, channel.getDstPort(), channel.getDstPort(), EdgeTrait.ModelOfComputationEdge);
                    });
                });
            });
        });
    }

}
