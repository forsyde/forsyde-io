package forsyde.io.kgraph.drivers;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.drivers.ForSyDeModelDriver;
import forsyde.io.java.typed.viewers.visualization.GreyBox;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import org.ainslec.picocog.PicoWriter;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.LowestCommonAncestorAlgorithm;
import org.jgrapht.alg.lca.BinaryLiftingLCAFinder;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        final PicoWriter topWriter = new PicoWriter();
        // we make a fake top to make sure everything works even in the absence of a container for all elements
        topWriter.writeln_r("knode forsyde {");
        topWriter.writeln("krectangle");
        topWriter.writeln("klabel \"ForSyDe Model\"");
        // first, we filter only the visualizable elements of the model
        final Graph<Vertex, EdgeInfo> visuGraph = new AsSubgraph<Vertex, EdgeInfo>(model,
                model.vertexSet().stream().filter(Visualizable::conforms).collect(Collectors.toSet()),
                model.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION) || e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)).collect(Collectors.toSet())
        );
        final Graph<Vertex, EdgeInfo> containmentGraph = new AsSubgraph<Vertex, EdgeInfo>(visuGraph,
                visuGraph.vertexSet(),
                visuGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)).collect(Collectors.toSet())
        );
        final Graph<Vertex, EdgeInfo> connectionGraph = new AsSubgraph<Vertex, EdgeInfo>(visuGraph,
                visuGraph.vertexSet(),
                visuGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)).collect(Collectors.toSet())
        );
        // keep a map of writer to make sure that we can write everything correctly
        final Map<Vertex, PicoWriter> writers = new HashMap<>();
        final Map<Vertex, PicoWriter> extensionPoints = new HashMap<>();
        // keep a map of children to parents
        final Map<Vertex, Vertex> childrenToParents = new HashMap<>();
        // we first follow a minimum spanning tree direction so that the parent-children relations are well respected.
        new TopologicalOrderIterator<Vertex, EdgeInfo>(containmentGraph).forEachRemaining(v -> {
            final PicoWriter vWriter = writers.getOrDefault(v, topWriter);
            // replace any special characters for ID generation
            final String vId = "v" + v.getIdentifier()
                    .replace(" ", "_")
                    .replace(".", "_");
            // write the node
            vWriter.writeln_r("knode " + vId + " {");
            vWriter.writeln("krectangle");
            // write its label
            vWriter.writeln("klabel \"" + v.getIdentifier() + "\"");
            // write all its ports, which is like node and label again
            v.getPorts().forEach(p -> {
                final String portString = p.replace(" ", "_").replace(".", "_");
                vWriter.writeln_r("kport " + portString + " {");
                vWriter.writeln("klabel \"" + p + "\"");
                vWriter.writeln_l("}");
            });
            // check edges and prepare for children that come in the iteration
            // by defeering writers
            visuGraph.outgoingEdgesOf(v).forEach(e -> {
                final Vertex dst = model.getEdgeTarget(e);
                // replace any special characters for ID generation
                final String dstId = "v" + dst.getIdentifier()
                        .replace(" ", "_")
                        .replace(".", "_");
                // first case: it has a child,
                // second case: it just connects
                if (e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT) && GreyBox.conforms(v)) {
                    writers.put(dst, vWriter.createDeferredWriter());
                    childrenToParents.put(dst, v);
//                        vWriter.writeln("kedge (-> " + dstId + ")");
                }
                if (e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)) {
                    vWriter.writeln("kedge ( " +
                            e.getSourcePort().map(s -> ":" + s).orElse("") +
                            " -> " +
                            dstId +
                            e.getTargetPort().map(s -> ":" + s).orElse("") +
                            ")");
                }
            });
            extensionPoints.put(v, vWriter.createDeferredWriter());
            vWriter.writeln_l("}");
        });

        // set up the LCA algorithm
        final Set<Vertex> roots = containmentGraph.vertexSet().stream().filter(v -> containmentGraph.incomingEdgesOf(v).isEmpty()).collect(Collectors.toSet());

        // now we transform all long hierarchical nodes to short hierarchical nodes
        if (!roots.isEmpty()) {
            final LowestCommonAncestorAlgorithm<Vertex> lowestCommonAncestorAlgorithm = new BinaryLiftingLCAFinder<>(
                    containmentGraph,
                    containmentGraph.vertexSet().stream().filter(v -> containmentGraph.incomingEdgesOf(v).isEmpty()).collect(Collectors.toSet())
            );

            connectionGraph.edgeSet().forEach(e -> {
                final Vertex src = connectionGraph.getEdgeSource(e);
                final String srcPort = e.getSourcePort().orElse(src.getIdentifier());
                final Vertex dst = connectionGraph.getEdgeTarget(e);
                final String dstPort = e.getTargetPort().orElse(dst.getIdentifier());
                // if both are not root elements themselves
                if (childrenToParents.containsKey(src) || childrenToParents.containsKey(dst)) {
                    final Vertex ancestor = lowestCommonAncestorAlgorithm.getLCA(src, dst);
                    // create links and ports from the src to ancestor;
                    Vertex it = src;
                    String itPort = srcPort;
                    // if there is no common ancestor, then the iteration should go all the way
                    // to the top
                    while (
                            childrenToParents.containsKey(it) && (childrenToParents.get(it) != ancestor || ancestor == null)
                    ) {
                        final Vertex parent = childrenToParents.get(it);
                        final PicoWriter itWriter = extensionPoints.getOrDefault(it, topWriter);
                        final PicoWriter parentWriter = extensionPoints.getOrDefault(parent, topWriter);
                        final String parentString = "v" + parent.getIdentifier().replace(" ", "_")
                                .replace(".", "_");
                        final String portString = srcPort.replace(" ", "_").replace(".", "_");
                        parentWriter.writeln("// port created by long-to-short translation");
                        parentWriter.writeln_r("kport " + portString + " {");
                        parentWriter.writeln("klabel \"" + srcPort + "_transformed" + "\"");
                        parentWriter.writeln_l("}");
                        itWriter.writeln("// translation from long to short hierarchy");
                        itWriter.writeln("kedge ( " +
                                (itPort.equals(src.getIdentifier()) ? "" : (":" + itPort)) +
                                " -> " +
                                parentString +
                                ":" + portString +
                                ")");
                        it = parent;
                        itPort = portString;
                    }
                    // save for a global connection later
                    Vertex lowestSrc = it;
                    String lowestSrcPort = itPort;
                    // do the same for the ancestor to dst
                    it = dst;
                    itPort = dstPort;
                    // the same logic for going up as before
                    while (
                            childrenToParents.containsKey(it) && (childrenToParents.get(it) != ancestor || ancestor == null)
                    ) {
                        final Vertex parent = childrenToParents.get(it);
    //                    final PicoWriter itWriter = extensionPoints.getOrDefault(it, topWriter);
                        final PicoWriter parentWriter = extensionPoints.getOrDefault(parent, topWriter);
                        final String itString = "v" + it.getIdentifier().replace(" ", "_")
                                .replace(".", "_");
                        final String portString = dstPort.replace(" ", "_").replace(".", "_");
                        parentWriter.writeln("// port created by long-to-short translation");
                        parentWriter.writeln_r("kport " + portString + " {");
                        parentWriter.writeln("klabel \"" + dstPort + "_transformed" + "\"");
                        parentWriter.writeln_l("}");
                        parentWriter.writeln("// translation from long to short hierarchy");
                        parentWriter.writeln("kedge ( " +
                                ":" + portString +
                                " -> " +
                                itString +
                                (itPort.equals(dst.getIdentifier()) ? "" : (":" + itPort)) +
                                ")");
                        it = parent;
                        itPort = portString;
                    }
                    // finally connect the elemsnts in the context of the common ancestor
                    final PicoWriter ancestorSrcWriter = extensionPoints.getOrDefault(lowestSrc, extensionPoints.getOrDefault(ancestor, topWriter));
                    ancestorSrcWriter.writeln("// translation from long to short hierarchy");
                    ancestorSrcWriter.writeln("kedge ( " +
                            (lowestSrcPort.equals(src.getIdentifier()) ? "" : (":" + lowestSrcPort)) +
                            " -> " +
                            "v" + it.getIdentifier().replace(" ", "_").replace(".", "_") +
                            (itPort.equals(dst.getIdentifier()) ? "" : (":" + itPort)) +
                            ")"
                    );
                }
            });
        }

        // now we finally close the writers and close the "open" descriptions
//        writers.values().forEach(w -> w.writeln_l("}"));
        // now we close the top guy
        topWriter.writeln_l("}");
        out.write(topWriter.toString().getBytes(StandardCharsets.UTF_8));
    }
}
