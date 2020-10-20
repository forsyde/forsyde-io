package forsyde.io.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import forsyde.io.generators.haskell.types.TypePackageToHaskell;
import forsyde.io.generators.python.types.TypePackageToPython;
import forsyde.io.generators.utils.Packages;

public class HaskellGenerator {
	
	public void generate() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI("ecore/types.ecore"), true);
		
		EPackage forSyDeTypes = (EPackage) fecore.getContents().get(0);
		
		processPackage(forSyDeTypes, "haskell-io/src/ForSyDe/IO/Haskell");
		TreeIterator<EObject> iterator = forSyDeTypes.eAllContents();
		while(iterator.hasNext()) {
			Object e = iterator.next();
			if (e instanceof EPackage) {
				EPackage pac = (EPackage) e;
				processPackage(forSyDeTypes, "haskell-io/src/ForSyDe/IO/Haskell");
			}
		}

	}
	
	private void processPackage(EPackage pak, String packageRoot) throws IOException {
		final CharSequence produced = TypePackageToHaskell.toText(pak);
		String filePathParent = Packages.getPackageSequence(pak).stream()
				.filter((p) -> p != pak)
				.map(p -> p.getName())
				.reduce((s1, s2) -> s1 + '/' + s2)
				.orElse(".");
		Path fileDir;
		Path fileTotal;
		fileDir = Paths.get(packageRoot, filePathParent);
		fileTotal = Paths.get(packageRoot, filePathParent, pak.getName() + ".hs");
		Files.createDirectories(fileDir);
		Files.writeString(fileTotal, produced);
	}
	
}
