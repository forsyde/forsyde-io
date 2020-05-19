package forsyde.io.generators;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class HaskellGenerator {

	public void generate() throws IOException {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("*", new ResourceFactoryImpl());
		
		Resource forsydeRes = resourceSet.getResource(URI.createFileURI("model/forsyde.ecore"), true);
		forsydeRes.load(Collections.EMPTY_MAP);
		
		EPackage forSyDePackage = (EPackage) forsydeRes.getContents().get(0);
	}
	
}
