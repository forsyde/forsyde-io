package forsyde.io.bridge.forsyde.systemc;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.drivers.ModelDriver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ForSyDeSystemCDriver implements ModelDriver, ForSyDeSystemCAdapter {

    @Override
    public List<String> inputExtensions() {
        return List.of("cpp", "hpp", "cc", "hh");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of();
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        var code = new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n"));
        return inspectSourceCode(code);
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {

    }

    @Override
    public String printModel(SystemGraph model) throws Exception {
        return null;
    }
}
