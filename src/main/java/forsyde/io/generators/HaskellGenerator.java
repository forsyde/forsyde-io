package forsyde.io.generators;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

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
	
}
