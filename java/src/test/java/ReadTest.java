import org.junit.Test;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.drivers.ForSyDeModelHandler;

public class ReadTest {

	@Test
	public void testReading() {
		ForSyDeModel model;
		try {
			model = ForSyDeModelHandler.loadModel("../examples/sobel2mpsoc.forxml");
			ForSyDeModelHandler.writeModel(model, "sobel2mpsoc_out.forxml");
			ForSyDeModelHandler.writeModel(model, "sobel2mpsoc_gm.graphml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
