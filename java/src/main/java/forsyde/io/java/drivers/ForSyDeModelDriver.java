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
import java.nio.file.Path;
import java.nio.file.Paths;

import forsyde.io.java.core.ForSyDeModel;

/**
 * @author rjordao
 *
 */
public interface ForSyDeModelDriver {
	
	default ForSyDeModel loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	default ForSyDeModel loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	default ForSyDeModel loadModel(Path inPath) throws Exception {
		return loadModel(Files.newInputStream(inPath));
	}
	
	ForSyDeModel loadModel(InputStream in) throws Exception;
	
	default void writeModel(ForSyDeModel model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	default void writeModel(ForSyDeModel model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	default void writeModel(ForSyDeModel model, Path outPath) throws Exception {
		writeModel(model, Files.newOutputStream(outPath));
	}
	
	void writeModel(ForSyDeModel model, OutputStream out) throws Exception;

}
