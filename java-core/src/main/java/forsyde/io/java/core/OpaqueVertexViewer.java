package forsyde.io.java.core;

import java.util.Optional;

public class OpaqueVertexViewer implements VertexViewer {

    final SystemGraph systemGraph;
    final Vertex vertex;
    @Override
    public Vertex getViewedVertex() {
        return vertex;
    }

    @Override
    public String getTraitName() {
        return "";
    }

    protected OpaqueVertexViewer(SystemGraph systemGraph, Vertex vertex) {
        this.systemGraph = systemGraph;
        this.vertex = vertex;
    }

    public static Optional<VertexViewer> tryView(SystemGraph systemGraph, Vertex vertex) {
        return Optional.of(new OpaqueVertexViewer(systemGraph, vertex));
    }
}
