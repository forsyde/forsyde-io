/**
 * 
 */
package forsyde.io.java.drivers;

import forsyde.io.java.core.ForSyDeModel;

/**
 * @author rjordao
 *
 */
public final class ForSyDeModelHandler {

	@SuppressWarnings("deprecation")
	public static ForSyDeModel loadModel(String filePath) throws Exception {
		ForSyDeModelDriver driver;
		if (filePath.endsWith(".forxml")) {
			driver = new ForSyDeMLDriver();
		} else {
			throw new Exception("Only '.xml' and '.forxml' formats are supported.");
		}
		return driver.loadModel(filePath);
	}

	@SuppressWarnings("deprecation")
	public static void writeModel(ForSyDeModel model, String filePath) throws Exception {
		ForSyDeModelDriver driver;
		if (filePath.endsWith(".forxml")) {
			driver = new ForSyDeMLDriver();
		} else if (filePath.endsWith(".graphml")) {
			driver = new ForSyDeGraphMLDriver();
		} else {
			throw new Exception("Supported write formats: ['forxml', 'graphml'].");
		}
		driver.writeModel(model, filePath);
	}
}
