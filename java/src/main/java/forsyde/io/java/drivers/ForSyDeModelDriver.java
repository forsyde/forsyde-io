/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import forsyde.io.java.core.ForSyDeModel;

/**
 * @author rjordao
 *
 */
public abstract class ForSyDeModelDriver {
	
	public ForSyDeModel loadModel(String filePath) throws Exception {
		return loadModel(Files.newInputStream(Paths.get(filePath)));
	}

	public ForSyDeModel loadModel(File file) throws Exception {
		return loadModel(new FileInputStream(file));
	}
	
	abstract public ForSyDeModel loadModel(InputStream in) throws Exception;
	
	public void writeModel(ForSyDeModel model, String filePath) throws Exception {
		writeModel(model, Files.newOutputStream(Paths.get(filePath)));
	}

	public void writeModel(ForSyDeModel model, File file) throws Exception {
		writeModel(model, new FileOutputStream(file));
	}
	
	abstract public void writeModel(ForSyDeModel model, OutputStream out) throws Exception;

}
