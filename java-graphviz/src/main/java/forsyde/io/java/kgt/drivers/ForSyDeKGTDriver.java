package forsyde.io.java.kgt.drivers;

import forsyde.io.java.core.*;
import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.typed.viewers.visualization.GreyBox;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import forsyde.io.java.typed.viewers.visualization.VisualizableWithProperties;
import org.ainslec.picocog.PicoWriter;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.LowestCommonAncestorAlgorithm;
import org.jgrapht.alg.lca.BinaryLiftingLCAFinder;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ForSyDeKGTDriver implements ForSyDeModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("kgt", "kgraph");
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        throw new UnsupportedOperationException("'ForSyDeKGTDriver' does not support loading Kgraph models.");
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        final ForSyDeSystemGraph modelCopy = new ForSyDeSystemGraph();
        modelCopy.mergeInPlace(model);
        final PicoWriter topWriter = new PicoWriter();
        // keep a map of writer to make sure that we can write everything correctly
        final Map<Vertex, PicoWriter> writers = new HashMap<>();
        final Map<Vertex, PicoWriter> extensionPoints = new HashMap<>();
        final Vertex forSyDeTopVertex = modelCopy.newVertex("ForSyDe Model");
        final GreyBox forSyDeTop = GreyBox.enforce(forSyDeTopVertex);
        // we make a fake top to make sure everything works even in the absence of a
        // container for all elements
        // topWriter.writeln_r("knode forsyde {");
        // topWriter.writeln("krectangle");
        // topWriter.writeln("klabelt \"ForSyDe Model\"");
        writers.put(forSyDeTopVertex, topWriter);
        for (Vertex v : modelCopy.vertexSet()) {
            Visualizable.safeCast(v).ifPresent(visual -> {
                final boolean isRoot = modelCopy.incomingEdgesOf(v).stream()
                        .noneMatch(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT));
                if (isRoot && !v.equals(forSyDeTopVertex)) {
                    forSyDeTop.insertContainedPort(modelCopy, visual);
                }
                final PicoWriter vWriter = new PicoWriter();
                writers.put(v, vWriter);
                // replace any special characters for ID generation
                final String vId = "v" + v.getIdentifier()
                        .replace(" ", "_")
                        .replace(".", "_");
                // write the node
                vWriter.writeln_r("knode " + vId + " {");
                // vWriter.writeln("krectangle");
                // write its label
                vWriter.writeln("klabel \"" + v.getIdentifier() + " [" + v.getTraits().stream().filter(t -> !t.refines(VertexTrait.VISUALIZATION_VISUALIZABLE)).map(Trait::getName).collect(Collectors.joining(", ")) + "]\"");
                VisualizableWithProperties.safeCast(v).ifPresent(visualizableWithProperties -> {
                    vWriter.writeln_r("knode vProperties {");
                    // TODO: until I figure out how to make the size of the node label be respected,
                    // this small sizing hack with width and height stays.
                    final int width = 5 * visualizableWithProperties.getVisualizedPropertiesNames().stream()
                            .mapToInt(p -> ("\\n " + p + ": " + v.getProperties().get(p).toString()).length()).sum();
                    final int height = 35 * visualizableWithProperties.getVisualizedPropertiesNames().size();
                    vWriter.writeln("size: width=" + width + " height=" + height);
                    vWriter.write("klabel \"properties:");
                    visualizableWithProperties.getVisualizedPropertiesNames()
                            .forEach(p -> vWriter.write("\\n " + p + ": " + v.getProperties().get(p).toString()));
                    vWriter.writeln("\"");
                    vWriter.writeln_l("}");
                });
                // write all its ports, which is like node and label again
                // as long as tehre is any visualizable connection incoming or outgoing
                v.getPorts().stream()
                        .filter(p -> modelCopy.incomingEdgesOf(v).stream()
                                .anyMatch(e -> e.getTargetPort().map(tp -> tp.equals(p)).orElse(false)
                                        && e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION))
                                ||
                                modelCopy.outgoingEdgesOf(v).stream()
                                        .anyMatch(e -> e.getSourcePort().map(sp -> sp.equals(p)).orElse(false)
                                                && e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)))
                        .forEach(p -> {
                            final String portString = p.replace(" ", "_").replace(".", "_");
                            vWriter.writeln_r("kport " + portString + " {");
                            vWriter.writeln("klabel \"" + p + "\"");
                            vWriter.writeln_l("}");
                        });

                extensionPoints.put(v, vWriter.createDeferredWriter());
                vWriter.writeln_l("}");
            });
        }
        // now add the edges, both in the same and in different hierarchies
        // check edges and prepare for children that come in the iteration
        // by defeering writers
        final Graph<Vertex, EdgeInfo> visuGraph = new AsSubgraph<Vertex, EdgeInfo>(modelCopy,
                modelCopy.vertexSet().stream().filter(Visualizable::conforms).collect(Collectors.toSet()),
                modelCopy.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)
                        || e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)).collect(Collectors.toSet()));
        final Graph<Vertex, EdgeInfo> connectionGraph = new AsSubgraph<Vertex, EdgeInfo>(visuGraph,
                visuGraph.vertexSet(),
                visuGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION))
                        .collect(Collectors.toSet()));
        for (EdgeInfo e : connectionGraph.edgeSet()) {
            final Vertex src = connectionGraph.getEdgeSource(e);
            final String srcPort = e.getSourcePort().orElse(src.getIdentifier());
            final Vertex dst = connectionGraph.getEdgeTarget(e);
            final String dstPort = e.getTargetPort().orElse(dst.getIdentifier());
            final PicoWriter srcWriter = extensionPoints.getOrDefault(src, topWriter);
            srcWriter.writeln("kedge ( " +
                    (srcPort.equals(src.getIdentifier()) ? "" : (":" + srcPort)) +
                    " -> " +
                    "v" + dst.getIdentifier().replace(" ", "_").replace(".", "_") +
                    (dstPort.equals(dst.getIdentifier()) ? "" : (":" + dstPort)) +
                    ")");
        }
        visuGraph.addVertex(forSyDeTopVertex);

        final Graph<Vertex, EdgeInfo> containmentGraph = new AsSubgraph<Vertex, EdgeInfo>(visuGraph,
                visuGraph.vertexSet(),
                visuGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))
                        .collect(Collectors.toSet()));

        // keep a set of created ports,
        // final Map<Vertex, Set<String>> addedPorts = new HashMap<>();
        // keep a map of children to parents
        // final Map<Vertex, Vertex> childrenToParents = new HashMap<>();
        // we first follow a minimum spanning tree direction so that the parent-children
        // relations are well respected.
        final ArrayDeque<Vertex> topoQueue = new ArrayDeque<>();
        new TopologicalOrderIterator<Vertex, EdgeInfo>(containmentGraph).forEachRemaining(topoQueue::push);
        while (!topoQueue.isEmpty()) {
            final Vertex src = topoQueue.pop();
            final PicoWriter srcExtensions = extensionPoints.get(src);
            containmentGraph.outgoingEdgesOf(src).forEach(edgeInfo -> {
                final Vertex dst = containmentGraph.getEdgeTarget(edgeInfo);
                srcExtensions.writeln(writers.get(dst));
            });
        }
            /*final PicoWriter vWriter = writers.getOrDefault(v, topWriter);
            // replace any special characters for ID generation
            final String vId = "v" + v.getIdentifier()
                    .replace(" ", "_")
                    .replace(".", "_");
            // write the node
            vWriter.writeln_r("knode " + vId + " {");
            // vWriter.writeln("krectangle");
            // write its label
            vWriter.writeln("klabel \"" + v.getIdentifier() + " [" + v.getTraits().stream().filter(t -> t.refines(VertexTrait.VISUALIZATION_VISUALIZABLE)).map(Trait::getName).collect(Collectors.joining(", ")) + "]\"");
            VisualizableWithProperties.safeCast(v).ifPresent(visualizableWithProperties -> {
                vWriter.writeln_r("knode vProperties {");
                // TODO: until I figure out how to make the size of the node label be respected,
                // this small sizing hack with width and height stays.
                final int width = 5 * visualizableWithProperties.getVisualizedPropertiesNames().stream()
                        .mapToInt(p -> ("\\n " + p + ": " + v.getProperties().get(p).toString()).length()).sum();
                final int height = 35 * visualizableWithProperties.getVisualizedPropertiesNames().size();
                vWriter.writeln("size: width=" + width + " height=" + height);
                vWriter.write("klabel \"properties:");
                visualizableWithProperties.getVisualizedPropertiesNames()
                        .forEach(p -> vWriter.write("\\n " + p + ": " + v.getProperties().get(p).toString()));
                vWriter.writeln("\"");
                vWriter.writeln_l("}");
            });
            // write all its ports, which is like node and label again
            // as long as tehre is any visualizable connection incoming or outgoing
            v.getPorts().stream()
                    .filter(p -> modelCopy.incomingEdgesOf(v).stream()
                            .anyMatch(e -> e.getTargetPort().map(tp -> tp.equals(p)).orElse(false)
                                    && e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION))
                            ||
                            modelCopy.outgoingEdgesOf(v).stream()
                                    .anyMatch(e -> e.getSourcePort().map(sp -> sp.equals(p)).orElse(false)
                                            && e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)))
                    .forEach(p -> {
                        final String portString = p.replace(" ", "_").replace(".", "_");
                        vWriter.writeln_r("kport " + portString + " {");
                        vWriter.writeln("klabel \"" + p + "\"");
                        vWriter.writeln_l("}");
                    });

            // take care to create children writers
            GreyBox.safeCast(v).ifPresent(greyBox -> {
                for (Visualizable child : greyBox.getContainedPort(modelCopy)) {
                    vWriter.writeln("// for " + child.getIdentifier());
                    writers.put(child.getViewedVertex(), vWriter.createDeferredWriter());
                }
            });

             extensionPoints.put(v, vWriter.createDeferredWriter());
            vWriter.writeln_l("}");
        });*/

        // now add the edges, both in the same and in different hierarchies
        // check edges and prepare for children that come in the iteration
        // by defeering writers
        /*for (EdgeInfo e : connectionGraph.edgeSet()) {
            final Vertex src = connectionGraph.getEdgeSource(e);
            final String srcPort = e.getSourcePort().orElse(src.getIdentifier());
            final Vertex dst = connectionGraph.getEdgeTarget(e);
            final String dstPort = e.getTargetPort().orElse(dst.getIdentifier());
            final PicoWriter srcWriter = extensionPoints.getOrDefault(src, topWriter);
            srcWriter.writeln("kedge ( " +
                    (srcPort.equals(src.getIdentifier()) ? "" : (":" + srcPort)) +
                    " -> " +
                    "v" + dst.getIdentifier().replace(" ", "_").replace(".", "_") +
                    (dstPort.equals(dst.getIdentifier()) ? "" : (":" + dstPort)) +
                    ")");

            // // now we transform all long hierarchical nodes to short hierarchical nodes
            // if (!roots.isEmpty()) {
            *//*
            final LowestCommonAncestorAlgorithm<Vertex> lowestCommonAncestorAlgorithm
                    =
                    new BinaryLiftingLCAFinder<>(
                            containmentGraph,
                            forSyDeTopVertex
                    );

            // connectionGraph.edgeSet().forEach(e -> {
            final Vertex src = connectionGraph.getEdgeSource(e);
            final String srcPort = e.getSourcePort().orElse(src.getIdentifier());
            final Vertex dst = connectionGraph.getEdgeTarget(e);
            final String dstPort = e.getTargetPort().orElse(dst.getIdentifier());
            // if both are not root elements themselves
            // if (childrenToParents.containsKey(src) || childrenToParents.containsKey(dst))
            // {
            final Vertex ancestor = lowestCommonAncestorAlgorithm.getLCA(src, dst);
            // create links and ports from the src to ancestor;

            Vertex backwardsIt = dst;
            String backwardsItPort = dstPort;
            // the same logic for going up as before
            while (backwardsIt != ancestor
                    && (getParentInContainmentGraph(containmentGraph, backwardsIt) != ancestor)) {
                final Vertex parent = getParentInContainmentGraph(containmentGraph, backwardsIt);
                // final PicoWriter itWriter = extensionPoints.getOrDefault(it, topWriter);
                final PicoWriter parentWriter = extensionPoints.getOrDefault(parent,
                        topWriter);
                final String childString = "v" + backwardsIt.getIdentifier().replace(" ", "_")
                        .replace(".", "_");
                final String portString = dstPort.replace(" ", "_").replace(".", "_");
                if (!parent.hasPort(portString)) {
                    parentWriter.writeln("// port created by long-to-short translation - backwards");
                    parentWriter.writeln_r("kport " + portString + " {");
                    // parentWriter.writeln("klabel \"" + dstPort + "_transformed" + "\"");
                    parentWriter.writeln_l("}");
                    parent.addPort(portString);
                }
                parentWriter.writeln("// translation from long to short hierarchy - backwards");
                parentWriter.writeln("kedge ( " +
                        ":" + portString +
                        " -> " +
                        childString +
                        (backwardsItPort.equals(dst.getIdentifier()) ? "" : (":" + backwardsItPort)) +
                        ")");
                backwardsIt = parent;
                backwardsItPort = portString;
            }
            // if there is no common ancestor, then the iteration should go all the way
            // to the top
            Vertex upwardsIt = src;
            String upwardsItPort = srcPort;
            while (upwardsIt != ancestor
                    && (getParentInContainmentGraph(containmentGraph, upwardsIt) != ancestor)) {
                final Vertex parent = getParentInContainmentGraph(containmentGraph, upwardsIt);
                final PicoWriter itWriter = extensionPoints.getOrDefault(upwardsIt, topWriter);
                final PicoWriter parentWriter = extensionPoints.getOrDefault(parent, topWriter);
                final String parentString = "v" + parent.getIdentifier().replace(" ", "_")
                        .replace(".", "_");
                final String portString = srcPort.replace(" ", "_").replace(".", "_");
                if (!parent.hasPort(portString)) {
                    parentWriter.writeln("// port created by long-to-short translation - upwards");
                    parentWriter.writeln_r("kport " + portString + " {");
                    // parentWriter.writeln("klabel \"" + srcPort + "_transformed" + "\"");
                    parentWriter.writeln_l("}");
                    parent.addPort(portString);
                }
                itWriter.writeln("// translation from long to short hierarchy - upwards");
                itWriter.writeln("kedge ( " +
                        (upwardsItPort.equals(src.getIdentifier()) ? "" : (":" + upwardsItPort)) +
                        " -> " +
                        parentString +
                        ":" + portString +
                        ")");
                upwardsIt = parent;
                upwardsItPort = portString;
            }
            // // save for a global connection later
            // Vertex lowestSrc = it;
            // String lowestSrcPort = itPort;
            // // do the same for the ancestor to dst
            // finally connect the elemsnts in the context of the common ancestor
            final PicoWriter ancestorSrcWriter = extensionPoints.getOrDefault(upwardsIt, topWriter);
            ancestorSrcWriter.writeln("// translation from long to short hierarchy - horizontal");
            ancestorSrcWriter.writeln("kedge ( " +
                    (upwardsItPort.equals(src.getIdentifier()) ? "" : (":" + upwardsItPort)) +
                    " -> " +
                    "v" + backwardsIt.getIdentifier().replace(" ", "_").replace(".", "_") +
                    (backwardsItPort.equals(dst.getIdentifier()) ? "" : (":" + backwardsItPort)) +
                    ")");
             *//*
        }*/

        // now we finally close the writers and close the "open" descriptions
        // writers.values().forEach(w -> w.writeln_l("}"));
        // now we close the top guy
        // topWriter.writeln_l("}");
        out.write(writers.get(forSyDeTopVertex).toString().getBytes(StandardCharsets.UTF_8));
    }

    protected Vertex getParentInContainmentGraph(Graph<Vertex, EdgeInfo> containmentGraph, Vertex child) {
        return containmentGraph.incomingEdgesOf(child).stream()
                .filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))
                .map(containmentGraph::getEdgeSource)
                .filter(GreyBox::conforms)
                .findFirst().orElse(null);
    }
}
