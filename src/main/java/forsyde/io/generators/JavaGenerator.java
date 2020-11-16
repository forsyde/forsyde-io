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

import forsyde.io.generators.java.EnumToJava;
import forsyde.io.generators.java.types.TypesFactoryGeneratorJava;
import forsyde.io.generators.java.types.TypesClassToJava;
import forsyde.io.generators.utils.Packages;

public class JavaGenerator extends TypesGenerator {
	
	public void generate(String typeSrc, String typeDst) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("ecore", new EcoreResourceFactoryImpl());
		
		Resource fecore = resourceSet.getResource(URI.createFileURI(typeSrc), true);
		
		EPackage forSyDeTypes = (EPackage) fecore.getContents().get(0);
		
		final String packageRoot = typeDst;
		
		// the main reason to use this sort of iteration instead of the 'forEach' is that I wanted
		// to add the throws declaration in the generate signature
		processPackage(forSyDeTypes, packageRoot);
		for (TreeIterator<EObject> iterator = forSyDeTypes.eAllContents(); iterator.hasNext();) {
			EObject elem = iterator.next();
			if (elem instanceof EClass) {
				processClass((EClass) elem, packageRoot);
			} else if (elem instanceof EEnum) {
				EEnum enu = (EEnum) elem;
				final CharSequence produced = EnumToJava.toText(enu);
				// System.out.println(produced);
				final String filePath = Packages.getPackageSequence(enu.getEPackage()).stream()
						.map(p -> p.getName().toLowerCase())
						.reduce((s1, s2) -> s1 + '/' + s2)
						.orElseThrow();
				final Path fileDir = Paths.get(packageRoot, filePath);
				final Path fileTotal = Paths.get(packageRoot, filePath, enu.getName() + ".java");
				Files.createDirectories(fileDir);
				Files.writeString(fileTotal, produced);
			} else if (elem instanceof EPackage) {
				processPackage((EPackage) elem, packageRoot);
			}
		}
	}
	
	private void processPackage(EPackage pak, String packageRoot) throws IOException {
		final CharSequence produced = TypesFactoryGeneratorJava.toText(pak);
		String filePathParent = Packages.getPackageSequence(pak).stream()
				.map(p -> p.getName().toLowerCase())
				.reduce((s1, s2) -> s1 + '/' + s2)
				.orElseThrow();
		Path fileDir;
		Path fileTotal;
		fileDir = Paths.get(packageRoot, filePathParent);
		fileTotal = Paths.get(packageRoot, filePathParent, pak.getName() + "Factory.java");
		Files.createDirectories(fileDir);
		Files.writeString(fileTotal, produced);
	}
	
	private void processClass(EClass cls, String packageRoot) throws IOException {
		final CharSequence produced = TypesClassToJava.toText(cls);
		// System.out.println(produced);
		final String filePath = Packages.getPackageSequence(cls.getEPackage()).stream()
				.map(p -> p.getName().toLowerCase())
				.reduce((s1, s2) -> s1 + '/' + s2)
				.orElseThrow();
		final Path fileDir = Paths.get(packageRoot, filePath);
		final Path fileTotal = Paths.get(packageRoot, filePath, cls.getName() + ".java");
		Files.createDirectories(fileDir);
		Files.writeString(fileTotal, produced);
	}
}
