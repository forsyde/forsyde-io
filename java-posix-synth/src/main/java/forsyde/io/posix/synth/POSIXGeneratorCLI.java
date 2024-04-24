package forsyde.io.posix.synth;

import forsyde.io.core.ModelHandler;
import forsyde.io.core.SystemGraph;
import forsyde.io.lib.LibForSyDeModelHandler;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@CommandLine.Command(name = "fio2freertos", mixinStandardHelpOptions = true,
        description = "Generates a POSIX project from ForSyDe IO models.")
public class POSIXGeneratorCLI implements Callable<Integer> {

    @CommandLine.Parameters(index = "0..*", description = "The input ForSyDe IO models (files).")
    private List<File> modelFiles = new ArrayList<>();

    @CommandLine.Option(names = {"-o", "--output"}, description = "The base path used as the root for the generation. Default is the current directory.")
    private Path outputRoot = Path.of(".");

    @Override
    public Integer call() throws Exception {
        var modelHandler = LibForSyDeModelHandler.registerLibForSyDe(new ModelHandler());
        var merged = new SystemGraph();
        for (var in : modelFiles) {
            if (modelHandler.canLoadModel(in.toPath())) {
                merged.mergeInPlace(modelHandler.loadModel(in));
            }
        }
        // look for FPS runtimes
        var runtimes = merged.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.FixedPriorityScheduledRuntime.tryView(merged, v).stream())
                .collect(Collectors.toSet());
//        for (var runtime : runtimes) {
//            var freeRTOSMain = new FreeRTOSMain(runtime);
//            var files = freeRTOSMain.getPathsAndContent();
//            for (var path: files.keySet()) {
//                var content = files.get(path);
//                Files.writeString(outputRoot.resolve(path), content);
//            }
//        }
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new POSIXGeneratorCLI()).execute(args);
        System.exit(exitCode);
    }
}
