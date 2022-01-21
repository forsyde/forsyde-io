package forsyde.io.java.drivers;

import forsyde.io.java.core.ForSyDeSystemGraph;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.emf.AmaltheaResourceFactory;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaLoader;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaWriter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class ForSyDeAmaltheaDriver implements ForSyDeModelDriver {


    @Override
    public List<String> inputExtensions() {
        return List.of("amxmi");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("amxmi");
    }

    @Override
    public ForSyDeSystemGraph loadModel(File file) throws Exception {
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        final Amalthea amalthea = AmaltheaLoader.loadFromFile(file);
        return amaltheaAdapter.convert(amalthea);
    }

    @Override
    public ForSyDeSystemGraph loadModel(Path inPath) throws Exception {
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        final Amalthea amalthea = AmaltheaLoader.loadFromURI(URI.createURI(inPath.toUri().toString()));
        return amaltheaAdapter.convert(amalthea);
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        final AmaltheaResourceFactory amaltheaResourceFactory = new AmaltheaResourceFactory();
        final Resource res = amaltheaResourceFactory.createResource(URI.createURI("inmemory_amxmi.amxmi"));
        //final Resource res = resourceSet.getResource(URI.createURI("inmemory.lf"), true);
        res.load(in, res.getResourceSet().getLoadOptions());
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        return amaltheaAdapter.convert((Amalthea) res.getContents().get(0));
        //throw new Exception("Amalthea models cannot be consumed from InputStreams directly");
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, Path outPath) throws Exception {
        final AmaltheaAdapter amaltheaAdapter = new AmaltheaAdapter();
        final Amalthea amalthea = amaltheaAdapter.convert(model);
        AmaltheaWriter.writeToURI(amalthea, URI.createURI(outPath.toUri().toString()));
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        throw new Exception("Amalthea models cannot be produced to OutputStreams directly");
    }
}
