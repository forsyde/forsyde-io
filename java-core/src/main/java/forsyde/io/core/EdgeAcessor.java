package forsyde.io.core;

/**
 * This interface enables proper code generation in which the edge traits have classes
 * representing them, so that we can manipulate the system graph more ergonomically.
 *
 * It is the same idea that {@link VertexViewer} has, but for edge traits.
 *
 */
public interface EdgeAcessor {

    EdgeTrait[] getEdgeTraits();

    default boolean isAllowedSource(VertexViewer src) {
        return isAllowedSource(src.getViewedVertex());
    }

    default boolean isAllowedTarget(VertexViewer dst) {
        return isAllowedTarget(dst.getViewedVertex());
    }

    default boolean isAllowedSource(Vertex src) {
        return true;
    }

    default boolean isAllowedTarget(Vertex dst) {
        return true;
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, VertexViewer src, VertexViewer dst) {
        createStatic(edgeAcessor, systemGraph, src.getViewedVertex(), dst.getViewedVertex());
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, VertexViewer src, VertexViewer dst, String srcPort) {
        createStatic(edgeAcessor, systemGraph, src.getViewedVertex(), dst.getViewedVertex(), srcPort);
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, VertexViewer src, VertexViewer dst, String srcPort, String dstPort) {
        createStatic(edgeAcessor, systemGraph, src.getViewedVertex(), dst.getViewedVertex(), srcPort, dstPort);
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, Vertex src, Vertex dst) {
        if (edgeAcessor.isAllowedSource(src) && edgeAcessor.isAllowedTarget(dst)) {
            systemGraph.connect(src, dst, edgeAcessor.getEdgeTraits());
        }
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, Vertex src, Vertex dst, String srcPort) {
        if (edgeAcessor.isAllowedSource(src) && edgeAcessor.isAllowedTarget(dst)) {
            if(srcPort != null) src.addPort(srcPort);
            systemGraph.connect(src, dst, srcPort, edgeAcessor.getEdgeTraits());
        }
    }

    static void createStatic(EdgeAcessor edgeAcessor, SystemGraph systemGraph, Vertex src, Vertex dst, String srcPort, String dstPort) {
        if (edgeAcessor.isAllowedSource(src) && edgeAcessor.isAllowedTarget(dst)) {
            if(srcPort != null) src.addPort(srcPort);
            if(dstPort != null) dst.addPort(dstPort);
            systemGraph.connect(src, dst, srcPort, dstPort, edgeAcessor.getEdgeTraits());
        }
    }
}
