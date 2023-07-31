package forsyde.io.visual.graphviz;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.drivers.ModelDriver;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * ForSyDeGraphVizDriver is meant to produce diagrams the graphviz description language.
 *
 * This takes care of also processing 'visualization' traits.
 */
public class GraphVizDriver implements ModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("gv", "graphviz");
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        throw new UnsupportedOperationException("'ForSyDeGraphVizDriver' does not support loading graphviz models.");
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {
        final ForSyDeGraphVizAdapter forSyDeGraphVizAdapter = new ForSyDeGraphVizAdapter();
        final Graph vizGraph = forSyDeGraphVizAdapter.convert(model);
        Graphviz.fromGraph(vizGraph).render(Format.XDOT).toOutputStream(out);
    }
}
