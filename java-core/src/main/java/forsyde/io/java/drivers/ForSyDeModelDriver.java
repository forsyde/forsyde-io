/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import forsyde.io.java.core.ForSyDeSystemGraph;

/**
 * Interface for reading and writing different formats from and to ForSyDe IO.
 * The strings are always assuemd to be UTF8 unless otherwise noted. The loading mechanisms
 * might crash for some special loading cases if the encoding is different!!
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

	default ForSyDeSystemGraph loadModel(Reader inReader) throws Exception {
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
