package forsyde.io.generators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import forsyde.io.generators.java.ClassToJava;
import forsyde.io.generators.java.ClassXMISerializerToJava;
import forsyde.io.generators.java.EnumToJava;
import forsyde.io.generators.utils.Packages;

public class JavaGenerator {
	
	public void generate() throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI("model/forsyde.ecore"), true);
		
		EPackage ForSyDe = (EPackage) fecore.getContents().get(0);
		
		final String packageRoot = "java-io/src/main/java";
		String ioPath = null;
		
		// the main reason to use this sort of iteration instead of the 'forEach' is that I wanted
		// to add the throws declaration in the generate signature
		for (TreeIterator<EObject> iterator = ForSyDe.eAllContents(); iterator.hasNext();) {
			EObject elem = iterator.next();
			if (elem instanceof EClass) {
				EClass cls = (EClass) elem;
				final CharSequence produced = ClassToJava.toText(cls);
				// System.out.println(produced);
				final String filePath = Packages.getPackageSequence(cls.getEPackage()).stream()
						.map(p -> p.getName())
						.reduce((s1, s2) -> s1 + '/' + s2)
						.orElseThrow();
				final Path fileDir = Paths.get(packageRoot, filePath);
				final Path fileTotal = Paths.get(packageRoot, filePath, cls.getName() + ".java");
				if (cls.getName().equals("ForSyDeIO")) {
					ioPath = filePath;
				}
				Files.createDirectories(fileDir);
				Files.writeString(fileTotal, produced);
			} else if (elem instanceof EEnum) {
				EEnum enu = (EEnum) elem;
				final CharSequence produced = EnumToJava.toText(enu);
				// System.out.println(produced);
				final String filePath = Packages.getPackageSequence(enu.getEPackage()).stream()
						.map(p -> p.getName())
						.reduce((s1, s2) -> s1 + '/' + s2)
						.orElseThrow();
				final Path fileDir = Paths.get(packageRoot, filePath);
				final Path fileTotal = Paths.get(packageRoot, filePath, enu.getName() + ".java");
				
				Files.writeString(fileTotal, produced);
			}
		}
		// add the XMI serializer and deserializer, should go in the same pacakge as ForSyDeIO
		final Path ioTotal = Paths.get(packageRoot, ioPath, "ForSyDeIOXMIDriver.java");
		final CharSequence produced = ClassXMISerializerToJava.toText(ForSyDe);
		Files.createDirectories(Paths.get(packageRoot, ioPath));
		Files.writeString(ioTotal, produced);
		
	}
	
}
