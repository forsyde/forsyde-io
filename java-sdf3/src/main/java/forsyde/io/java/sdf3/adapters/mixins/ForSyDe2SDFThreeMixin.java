package forsyde.io.java.sdf3.adapters.mixins;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.sdf3.adapters.mixins.elems.*;
import forsyde.io.java.typed.viewers.decision.results.AnalyzedActor;
import forsyde.io.java.typed.viewers.impl.DataBlock;
import forsyde.io.java.typed.viewers.impl.InstrumentedExecutable;
import forsyde.io.java.typed.viewers.impl.TokenizableDataBlock;
import forsyde.io.java.typed.viewers.moc.sdf.SDFActor;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannelViewer;
import forsyde.io.java.typed.viewers.platform.*;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.AsWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.builder.GraphBuilder;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ForSyDe2SDFThreeMixin extends EquivalenceModel2ModelMixin<Vertex, Object> {

    /**
     *  This function was extracted from IDeSyDe's source code and adapted for this place generation.
     */
    private List<List<Double>> computeWorstCaseDelays(ForSyDeSystemGraph model) {
        final GraphBuilder<Vertex, org.jgrapht.graph.DefaultEdge, ? extends SimpleDirectedWeightedGraph<Vertex, org.jgrapht.graph.DefaultEdge>> gBuilder = SimpleDirectedWeightedGraph.createBuilder(DefaultEdge::new);
        model.edgeSet().forEach(e -> {
                final Vertex src = model.getEdgeSource(e);
                final Vertex dst = model.getEdgeTarget(e);
                GenericCommunicationModule.safeCast(src).ifPresent(srcRouter -> {
                        InstrumentedCommunicationModule.safeCast(dst).ifPresent(dstRouter -> {
                                final double minBw = dstRouter.getMaxCyclesPerFlit().doubleValue() / dstRouter.getOperatingFrequencyInHertz().doubleValue();
                                gBuilder.addEdge(
                                        src,
                                        dst,
                                        minBw
                                );
                        });
                        GenericProcessingModule.safeCast(dst).ifPresent(dstCore -> {
                                gBuilder.addEdge(
                                        src,
                                        dst,
                                        0.0
                                );
                        });
                });
                GenericProcessingModule.safeCast(src).ifPresent(srcCore -> {
                        InstrumentedCommunicationModule.safeCast(dst).ifPresent(dstRouter -> {
                                final double minBw = dstRouter.getMaxCyclesPerFlit().doubleValue() / dstRouter.getOperatingFrequencyInHertz().doubleValue();
                                gBuilder.addEdge(
                                        src,
                                        dst,
                                        minBw
                                );
                        });
                        GenericProcessingModule.safeCast(dst).ifPresent(dstCore -> {
                                gBuilder.addEdge(
                                        src,
                                        dst,
                                        0.0
                                );
                        });
                });
        });
        final Graph<Vertex, DefaultEdge> directedAndConnectedMaxTimeGraph = gBuilder.buildAsUnmodifiable();
        final double maxWeight = directedAndConnectedMaxTimeGraph.edgeSet().stream()
                .mapToDouble(directedAndConnectedMaxTimeGraph::getEdgeWeight)
                .max()
                .orElse(0.0);
        final Graph<Vertex, DefaultEdge> reversedGraph = new AsWeightedGraph<>(
                directedAndConnectedMaxTimeGraph,
                (e) -> maxWeight - directedAndConnectedMaxTimeGraph.getEdgeWeight(e),
                true,
                false
        );
        final FloydWarshallShortestPaths<Vertex, DefaultEdge> paths = new FloydWarshallShortestPaths(reversedGraph);
        final List<Vertex> cores = new ArrayList<Vertex>();
        directedAndConnectedMaxTimeGraph.vertexSet().forEach(vertex -> {
            if (GenericProcessingModule.conforms(vertex)) cores.add(vertex);
        });
        return cores.stream().map(src -> {
                return cores.stream().map(dst -> {
                    if (src != dst && paths.getPath(src, dst) != null) {
                        return Double.valueOf((maxWeight * paths.getPath(src, dst).getLength())-paths.getPathWeight(src, dst));
                    } else
                        return Double.valueOf(-1.0);
                }).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    /**
     *  This function was extracted from IDeSyDe's source code and adapted for the experments generation.
     */
    private List<List<Double>> computeWCETTable(ForSyDeSystemGraph model) {
        final int actorCount = (int) model.vertexSet().stream().filter(InstrumentedExecutable::conforms).count();
        final int coresCount = (int) model.vertexSet().stream().filter(InstrumentedProcessingModule::conforms).count();
        return model.vertexSet().stream().flatMap(v -> InstrumentedExecutable.safeCast(v).stream()).map(iactor -> {
            return model.vertexSet().stream().flatMap(v -> InstrumentedProcessingModule.safeCast(v).stream()).map(icpu -> {
                return iactor.getOperationRequirements().entrySet().stream().flatMapToDouble(opEntry -> {
                    return icpu.getModalInstructionsPerCycle().entrySet().stream().mapToDouble(ipcEntry -> {
                        if (opEntry.getValue().keySet().containsAll(ipcEntry.getValue().keySet())) {
                            return opEntry.getValue().entrySet().stream().mapToDouble(entry -> entry.getValue() / ipcEntry.getValue().get(entry.getKey())).sum();
                        } else {
                            return Double.MAX_VALUE;
                        }
                    });
                }).min().orElse(-1.0) / icpu.getOperatingFrequencyInHertz().doubleValue();
            }).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    default void convertApplications(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        if (sdf3.getApplicationGraph() == null) {
            final ApplicationGraph applicationGraph = new ApplicationGraph();
            applicationGraph.setName("sdfGraphs");
            sdf3.setApplicationGraph(applicationGraph);
        }
        if (sdf3.getApplicationGraph().getSdf() == null) {
            final Sdf sdf = new Sdf();
            sdf.setName("allSdfGraphs");
            sdf.setType("G");
            sdf3.getApplicationGraph().setSdf(sdf);
        }
        for (Vertex v : systemGraph.vertexSet()) {
            SDFActor.safeCast(v).ifPresent(sdfActor -> {
                final Actor actor = new Actor();
                actor.setName(v.getIdentifier());
                actor.setType(v.getIdentifier());
                InstrumentedExecutable.safeCast(sdfActor).ifPresentOrElse(instrumentedExecutable -> {
                    actor.setSize(new BigDecimal(instrumentedExecutable.getSizeInBits()));
                }, () -> {
                    actor.setSize(new BigDecimal(0L));
                });
                sdf3.getApplicationGraph().getSdf().getActor().add(actor);
                addEquivalence(v, actor);
            });
            SDFChannel.safeCast(v).ifPresent(sdfChannel -> {
                final Channel channel = new Channel();
                channel.setName(sdfChannel.getIdentifier());
                channel.setInitialTokens(new BigDecimal(sdfChannel.getNumOfInitialTokens()));
                DataBlock.safeCast(sdfChannel).ifPresentOrElse(dataBlock -> {
                    channel.setSize(new BigDecimal(dataBlock.getMaxSizeInBits()));
                }, () -> {
                    channel.setSize(new BigDecimal(0L));
                });
                sdfChannel.getConsumerPort(systemGraph).ifPresent(sdfActor -> {
                    channel.setDstActor(sdfActor.getIdentifier());
                });
                sdfChannel.getProducerPort(systemGraph).ifPresent(sdfActor -> {
                    channel.setSrcActor(sdfActor.getIdentifier());
                });
                sdf3.getApplicationGraph().getSdf().getChannel().add(channel);
                addEquivalence(v, channel);
            });
        }
        for (EdgeInfo edgeInfo : systemGraph.edgeSet()) {
            SDFActor.safeCast(systemGraph.getEdgeSource(edgeInfo)).ifPresent(sdfActor -> {
                SDFChannel.safeCast(systemGraph.getEdgeTarget(edgeInfo)).ifPresent(sdfChannel -> {
                    equivalent(sdfActor.getViewedVertex()).filter(o -> o instanceof Actor).map(o -> (Actor) o).ifPresent(actor -> {
                        final Port port = new Port();
                        port.setName(edgeInfo.getSourcePort().orElse(sdfChannel.getIdentifier()));
                        final int rate = sdfActor.getProduction().getOrDefault(edgeInfo.getSourcePort().orElse(sdfChannel.getIdentifier()), 1);
                        port.setRate(new BigDecimal(rate));
                        port.setType("out");
                        actor.getPort().add(port);
                        equivalent(sdfChannel.getViewedVertex()).filter(o -> o instanceof Channel).map(o -> (Channel) o).ifPresent(channel -> {
                            channel.setSrcPort(edgeInfo.getSourcePort().orElse(sdfChannel.getIdentifier()));
                        });
                    });
                });
            });
            SDFChannel.safeCast(systemGraph.getEdgeSource(edgeInfo)).ifPresent(sdfChannel -> {
                SDFActor.safeCast(systemGraph.getEdgeTarget(edgeInfo)).ifPresent(sdfActor -> {
                    equivalent(sdfActor.getViewedVertex()).filter(o -> o instanceof Actor).map(o -> (Actor) o).ifPresent(actor -> {
                        final Port port = new Port();
                        port.setName(edgeInfo.getTargetPort().orElse(sdfChannel.getIdentifier()));
                        final int rate = sdfActor.getConsumption().getOrDefault(edgeInfo.getTargetPort().orElse(sdfChannel.getIdentifier()), 1);
                        port.setRate(new BigDecimal(rate));
                        port.setType("in");
                        actor.getPort().add(port);
                        equivalent(sdfChannel.getViewedVertex()).filter(o -> o instanceof Channel).map(o -> (Channel) o).ifPresent(channel -> {
                            channel.setDstPort(edgeInfo.getTargetPort().orElse(sdfChannel.getIdentifier()));
                        });
                    });
                });
            });
        }
    }

    default void convertArchitecture(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        if (sdf3.getArchitectureGraph() == null) {
            final ArchitectureGraph architectureGraph = new ArchitectureGraph();
            architectureGraph.setName("sdfGraphs");
            sdf3.setArchitectureGraph(architectureGraph);
        }
        final List<Tile> tileList = new ArrayList<>();
        final List<NetworkInterface> networkInterfaceList = new ArrayList<>();
        for (Vertex v : systemGraph.vertexSet()) {
            GenericProcessingModule.safeCast(v).ifPresent(genericProcessingModule -> {
                final Tile tile = new Tile();
                final Processor processor = new Processor();
                final Arbitration arbitration = new Arbitration();

                tile.setName(genericProcessingModule.getIdentifier() + "_tile");
                processor.setName(genericProcessingModule.getIdentifier());
                processor.setType(genericProcessingModule.getIdentifier());

                arbitration.setType("TDMA");
                arbitration.setWheelsize(new BigDecimal(100));
                processor.setArbitration(arbitration);

                addEquivalence(v, processor);
                addEquivalence(v, tile);

                final Set<Vertex> neighs = Stream.concat(
                        systemGraph.incomingEdgesOf(v).stream().map(systemGraph::getEdgeSource),
                        systemGraph.outgoingEdgesOf(v).stream().map(systemGraph::getEdgeTarget)
                    )
                    .collect(Collectors.toSet());
                for (Vertex neigh : neighs) {
                    if (equivalents(neigh).findAny().isEmpty()) {
                        GenericMemoryModule.safeCast(neigh).ifPresent(genericMemoryModule -> {
                            final Memory memory = new Memory();
                            memory.setName(genericMemoryModule.getIdentifier());
                            memory.setSize(new BigDecimal(genericMemoryModule.getSpaceInBits()));
                            tile.setMemory(memory);
                            addEquivalence(neigh, memory);
                        });
                        GenericCommunicationModule.safeCast(neigh).ifPresent(genericCommunicationModule -> {
                            final NetworkInterface networkInterface = new NetworkInterface();
                            networkInterface.setName(genericCommunicationModule.getIdentifier());
                            InstrumentedCommunicationModule.safeCast(genericCommunicationModule).ifPresent(instrumentedCommunicationModule -> {
                                networkInterface.setNrConnections(new BigDecimal(instrumentedCommunicationModule.getMaxConcurrentFlits()));
                                networkInterface.setInBandwidth(
                                        instrumentedCommunicationModule.getMaxConcurrentFlits().doubleValue() *
                                        instrumentedCommunicationModule.getFlitSizeInBits().doubleValue() *
                                        instrumentedCommunicationModule.getOperatingFrequencyInHertz().doubleValue() / 2.0
                                );
                                networkInterface.setOutBandwidth(
                                        instrumentedCommunicationModule.getMaxConcurrentFlits().doubleValue() *
                                        instrumentedCommunicationModule.getFlitSizeInBits().doubleValue() *
                                        instrumentedCommunicationModule.getOperatingFrequencyInHertz().doubleValue() / 2.0
                                );
                            });
                            tile.setNetworkInterface(networkInterface);
                            networkInterfaceList.add(networkInterface);
                            addEquivalence(neigh, networkInterface);
                        });
                    }
                }

                tile.setProcessor(processor);
                sdf3.getArchitectureGraph().getTile().add(tile);
                tileList.add(tile);
            });
        }

        final List<List<Double>> wccts = computeWorstCaseDelays(systemGraph);
        final List<GenericProcessingModule> cores = systemGraph.vertexSet().stream().flatMap(v -> GenericProcessingModule.safeCast(v).stream()).collect(Collectors.toList());
        for (int srci = 0; srci < cores.size(); srci++) {
            for (int dsti = 0; dsti < cores.size(); dsti++) {
                if (srci != dsti && wccts.get(srci).get(dsti) > -1) {
                    final Connection connection = new Connection();
                    connection.setName(cores.get(srci).getIdentifier() + "_" + cores.get(dsti).getIdentifier());
                    connection.setDelay(BigDecimal.valueOf(wccts.get(srci).get(dsti)));
                    connection.setSrcTile(cores.get(srci).getIdentifier() + "_tile");
                    connection.setDstTile(cores.get(dsti).getIdentifier() + "_tile");
                    sdf3.getArchitectureGraph().getConnection().add(connection);
                }
            }
        }


        final Network network = new Network();
        network.setFlitSize(new BigDecimal(
                systemGraph.vertexSet().stream().flatMap(v -> InstrumentedCommunicationModule.safeCast(v).stream()).mapToLong(InstrumentedCommunicationModule::getFlitSizeInBits).max().orElse(1L)
        ));
        // TODO: Figure out a way to get these other parameters later
        network.setPacketHeaderSize(new BigDecimal(32));
        network.setReconfigurationTimeNI(new BigDecimal(1));
        network.setSlotTableSize(new BigDecimal(tileList.size()));
//        network.getTile().addAll(tileList);
        for (Vertex vertex : systemGraph.vertexSet()) {
            if(equivalents(vertex).noneMatch(networkInterfaceList::contains)) {
                GenericCommunicationModule.safeCast(vertex).ifPresent(genericCommunicationModule -> {
                    final Router router = new Router();
                    router.setName(genericCommunicationModule.getIdentifier());
                    network.getRouter().add(router);
                    addEquivalence(vertex, router);
                });
            }
        }
        for (EdgeInfo edgeInfo : systemGraph.edgeSet()) {
            final Vertex src = systemGraph.getEdgeSource(edgeInfo);
            final Vertex dst = systemGraph.getEdgeTarget(edgeInfo);
            equivalents(src).forEach(srco -> {
                if (srco instanceof NetworkInterface || srco instanceof Router) {
                    equivalents(dst).forEach(dsto -> {
                        if (dsto instanceof NetworkInterface || dsto instanceof Router) {
                            final Link link = new Link();
                            link.setName(src.getIdentifier() + "_" + dst.getIdentifier());
                            if (srco instanceof NetworkInterface) {
                                link.setSrc(tileList.get(networkInterfaceList.indexOf(srco)).getName());
                            } else {
                                link.setSrc(src.getIdentifier());
                            }
                            if (dsto instanceof NetworkInterface) {
                                link.setDst(tileList.get(networkInterfaceList.indexOf(dsto)).getName());
                            } else {
                                link.setDst(dst.getIdentifier());
                            }
                            network.getLink().add(link);
                        }
                    });
                }
            });
        }
        sdf3.getArchitectureGraph().setNetwork(network);
    }

    default void convertParameters(final Sdf3 sdf3, final ForSyDeSystemGraph systemGraph) {
        List<List<Double>> wcets = computeWCETTable(systemGraph);
        final List<Actor> actors = systemGraph.vertexSet().stream().flatMap(this::equivalents).filter(o -> o instanceof Actor).map(o -> (Actor) o).distinct().collect(Collectors.toList());
        final List<Processor> processors = systemGraph.vertexSet().stream().flatMap(this::equivalents).filter(o -> o instanceof Processor).map(o -> (Processor) o).distinct().collect(Collectors.toList());
        if (sdf3.getApplicationGraph().getSdfProperties() == null) {
            sdf3.getApplicationGraph().setSdfProperties(new SdfProperties());
        }
        for (int i = 0; i < actors.size(); i++) {
            final Actor actor = actors.get(i);
            final ActorProperties actorProperties = new ActorProperties();
            actorProperties.setActor(actor.getName());
            for (int j = 0; j < processors.size(); j++) {
                final Processor processor = processors.get(j);
                if (wcets.get(i).get(j) > -1.0) {
                    // wow... they really tried to cut corners here. Reusing a Processor tag might sound like a good idea
                    // but it just leaks conceptually everywhere.
                    final Processor newProcessor = new Processor();
                    final Memory newMemory = new Memory();
                    final StateSize stateSize = new StateSize();
                    final ExecutionTime executionTime = new ExecutionTime();
                    executionTime.setTime(BigDecimal.valueOf(wcets.get(i).get(j)));
                    stateSize.setMax(actor.getSize());
                    newMemory.setStateSize(stateSize);
                    newProcessor.setExecutionTime(executionTime);
                    newProcessor.setMemory(newMemory);
                    newProcessor.setType(processor.getType());
                    newProcessor.setDefault("true");
                    actorProperties.getProcessor().add(newProcessor);
                }
            }
            sdf3.getApplicationGraph().getSdfProperties().getActorProperties().add(actorProperties);
        }
        final List<AnalyzedActor> analyzedActorList = systemGraph.vertexSet().stream().flatMap(v -> AnalyzedActor.safeCast(v).stream()).collect(Collectors.toList());
        if (analyzedActorList.size() > 0) {
            final GraphProperties graphProperties = new GraphProperties();
            final TimeConstraints timeConstraints = new TimeConstraints();
            timeConstraints.setThroughput(
                    BigDecimal.valueOf(analyzedActorList.stream().mapToDouble(v -> v.getThroughputInSecsNumerator().doubleValue() / v.getThroughputInSecsDenominator().doubleValue()).max().orElse(0.0))
            );
            graphProperties.setTimeConstraints(timeConstraints);
            sdf3.getApplicationGraph().getSdfProperties().setGraphProperties(graphProperties);
        }

        systemGraph.vertexSet().stream().flatMap(v -> TokenizableDataBlock.safeCast(v).stream()).forEach(tokenizableDataBlock -> {
            final ChannelProperties channelProperties = new ChannelProperties();
            final TokenSize tokenSize = new TokenSize();
            tokenSize.setSz(new BigDecimal(tokenizableDataBlock.getTokenSizeInBits()));
            channelProperties.setChannel(tokenizableDataBlock.getIdentifier());
            channelProperties.getTokenSize().add(tokenSize);
            if (tokenizableDataBlock.getMaxSizeInBits() > 0L) {
                final BufferSize bufferSize = new BufferSize();
                bufferSize.setSz(new BigDecimal(tokenizableDataBlock.getMaxSizeInBits()));
                bufferSize.setMem(new BigDecimal(tokenizableDataBlock.getMaxSizeInBits()));
                channelProperties.setBufferSize(bufferSize);
            }
            sdf3.getApplicationGraph().getSdfProperties().getChannelProperties().add(channelProperties);
        });
    }

}
