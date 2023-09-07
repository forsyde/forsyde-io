package forsyde.io.frontsyde;

import forsyde.io.core.ModelHandler;
import forsyde.io.lib.TraitNamesFrom0_6To0_7;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.visual.kgt.drivers.KGTDriver;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "frontsyde", mixinStandardHelpOptions = true,
        description = "Front-end CLI for ForSyDe IO.")
public class FrontSyDeCLI implements Callable<Integer> {

    public ModelHandler modelHandler = new ModelHandler()
            .registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7())
            .registerTraitHierarchy(new ForSyDeHierarchy())
            .registerDriver(new KGTDriver());

    @CommandLine.Parameters(description = "Input model files to be parsed and processes.")
    private List<File> inputFiles = new ArrayList<>();

    @CommandLine.Option(names = {"-o", "--output"}, description = "named output models of the process.")
    private List<File> outputFiles = new ArrayList<>();

    @Override
    public Integer call() throws Exception {
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FrontSyDeCLI()).execute(args);
        System.exit(exitCode);
    }
}
