import static org.junit.Assert.*;

import forsyde.io.epsilon.ForSyDeEspilonModel;
import org.eclipse.epsilon.eol.EolModule;
import org.junit.Test;

public class ReadingTest {

	static private String createString = "var v1 = new Vertex('hi'); var v2 = new Vertex('toto');";

	@Test
	public void test() throws Exception {
		ForSyDeEspilonModel forsyde = new ForSyDeEspilonModel();
		forsyde.setStoredOnDisposal(true);
		EolModule module = new EolModule();
		module.getContext().getModelRepository().addModel(forsyde);
		module.parse(createString);
		module.execute();
		forsyde.dispose();
	}

}
