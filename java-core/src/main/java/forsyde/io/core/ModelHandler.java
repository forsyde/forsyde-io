/**
 * 
 */
package forsyde.io.core;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import forsyde.io.core.drivers.FiodlDriver;
import forsyde.io.core.drivers.ModelDriver;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.core.migrations.*;
import forsyde.io.core.validation.BasicTraitsValidation;
import forsyde.io.core.validation.SystemGraphValidation;

/**
 * @author rjordao
 *
 */
public final class ModelHandler {

	private final List<SystemGraphMigrator> registeredMigrators = new ArrayList<>();
	private final List<SystemGraphValidation> registesteredValidators = new ArrayList<>();
	private final List<SystemGraphInference> registeredInferences = new ArrayList<>();
	private final List<ModelDriver> registeredDrivers = new ArrayList<>();
	private final List<PathMatcher> registeredDriversInputMatchers = new ArrayList<>();
	private final List<PathMatcher> registeredDriversOutputMatchers = new ArrayList<>();

	private final Set<TraitHierarchy> registeredTraitHierarchies = new HashSet<>();
//	PathMatcher forsydeMLMatcher;
//	PathMatcher graphMLMatcher;
//	PathMatcher dotMatcher;
//	PathMatcher linguaFrancaMatcher;
//	PathMatcher amaltheaMatcher;

	public ModelHandler() {
		// mandatory validator
		registerValidation(new BasicTraitsValidation());
		// mandatory drivers
		registerDriver(new FiodlDriver());
	}

//	public ModelHandler(ModelDriver... extraDrivers) {
//		// mandatory validator
//		registesteredValidators.add(new BasicTraitsValidation());
//		// mandatory drivers
//		registeredDrivers.add(new FiodlDriver());
//		// register default inferences
////		registeredInferences.add(new PopulateTaskCommunications());
//		// register default migrators
////		registeredMigrators.add(new NoMoreReactiveTaskMigration());
////        registeredMigrators.add(new TaskCallSequenceSplit());
////		registeredMigrators.add(new SDFCombToSDFActorConversion());
////		registeredMigrators.add(new ParallelSkeletonsNameMigrator());
////		registeredMigrators.add(new MadeMocAndParallelPortsMultipleMigrator());
//		// default validators
////		registesteredValidators.add(new SDFValidator());
//		// register default drivers
//
//		// register extra drivers
//		registeredDrivers.addAll(Arrays.asList(extraDrivers));
//		// make their
//		for (ModelDriver driver : registeredDrivers) {
//			final String inExtensions = "{" + String.join(",", driver.inputExtensions()) + "}";
//			final String outExtensions = "{" + String.join(",", driver.outputExtensions()) + "}";
//			registeredDriversInputMatchers.add(
//					FileSystems.getDefault().getPathMatcher("glob:**." + inExtensions)
//			);
//			registeredDriversOutputMatchers.add(
//					FileSystems.getDefault().getPathMatcher("glob:**." + outExtensions)
//			);
//		}
//	}

	public ModelHandler registerValidation(SystemGraphValidation validation) {
		registesteredValidators.add(validation);
		return this;
	}

	public ModelHandler registerInference(SystemGraphInference inference) {
		registeredInferences.add(inference);
		return this;
	}

	public ModelHandler registerSystemGraphMigrator(SystemGraphMigrator systemGraphMigrator) {
		registeredMigrators.add(systemGraphMigrator);
		return this;
	}

	public ModelHandler registerSystemGraphMigrator(SystemGraphMigrator systemGraphMigrator, int applyOrder) {
		registeredMigrators.add(applyOrder, systemGraphMigrator);
		return this;
	}

	public ModelHandler registerDriver(ModelDriver extraDriver, int loadOrder) {
		if (!registeredDrivers.contains(extraDriver)) {
			final String inExtensions = "{" + String.join(",", extraDriver.inputExtensions()) + "}";
			final String outExtensions = "{" + String.join(",", extraDriver.outputExtensions()) + "}";
			registeredDrivers.add(Math.min(loadOrder, registeredDrivers.size()), extraDriver);
			registeredDriversInputMatchers.add(Math.min(loadOrder, registeredDrivers.size()),
					FileSystems.getDefault().getPathMatcher("glob:**." + inExtensions)
			);
			registeredDriversOutputMatchers.add(Math.min(loadOrder, registeredDrivers.size()),
					FileSystems.getDefault().getPathMatcher("glob:**." + outExtensions)
			);
		}
		return this;
	}

	public ModelHandler registerTraitHierarchy(TraitHierarchy traitHierarchy) {
		registeredTraitHierarchies.add(traitHierarchy);
		return this;
	}

	public ModelHandler registerDriver(ModelDriver extraDriver) {
		return registerDriver(extraDriver, registeredDrivers.size());
	}

	public boolean canLoadModel(Path path) {
		return registeredDriversInputMatchers.stream().anyMatch(d ->
				d.matches(path)
		);
	}

	public boolean canWriteModel(Path path) {
		return registeredDriversOutputMatchers.stream().anyMatch(d ->
				d.matches(path)
		);
	}

