package forsyde.io.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import forsyde.io.generators.java.ClassToJava;
import forsyde.io.generators.utils.Packages;

public class JavaGenerator {
	
	public void generate() {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI("model/forsyde.ecore"), true);
		
		EPackage ForSyDe = (EPackage) fecore.getContents().get(0);
		
		final String packageRoot = "java-io/src/main/java";
		
		ForSyDe.eAllContents().forEachRemaining(elem -> {
			if (elem instanceof EClass) {
				EClass cls = (EClass) elem;
				final CharSequence produced = ClassToJava.toText(cls);
				System.out.println(produced);
				final String filePath = Packages.getPackageSequence(cls.getEPackage()).stream()
						.map(p -> p.getName())
						.reduce((s1, s2) -> s1 + '/' + s2)
						.orElseThrow();
				final Path fileDir = Paths.get(packageRoot, filePath);
				final Path fileTotal = Paths.get(packageRoot, filePath, cls.getName() + ".java");
				try {
					Files.createDirectories(fileDir);
					Files.writeString(fileTotal, produced);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	
}
