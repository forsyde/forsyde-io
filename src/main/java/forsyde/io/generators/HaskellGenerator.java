package forsyde.io.generators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

import com.google.common.collect.Streams;

import forsyde.io.generators.haskell.ClassToHaskell;
import forsyde.io.generators.haskell.PackageToHaskell;

public class HaskellGenerator {
	
	public void generate() {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI("model/forsyde.ecore"), true);
		
		EPackage ForSyDe = (EPackage) fecore.getContents().get(0);
		
		ForSyDe.eAllContents().forEachRemaining(e -> {
			if (e instanceof EPackage) {
				EPackage pac = (EPackage) e;
				// count if it has anything besides other packages within
				if (pac.getEClassifiers().stream()
						.filter(c -> !(c instanceof EPackage))
						.count() > 0L) {
					System.out.println(PackageToHaskell.toText(pac));
				}
			}
		});

	}

	public void generate2() throws Exception {
		
		// Parse main.egx
        EgxModule module = new EgxModule(new EglFileGeneratingTemplateFactory());
        module.parse(new File("epsilon/forsyde2hs.egx").getAbsoluteFile());

        if (!module.getParseProblems().isEmpty()) {
          System.out.println("Syntax errors found.");
        }

        // Load the XML document
		EmfModel model = new EmfModel();
        model.setModelFile("model/forsyde.ecore");
        model.setName("ForSyDe");
        model.load();

        // Make the document visible to the EGX program
        module.getContext().getModelRepository().addModel(model);
        // ... and execute
        module.execute();
		
	}
	
}
