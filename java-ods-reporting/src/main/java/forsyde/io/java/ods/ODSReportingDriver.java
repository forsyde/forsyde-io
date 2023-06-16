package forsyde.io.java.ods;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.drivers.ModelDriver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ODSReportingDriver implements ModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("report.ods");
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        return null;
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {

    }
}
