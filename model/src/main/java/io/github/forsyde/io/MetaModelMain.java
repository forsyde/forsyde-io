package io.github.forsyde.io;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.Injector;

import io.github.forsyde.io.MetaStandaloneSetup;
import io.github.forsyde.io.generator.JavaMetaGenerator;
import io.github.forsyde.io.meta.Model;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "forsyde-io-meta-gen", mixinStandardHelpOptions = true, version = "FIO generator 1.0", description = "Generates typed helper for ForSyDe IO from a description file.")
public class MetaModelMain implements Callable<Integer> {

	@Option(names = { "-i" }, description = "Input file in the description language")
	private File inputFile = new File("model.forsydemeta");

	@Option(names = { "-o" }, description = "Output folder for generation")
	private Path outputFolder = Paths.get("..");

	@Option(names = { "-l" }, description = "Specific language to generate")
	private String targetLanguage = "";

	public static void main(String[] args) {
		int exitCode = new CommandLine(new MetaModelMain()).execute(args);
		System.exit(exitCode);
	}

	@Inject
	JavaMetaGenerator javaMetaGenerator;

	@Override
	public Integer call() throws Exception {
		Injector injector = new MetaStandaloneSetup().createInjectorAndDoEMFRegistration();
		injector.injectMembers(this);
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		// this strange line is the only things that worked on windows. Go figure.
		Resource resource = resourceSet.getResource(URI.createURI(inputFile.toURI().toString()), true);
		// resource.load(new FileInputStream(inputFile), resourceSet.getLoadOptions());
		// IParseResult result = parser.parse(Files.newReader(inputFile,
		// StandardCharsets.UTF_8));
		final Model root = (Model) resource.getContents().get(0);
		if (targetLanguage.isEmpty() || targetLanguage.equals("all")) {
			javaMetaGenerator.doGenerate(root, outputFolder.resolve("java"));
		} else if (targetLanguage.equals("java")) {
			javaMetaGenerator.doGenerate(root, outputFolder);
		}
		return 0;
	}

}
