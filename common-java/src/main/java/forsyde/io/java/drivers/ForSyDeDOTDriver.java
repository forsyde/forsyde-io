package forsyde.io.java.drivers;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ForSyDeDOTDriver implements ForSyDeModelDriver {

    DOTExporter<Vertex, EdgeInfo> dotExporter;

    ForSyDeDOTDriver() {
        dotExporter = new DOTExporter<>(v -> v.getIdentifier()
                .replace(".", "_")
                .replace("-", "__to__")
                .replace(" ", "__"));
        dotExporter.setEdgeIdProvider(e -> e.toIDString()
                .replace(".", "_")
                .replace("-", "__to__")
                .replace(" ", "__"));
    }

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("dot", "gv", "graphviz", "plain.gv", "plain.graphviz");
    }

    @Override
    @Deprecated
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        throw new Exception("read DOT representations is not supported.");
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        dotExporter.exportGraph(model, out);
    }
}
