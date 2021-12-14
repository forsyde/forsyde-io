/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import forsyde.io.java.core.ForSyDeSystemGraph;

/**
 * @author rjordao
 *
 */
public interface ForSyDeModelDriver {

	List<String> inputExtensions();

	List<String> outputExtensions();
	
	default ForSyDeSystemGraph loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	default ForSyDeSystemGraph loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	default ForSyDeSystemGraph loadModel(Path inPath) throws Exception {
		return loadModel(Files.newInputStream(inPath));
	}
	
	ForSyDeSystemGraph loadModel(InputStream in) throws Exception;
	
	default void writeModel(ForSyDeSystemGraph model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	default void writeModel(ForSyDeSystemGraph model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	default void writeModel(ForSyDeSystemGraph model, Path outPath) throws Exception {
		writeModel(model, Files.newOutputStream(outPath, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE));
	}
	
	void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception;

}