	/**
	 * This is a shortcut to loadModel(File) where the String is simply
	 * the stringfied path of the model file.
	 *
	 * @param filePath stringified path for the model file.
	 * @return the loaded model, if was possible to load.
	 * @throws Exception
	 */
	public SystemGraph loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	public SystemGraph loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	public SystemGraph loadModel(Reader inReader, String textualFormat) throws Exception {
		char[] charBuffer = new char[8 * 1024];
		final StringBuilder stringBuilder = new StringBuilder();
		int numCharsRead;
		while ((numCharsRead = inReader.read(charBuffer, 0, charBuffer.length)) != -1) {
			stringBuilder.append(charBuffer, 0, numCharsRead);
		}
		try(final InputStream inputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes(StandardCharsets.UTF_8))) {
			return loadModel(inputStream, textualFormat);
		}
	}

	public SystemGraph loadModel(InputStream in, String textualFormat) throws Exception {
        for (ModelDriver registeredDriver : registeredDrivers) {
            if (registeredDriver.inputExtensions().contains(textualFormat)) {
                return loadModel(in, registeredDriver);
            }
        }
		throw new Exception("No driver succeeded in reading the input stream");
	}

	private SystemGraph loadModel(InputStream in, ModelDriver driver) throws Exception {
		final SystemGraph systemGraph = driver.loadModel(in);
		return standardSystemGraphProcessing(systemGraph);
	}

	public SystemGraph loadModel(Path inPath) throws Exception {
		for (int i = 0; i < registeredDrivers.size(); i++) {
			if (registeredDriversInputMatchers.get(i).matches(inPath)) {
				return loadModel(Files.newInputStream(inPath), registeredDrivers.get(i));
			}
		}
		throw new Exception("Unsupported read format for file: " + inPath.toString());
	}
	
	public void writeModel(SystemGraph model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	public void writeModel(SystemGraph model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	public SystemGraph readModel(String text, String textualFormat) throws Exception {
        for (ModelDriver registeredDriver : registeredDrivers) {
            if (registeredDriver.inputExtensions().contains(textualFormat)) {
                return standardSystemGraphProcessing(registeredDriver.readModel(text));
            }
        }
		throw new Exception("Unsupported read format: " + textualFormat);
	}

	public void writeModel(SystemGraph model, Path outPath) throws Exception {
		registeredInferences.forEach(inference -> inference.infer(model));
		for (SystemGraphValidation validation : registesteredValidators) {
			final Optional<String> validationResult = validation.validate(model);
			if (validationResult.isPresent()) {
				throw new Exception(validationResult.get());
			}
		}
		for (int i = 0; i < registeredDrivers.size(); i++) {
			if (registeredDriversOutputMatchers.get(i).matches(outPath)) {
				registeredDrivers.get(i).writeModel(model, outPath);
				return;
			}
		}
		throw new Exception("Unsupported write format for file: " + outPath.toString());
	}

	public void writeModel(SystemGraph model, OutputStream out, String textualFormat) throws Exception {
		registeredInferences.forEach(inference -> inference.infer(model));
		for (SystemGraphValidation validation : registesteredValidators) {
			final Optional<String> validationResult = validation.validate(model);
			if (validationResult.isPresent()) {
				throw new Exception(validationResult.get());
			}
		}
        for (ModelDriver registeredDriver : registeredDrivers) {
            if (registeredDriver.outputExtensions().contains(textualFormat)) {
                registeredDriver.writeModel(model, out);
                return;
            }
        }
	}

	public String printModel(SystemGraph model, String textualFormat) throws Exception {
		registeredInferences.forEach(inference -> inference.infer(model));
		for (SystemGraphValidation validation : registesteredValidators) {
			final Optional<String> validationResult = validation.validate(model);
			if (validationResult.isPresent()) {
				throw new Exception(validationResult.get());
			}
		}
        for (ModelDriver registeredDriver : registeredDrivers) {
            if (registeredDriver.outputExtensions().contains(textualFormat)) {
                return registeredDriver.printModel(model);
            }
        }
		throw new Exception("Unsupported format: " + textualFormat);
	}

	public SystemGraph standardSystemGraphProcessing(SystemGraph systemGraph) throws Exception {
		// migrate what possible
		for (SystemGraphMigrator systemGraphMigrator : registeredMigrators) {
			if (!systemGraphMigrator.effect(systemGraph)) {
				throw new SystemGraphMigrator.SystemGraphMigrationException("Migrator " + systemGraphMigrator.getName() + " has failed its migration.");
			}
		}
		// use trait hierarchies to remove opaque traits
		for (var v : systemGraph.vertexSet()) {
			var toDelete = new HashSet<Trait>();
			var toAdd = new HashSet<Trait>();
			for (var t : v.getTraits()) {
				if (t instanceof OpaqueTrait) {
					for (var traitHierarchies : registeredTraitHierarchies) {
						var newT = traitHierarchies.fromName(t.getName());
						if (!(newT instanceof OpaqueTrait)) {
							toDelete.add(t);
							toAdd.add(newT);
						}
					}
				}
			}
			v.getTraits().removeAll(toDelete);
			v.getTraits().addAll(toAdd);
		}
		// post migration validation
		for (SystemGraphValidation validation : registesteredValidators) {
			final Optional<String> validationResult = validation.validate(systemGraph);
			if (validationResult.isPresent()) {
				throw new SystemGraphValidation.InvalidSystemGraph(validationResult.get());
			}
		}
		registeredInferences.forEach(inference -> inference.infer(systemGraph));
		return systemGraph;
	}
}
