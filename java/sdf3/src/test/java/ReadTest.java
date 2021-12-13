import org.junit.Test;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelHandler;

public class ReadTest {

	@Test
	public void testReading() {
		ForSyDeSystemGraph model;
		ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();
		try {
			model = forSyDeModelHandler.loadModel("../examples/sobel2mpsoc.forxml");
			forSyDeModelHandler.writeModel(model, "sobel2mpsoc_out.forxml");
			forSyDeModelHandler.writeModel(model, "sobel2mpsoc_gm.graphml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
