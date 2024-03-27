package forsyde.io.bridge.sdf3;

import forsyde.io.bridge.sdf3.drivers.SDF3Driver;
import forsyde.io.core.ModelHandler;
import forsyde.io.core.SystemGraph;
import forsyde.io.lib.LibForSyDeModelHandler;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "sdf3_bridge_fio", mixinStandardHelpOptions = true, version = "0.7.20",
        description = "Perform conversions from SDF3 to FioDL and LibForSyDe.")
public final class SDF3Converter implements Callable<Integer> {

    public ModelHandler modelHandler = new ModelHandler();

    @CommandLine.Parameters(description = "Input model files to be converted.")
    public List<File> inputFiles = new ArrayList<>();

    @CommandLine.Option(names = {"-o", "--output"}, description = "Merged converted models to output. All targets are copies with different names. Default is \"from_sdf3.fiodl\"")
    public List<File> outputFiles = new ArrayList<>();

    public SDF3Converter() {
//        modelHandler.registerDriver(new AmaltheaDriver());
        LibForSyDeModelHandler.registerLibForSyDe(modelHandler);

        modelHandler.registerDriver(new SDF3Driver());

        if (outputFiles.isEmpty()) {
            outputFiles.add(new File("from_sdf3.fiodl"));
        }
    }

    @Override
    public Integer call() throws Exception {
        if (inputFiles != null && !outputFiles.isEmpty()) {
            final List<Path> filteredInput = new ArrayList<>(inputFiles.size());
            final List<SystemGraph> models = new ArrayList<>(inputFiles.size());
            for (final File input : inputFiles) {
                final Path inputPath = input.toPath();
                if (modelHandler.canLoadModel(inputPath)) {
                    filteredInput.add(inputPath);
                } else {
                    System.out.println("Input model " + inputPath.getFileName() + " cannot be converted.");
                    return 128;
                }
            }
            // could do a streaming version because it complained on exceptions.
            for (final Path inputModel : filteredInput) {
                models.add(modelHandler.loadModel(inputModel));
            }
            final SystemGraph merged = models.stream()
                    .reduce(new SystemGraph(), (a, b) -> {
                        a.mergeInPlace(b);
                        return a;
                    });
            // finally do the writing
            for (File output : outputFiles) {
                modelHandler.writeModel(merged, output);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SDF3Converter()).execute(args);
        System.exit(exitCode);
    }
}
