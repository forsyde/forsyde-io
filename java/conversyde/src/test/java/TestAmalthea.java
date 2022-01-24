import forsyde.io.java.drivers.ForSyDeModelHandler;
import io.github.forsyde.conversyde.ConverSyDeStandalone;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class TestAmalthea {

    @Test
    void tryConvert() throws Exception {
        final ConverSyDeStandalone converSyDeStandalone = new ConverSyDeStandalone();
        converSyDeStandalone.setInputFiles(List.of(new File("democar-extended.amxmi")));
        converSyDeStandalone.call();
    }
}
