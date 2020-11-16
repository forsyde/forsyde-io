package forsyde.io.generators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.eclipse.emf.ecore.EPackage;

import forsyde.io.generators.java.types.TypesFactoryGeneratorJava;
import forsyde.io.generators.utils.Packages;

public abstract class TypesGenerator {
	
	public abstract void generate (
		String typeSrc,
		String typeDst
	) throws IOException;
		
	protected void processPackage(EPackage pak, String packageRoot, String prefix, String suffix, Boolean lowerCase) throws IOException {
		final CharSequence produced = TypesFactoryGeneratorJava.toText(pak);
		String filePathParent = Packages.getPackageSequence(pak).stream()
				.map(p -> lowerCase ? p.getName().toLowerCase() : p.getName())
				.reduce((s1, s2) -> s1 + '/' + s2)
				.orElseThrow();
		Path fileDir;
		Path fileTotal;
		fileDir = Paths.get(packageRoot, filePathParent);
		fileTotal = Paths.get(packageRoot, filePathParent, prefix + pak.getName() + suffix);
		Files.createDirectories(fileDir);
		Files.writeString(fileTotal, produced);
	}

}
