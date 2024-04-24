/**
 * 
 */
package forsyde.io.core.drivers;

import forsyde.io.core.SystemGraph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Interface for reading and writing different formats from and to models.
 * The strings are always assumed to be UTF8 unless otherwise noted. The loading mechanisms
 * might crash for some special loading cases if the encoding is different!
 * @author rjordao
 *
 */
public interface ModelDriver {

	List<String> inputExtensions();

	List<String> outputExtensions();
	
	default SystemGraph loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	default SystemGraph loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	default SystemGraph loadModel(Path inPath) throws Exception {
		return loadModel(Files.newInputStream(inPath));
	}

	default SystemGraph loadModel(Reader inReader) throws Exception {
		char[] charBuffer = new char[8 * 1024];
		final StringBuilder stringBuilder = new StringBuilder();
		int numCharsRead;
		while ((numCharsRead = inReader.read(charBuffer, 0, charBuffer.length)) != -1) {
			stringBuilder.append(charBuffer, 0, numCharsRead);
		}
		try(final InputStream inputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes(StandardCharsets.UTF_8))) {
			return loadModel(inputStream);
		}
	}
	
	SystemGraph loadModel(InputStream in) throws Exception;

	default SystemGraph readModel(String text) throws Exception {
		final StringReader rs = new StringReader(text);
		final SystemGraph model = loadModel(rs);
		return model;
	}
	
	default void writeModel(SystemGraph model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	default void writeModel(SystemGraph model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	default void writeModel(SystemGraph model, Path outPath) throws Exception {
		writeModel(model, Files.newOutputStream(outPath, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE));
	}
	
	void writeModel(SystemGraph model, OutputStream out) throws Exception;

	String printModel(SystemGraph model) throws Exception;

}
