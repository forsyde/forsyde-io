package forsyde.io.java.conversyde;

import forsyde.io.core.SystemGraph;
import forsyde.io.java.amalthea.drivers.AmaltheaDriver;
//import forsyde.io.java.drivers.ForSyDeLFDriver;
import forsyde.io.core.drivers.ModelHandler;
import forsyde.io.bridge.sdf3.drivers.SDF3Driver;
import forsyde.io.visual.graphviz.GraphVizDriver;
import forsyde.io.visual.kgt.drivers.KGTDriver;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "conversyde", mixinStandardHelpOptions = true,
        description = "Perform conversions in the standard ForSyDe IO library.")
public class ConverSyDeStandalone implements Callable<Integer> {

    public ModelHandler modelHandler = new ModelHandler();

    @CommandLine.Parameters(description = "Input model files to be converted.")
    private List<File> inputFiles = new ArrayList<>();

    @CommandLine.Option(names = {"-o", "--output"}, description = "Output models of the conversion.")
    private List<File> outputFiles = new ArrayList<>();

    public ConverSyDeStandalone() {
        // register additional drivers that do not come with the default model handler.
        modelHandler.registerDriver(new AmaltheaDriver());
        //forSyDeModelHandler.registerDriver(new ForSyDeLFDriver());
        modelHandler.registerDriver(new SDF3Driver());
        // put it at high priority to override the core graphviz driver
        modelHandler.registerDriver(new GraphVizDriver(), 0);
        modelHandler.registerDriver(new KGTDriver());
    }

    @Override
    public Integer call() throws Exception {
        if (getInputFiles() != null && getOutputFiles().size() > 0) {
            final List<Path> filteredInput = new ArrayList<>(inputFiles.size());
            final List<SystemGraph> models = new ArrayList<>(inputFiles.size());
            for (final File input : inputFiles) {
                final Path inputPath = input.toPath();
                if (modelHandler.canLoadModel(inputPath)) {
                    filteredInput.add(inputPath);
                } else {
                    System.out.println("Input model " + inputPath.getFileName() + " has no available converter.");
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
            if (outputFiles.size() == 0) outputFiles.add(new File("converted-model.forsyde.xmi"));
            for (File output : outputFiles) {
                modelHandler.writeModel(merged, output);
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
