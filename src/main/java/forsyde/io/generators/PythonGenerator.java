package forsyde.io.generators;	

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import forsyde.io.generators.python.types.TypePackageToPython;
import forsyde.io.generators.utils.Packages;

public class PythonGenerator extends  TypesGenerator {
	

	public void generate(String typeSrc, String typeDst) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecoreTypes = resourceSet.getResource(URI.createFileURI(typeSrc), true);
		
		EPackage forSyDeTypes = (EPackage) fecoreTypes.getContents().get(0);
		
		final String packageRoot = typeDst;
		
		
		final CharSequence produced = TypePackageToPython.toText(forSyDeTypes);
		Files.createDirectories(Paths.get(packageRoot));
		Files.writeString(Paths.get(packageRoot, "types.py"), produced);
//		processPackage(forSyDeTypes, packageRoot);
//		TreeIterator<EObject> iterator = forSyDeTypes.eAllContents();
//		while (iterator.hasNext()) {
//			EObject elem = iterator.next();
//			if(elem instanceof EPackage) {
//				EPackage pak = (EPackage) elem;
//				processPackage(pak, packageRoot);
//			}
//		}
	}
	
	private void processPackage(EPackage pak, String packageRoot) throws IOException {
		final CharSequence produced = TypePackageToPython.toText(pak);
		String filePathParent = Packages.getPackageSequence(pak).stream()
				.filter((e) -> e != pak)
				.map(p -> p.getName().toLowerCase())
				.reduce((s1, s2) -> s1 + '/' + s2)
				.orElse(".");
		Path fileDir;
		Path fileTotal;
		
		if (pak.getESubpackages().isEmpty() == false) {
			fileDir = Paths.get(packageRoot, filePathParent, pak.getName().toLowerCase());
			fileTotal = Paths.get(packageRoot, filePathParent, pak.getName().toLowerCase(), "__init__.py");
		} else {
			fileDir = Paths.get(packageRoot, filePathParent);
			fileTotal = Paths.get(packageRoot, filePathParent, pak.getName().toLowerCase() + ".py");
		}
		Files.createDirectories(fileDir);
		Files.writeString(fileTotal, produced);
	}
}
