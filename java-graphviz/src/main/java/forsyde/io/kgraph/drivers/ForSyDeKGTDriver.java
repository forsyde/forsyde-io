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
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        // first, we filter only the visualizable elements of the model
        final Graph<Vertex, EdgeInfo> visuGraph = new AsSubgraph<Vertex, EdgeInfo>(model,
                model.vertexSet().stream().filter(Visualizable::conforms).collect(Collectors.toSet()),
                model.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION) || e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)).collect(Collectors.toSet())
        );
        final Graph<Vertex, EdgeInfo> containmentGraph = new AsSubgraph<Vertex, EdgeInfo>(visuGraph,
                visuGraph.vertexSet(),
                visuGraph.edgeSet().stream().filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT)).collect(Collectors.toSet())
        );
        // keep a map of writer to make sure that we can write everything correctly
        final Map<Vertex, PicoWriter> writers = new HashMap<>();
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
            vWriter.writeln_l("}");
        });

        out.write(topWriter.toString().getBytes(StandardCharsets.UTF_8));
    }
}
