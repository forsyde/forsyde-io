package forsyde.io.generators;	

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import forsyde.io.generators.python.types.TypePackageToPython;

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
	}

}
