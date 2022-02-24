package forsyde.io.java.ods;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelDriver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ForSyDeODSReportingDriver implements ForSyDeModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of();
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("report.ods");
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        return null;
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {

    }
}
