package forsyde.io.java.drivers;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.InputStream;
import java.io.OutputStream;

public class ForSyDeDOTDriver implements ForSyDeModelDriver {

    DOTExporter<Vertex, Edge> dotExporter;

    ForSyDeDOTDriver() {
        dotExporter = new DOTExporter<>(Vertex::getIdentifier);
        dotExporter.setEdgeIdProvider(Edge::toIDString);
    }

    @Override
    @Deprecated
    public ForSyDeModel loadModel(InputStream in) throws Exception {
        throw new Exception("read DOT representations is not supported.");
    }

    @Override
    public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
        dotExporter.exportGraph(model, out);
    }
}
