package forsyde.io.java.drivers;

import com.google.inject.Injector;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.lflang.LFStandaloneSetup;
import org.lflang.LFStandaloneSetupGenerated;
import org.lflang.lf.*;

import javax.inject.Inject;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ForSyDeLFDriver implements ForSyDeModelDriver {

    @Inject
    ResourceSet resourceSet;

    ForSyDeLFDriver() {
        Injector injector = new LFStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet = injector.getInstance(ResourceSet.class);
    }

    @Override
    public ForSyDeModel loadModel(Path inPath) throws Exception {
        final Resource res = resourceSet.getResource(URI.createURI(inPath.toAbsolutePath().toString()), true);
        return fromLFtoForSyDe((Model) res.getContents().get(0));
    }

    @Override
    public ForSyDeModel loadModel(InputStream in) throws Exception {
        final Resource res = resourceSet.getResource(URI.createURI("inmemory.lf"), true);
        res.load(in, resourceSet.getLoadOptions());
        return fromLFtoForSyDe((Model) res.getContents().get(0));
    }

    @Override
    public void writeModel(ForSyDeModel model, OutputStream out) throws Exception {
        throw new Exception("Lingua Franca writing is not supported");
    }

    protected ForSyDeModel fromLFtoForSyDe(Model model) {
        ForSyDeModel forSyDeModel = new ForSyDeModel();
        Reactor mainReactor = model.getReactors().stream().filter(Reactor::isMain).findAny().orElseThrow();
        Queue<Instantiation> instantiationQueue = new LinkedList<Instantiation>(mainReactor.getInstantiations());
        Queue<String> scopeQueue = new LinkedList<>(Collections.nCopies(mainReactor.getInstantiations().size(), "Main"));
        while (!instantiationQueue.isEmpty()) {
            Instantiation currentInstantiation = instantiationQueue.poll();
            String scopeName = scopeQueue.poll();
            String currentName = scopeName + "::" + currentInstantiation.getName();
            Vertex reactorInstVertex = new Vertex(currentName, VertexTrait.LinguaFrancaReactor);
            reactorInstVertex.ports.add("timers");
            reactorInstVertex.ports.add("reactions");
            forSyDeModel.addVertex(reactorInstVertex);
            if (currentInstantiation.getReactorClass() instanceof Reactor) {
                Reactor currentReactor = (Reactor) currentInstantiation.getReactorClass();
                for (Port port : currentReactor.getInputs()) {
                    reactorInstVertex.ports.add(port.getName());
                }
                for (Port port : currentReactor.getOutputs()) {
                    reactorInstVertex.ports.add(port.getName());
                }
                for (Timer timer : currentReactor.getTimers()) {
                    Vertex timerVertex = new Vertex(currentName + "::" +  timer.getName(), VertexTrait.LinguaFrancaTimer);
                    forSyDeModel.addVertex(timerVertex);
                    forSyDeModel.connect(reactorInstVertex, timerVertex, "timers", EdgeTrait.LinguaFrancaConnection);
                }
                int i = 0;
                for (Reaction reaction : currentReactor.getReactions()) {
                    Vertex reactionVertex = new Vertex(currentName + "::reaction" + i, VertexTrait.LinguaFrancaReaction);
                    forSyDeModel.addVertex(reactionVertex);
                    forSyDeModel.connect(reactorInstVertex, reactionVertex, "reactions", EdgeTrait.LinguaFrancaConnection);

                    i += 1;
                }
                instantiationQueue.addAll(currentReactor.getInstantiations());
                scopeQueue.addAll(Collections.nCopies(currentReactor.getInstantiations().size(), currentName));
            }
            // forSyDeModel.addVertex();
//            for (Instantiation instantiation : currentInstantiation.get) {
//
//            }
        }
        return forSyDeModel;
    }
}
