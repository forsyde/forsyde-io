package forsyde.io.java.drivers;

import forsyde.io.java.adapters.AmaltheaAdapter;
import forsyde.io.java.core.ForSyDeModel;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaLoader;
import org.eclipse.emf.common.util.URI;

import java.io.*;
import java.nio.file.Path;

public class ForSyDeAmaltheaDriver implements ForSyDeModelDriver {

    @Override
    public ForSyDeModel loadModel(File file) throws Exception {
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        final Amalthea amalthea = AmaltheaLoader.loadFromFile(file);
        return amaltheaAdapter.convert(amalthea);
    }

    @Override
    public ForSyDeModel loadModel(Path inPath) throws Exception {
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        final Amalthea amalthea = AmaltheaLoader.loadFromURI(URI.createURI(inPath.toUri().toString()));
        return amaltheaAdapter.convert(amalthea);
    }

    @Override
    public ForSyDeModel loadModel(InputStream in) throws Exception {
        throw new Exception("Amalthea models cannot be consumed from InputStreams directly");
    }

    @Override
    public void writeModel(ForSyDeModel model, Path outPath) throws Exception {
        throw new Exception("Writing Amalthea models is still not supported");
    }

    @Override
    public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
        throw new Exception("Amalthea models cannot be produced to OutputStreams directly");
    }
}
