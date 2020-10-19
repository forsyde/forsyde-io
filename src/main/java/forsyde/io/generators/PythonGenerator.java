package forsyde.io.generators;	

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

import forsyde.io.generators.java.TypesToJava;
import forsyde.io.generators.python.PackageToPython;
import forsyde.io.generators.utils.Packages;

public class PythonGenerator {

	public void generate() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI("ecore/types.ecore"), true);
		
		EPackage ForSyDe = (EPackage) fecore.getContents().get(0);
		
		final String packageRoot = "python-io/forsyde/io/python";
		
		TreeIterator<EObject> iterator = ForSyDe.eAllContents();
		//Files.deleteIfExists(Paths.get(packageRoot, ForSyDe.getName().toLowerCase()));
		while (iterator.hasNext()) {
			EObject elem = iterator.next();
			if(elem instanceof EPackage) {
				EPackage pak = (EPackage) elem;
				final CharSequence produced = PackageToPython.toText(pak);
				String filePath = Packages.getPackageSequence(pak).stream()
						.filter((e) -> e != pak)
						.map(p -> p.getName().toLowerCase())
						.reduce((s1, s2) -> s1 + '/' + s2)
						.orElseThrow();
				Path fileDir = Paths.get(packageRoot, filePath);
				Path fileTotal = Paths.get(packageRoot, filePath, pak.getName().toLowerCase() + ".py");
				if (pak.getESubpackages().isEmpty() == false) {
					fileTotal = Paths.get(packageRoot, filePath, "__init__.py");
				}
				Files.createDirectories(fileDir);
				Files.writeString(fileTotal, produced);
			}
		}
	}
	
}
