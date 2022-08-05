package forsyde.io.java.sdf3.drivers;

import forsyde.io.java.sdf3.adapters.SDF3Adapter;
import forsyde.io.java.sdf3.adapters.mixins.elems.Sdf3;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelDriver;
import jakarta.xml.bind.JAXBContext;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ForSyDeSDF3Driver implements ForSyDeModelDriver {


    @Override
    public List<String> inputExtensions() {
        return List.of("sdf3.xml", "hsdf.xml", "sdf.xml");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of();
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        final SDF3Adapter sdf3Adapter = new SDF3Adapter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Sdf3.class);
        final Sdf3 sdf3 = (Sdf3) jaxbContext.createUnmarshaller().unmarshal(in);
        return sdf3Adapter.convert(sdf3);
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {

    }
}
