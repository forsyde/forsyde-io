package forsyde.io.java.graphviz;

import forsyde.io.graphviz.ForSyDeGraphVizAdapter;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelDriver;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * ForSyDeGraphVizDriver is meant to produce diagrams in a more appealing than the
 * standard {@link forsyde.io.java.drivers.ForSyDeDOTDriver}, which simply spits out
 * the systems graph.
 *
 * This takes care of also processing 'visualization' traits.
 */
public class ForSyDeGraphVizDriver implements ForSyDeModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("dot", "gv", "graphviz");
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        throw new UnsupportedOperationException("'ForSyDeGraphVizDriver' does not support loading graphviz models.");
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        final ForSyDeGraphVizAdapter forSyDeGraphVizAdapter = new ForSyDeGraphVizAdapter();
        final Graph vizGraph = forSyDeGraphVizAdapter.convert(model);
        Graphviz.fromGraph(vizGraph).render(Format.XDOT).toOutputStream(out);
    }
}
