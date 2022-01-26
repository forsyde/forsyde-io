import io.github.forsyde.conversyde.ConverSyDeStandalone;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class TestConversion {

    @Test
    void tryConvert() throws Exception {
        final ConverSyDeStandalone converSyDeStandalone = new ConverSyDeStandalone();
        converSyDeStandalone.setInputFiles(List.of(
                //new File("democar-extended.amxmi"),
                //new File("h263decoder.sdf3.xml")
                new File("systemmodel.amxmi")
        ));
        converSyDeStandalone.call();
    }
}
