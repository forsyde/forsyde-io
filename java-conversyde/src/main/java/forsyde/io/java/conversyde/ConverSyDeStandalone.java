package forsyde.io.java.conversyde;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.amalthea.drivers.ForSyDeAmaltheaDriver;
//import forsyde.io.java.drivers.ForSyDeLFDriver;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import forsyde.io.java.sdf3.drivers.ForSyDeSDF3Driver;
import forsyde.io.java.graphviz.drivers.ForSyDeGraphVizDriver;
import forsyde.io.java.kgt.drivers.ForSyDeKGTDriver;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "conversyde", mixinStandardHelpOptions = true,
        description = "Perform conversions in the standard ForSyDe IO library.")
public class ConverSyDeStandalone implements Callable<Integer> {

    public ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();

    @CommandLine.Parameters(description = "Input model files to be converted.")
    private List<File> inputFiles = new ArrayList<>();

    @CommandLine.Option(names = {"-o", "--output"}, description = "Output models of the conversion.")
    private List<File> outputFiles = new ArrayList<>();

    public ConverSyDeStandalone() {
        // register additional drivers that do not come with the default model handler.
        forSyDeModelHandler.registerDriver(new ForSyDeAmaltheaDriver());
        //forSyDeModelHandler.registerDriver(new ForSyDeLFDriver());
        forSyDeModelHandler.registerDriver(new ForSyDeSDF3Driver());
        // put it at high priority to override the core graphviz driver
        forSyDeModelHandler.registerDriver(new ForSyDeGraphVizDriver(), 0);
        forSyDeModelHandler.registerDriver(new ForSyDeKGTDriver());
    }

    @Override
    public Integer call() throws Exception {
        if (getInputFiles() != null && getOutputFiles().size() > 0) {
            final List<Path> filteredInput = new ArrayList<>(inputFiles.size());
            final List<ForSyDeSystemGraph> models = new ArrayList<>(inputFiles.size());
            for (final File input : inputFiles) {
                final Path inputPath = input.toPath();
                if (forSyDeModelHandler.canLoadModel(inputPath)) {
                    filteredInput.add(inputPath);
                } else {
                    System.out.println("Input model " + inputPath.getFileName() + " has no available converter.");
                    return 128;
                }
            }
            // could do a streaming version because it complained on exceptions.
            for (final Path inputModel : filteredInput) {
                models.add(forSyDeModelHandler.loadModel(inputModel));
            }
            final ForSyDeSystemGraph merged = models.stream()
                    .reduce(new ForSyDeSystemGraph(), (a, b) -> {
                        a.mergeInPlace(b);
                        return a;
                    });
            // finally do the writing
            if (outputFiles.size() == 0) outputFiles.add(new File("converted-model.forsyde.xmi"));
            for (File output : outputFiles) {
                forSyDeModelHandler.writeModel(merged, output);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ConverSyDeStandalone()).execute(args);
        System.exit(exitCode);
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public List<File> getOutputFiles() {
        return outputFiles;
    }

    public void setOutputFiles(List<File> outputFiles) {
        this.outputFiles = outputFiles;
    }


}
