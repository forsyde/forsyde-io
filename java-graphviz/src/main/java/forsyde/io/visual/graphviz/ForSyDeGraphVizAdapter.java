package forsyde.io.visual.graphviz;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.java.adapters.ModelAdapter;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.model.Factory.node;

public class ForSyDeGraphVizAdapter implements ModelAdapter<Graph> {

    final Map<Vertex, Node> nodes = new HashMap<>();
    final Map<Vertex, Graph> clusters = new HashMap<>();

    @Override
    public SystemGraph convert(Graph inputModel) {
        return null;
    }

    @Override
    public Graph convert(SystemGraph inputModel) {
        //take only the visualizable subset
        //final AsSubgraph<Vertex, EdgeInfo> visuGSubset = new AsSubgraph<>(inputModel, visuVSet, visuESet);
        return null; //buildHierarchical(inputModel);
    }

//    protected Graph buildHierarchical(final SystemGraph inputModel) {
//        // find the topmost containers
//        final Set<Vertex> rootClusters = inputModel.vertexSet().stream()
//                .filter(GreyBox::conforms)
//                // no incoming edge is of containment
//                .filter(v ->
//                        inputModel.incomingEdgesOf(v).stream().noneMatch(e ->
//                                e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)))
//                .collect(Collectors.toSet());
//        final List<Graph> rootClusterGraphs = rootClusters.stream()
//                .map(GreyBox::enforce)
//                .map(g -> buildRecursive(inputModel, g))
//                .collect(Collectors.toList());
//        return graph().directed()
//                // first the clusters
//                .with(rootClusterGraphs)
//                // now the nodes that are not part of anything
//                .with(inputModel.vertexSet().stream()
//                        .filter(Visualizable::conforms)
//                        .filter(v -> !GreyBox.conforms(v))
//                        .filter(v -> !nodes.containsKey(v))
//                        .map(vertex -> {
//                            final Node newNode = buildNodeFromVertex(vertex);
//                            nodes.put(vertex, newNode);
//                            return newNode;
//                        })
//                        .collect(Collectors.toList()))
//                // now make the edges
//                // src node and dst node
//                .with(inputModel.edgeSet().stream()
//                        .filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION))
//                        .filter(e -> {
//                            final Vertex srcVertex = inputModel.queryVertex(e.sourceId).get();
//                            final Vertex dstVertex = inputModel.queryVertex(e.targetId).get();
//                            return nodes.containsKey(srcVertex) && nodes.containsKey(dstVertex);
//                        })
//                        .map(edgeInfo -> {
//                            final Vertex srcVertex = inputModel.queryVertex(edgeInfo.sourceId).get();
//                            final Vertex dstVertex = inputModel.queryVertex(edgeInfo.targetId).get();
//                            final Node srcNode = nodes.get(srcVertex);
//                            final Node dstNode = nodes.get(dstVertex);
//                            return edgeInfo.getSourcePort().isPresent() && edgeInfo.getTargetPort().isPresent() ?
//                                    srcNode.link(between(port(edgeInfo.getSourcePort().get()), dstNode.port(edgeInfo.getTargetPort().get()))) :
//                                    edgeInfo.getSourcePort().isPresent() ?
//                                            srcNode.link(between(port(edgeInfo.getSourcePort().get()), dstNode)) :
//                                            edgeInfo.getTargetPort().isPresent() ?
//                                                    srcNode.link(dstNode.port(edgeInfo.getTargetPort().get())) :
//                                                    srcNode.link(dstNode);
//                        })
//                        .collect(Collectors.toList()))
//                .graphAttr()
//                .with(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
//        //return rootGraph.toImmutable();
//    }
//
//    protected Graph buildRecursive(final SystemGraph inputModel, final GreyBox greyBox) {
//        return graph(greyBox.getViewedVertex().getIdentifier()).cluster().directed()
//                // first sub clusters
//                .with(
//                        greyBox.getContainedPort(inputModel).stream()
//                                .filter(GreyBox::conforms)
//                                .map(GreyBox::enforce)
//                                .map(child -> {
//                                    final Graph graph = buildRecursive(inputModel, child);
//                                    clusters.put(child.getViewedVertex(), graph);
//                                    return graph;
//                                })
//                                .collect(Collectors.toList())
//                )
//                .with(
//                        greyBox.getContainedPort(inputModel).stream()
//                                .filter(v -> !GreyBox.conforms(v))
//                                .map(child -> {
//                                    final Node newNode = buildNodeFromVertex(child.getViewedVertex());
//                                    nodes.put(child.getViewedVertex(), newNode);
//                                    return newNode;
//                                })
//                                .collect(Collectors.toList())
//                )
//                .graphAttr().with(Label.of(greyBox.getViewedVertex().getIdentifier()))
//                .graphAttr().with(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));
//    }

    protected Node buildNodeFromVertex(final Vertex vertex) {
        return node(vertex.getIdentifier())
                .with(
                    Records.of(
                        Records.turn(
                            vertex.getIdentifier(),
                            //vertex.getTraits().stream().map(Trait::getName).collect(Collectors.joining(", ")),
                            Records.turn(vertex.getPorts().stream().map(portName -> Records.rec(portName, portName))
                                    .collect(Collectors.joining("|")))
                        )
                    )
                );
    }
}
