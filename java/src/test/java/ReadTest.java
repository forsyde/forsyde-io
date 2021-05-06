import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.xml.sax.SAXException;

import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import forsyde.io.java.drivers.ForSyDeXMLDriver;

public class ReadTest {
	
	@Test
	public void testReading() {
		ForSyDeModel model;
		try {
			model = ForSyDeModelHandler.loadModel("../examples/sobel2mpsoc.forxml");
			System.out.println(model);
			ForSyDeModelHandler.writeModel(model, "sobel2mpsoc_out.forxml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
