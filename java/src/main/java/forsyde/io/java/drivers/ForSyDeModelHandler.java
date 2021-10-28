/**
 * 
 */
package forsyde.io.java.drivers;

import java.io.File;
import java.nio.file.*;

import forsyde.io.java.core.ForSyDeModel;

/**
 * @author rjordao
 *
 */
public final class ForSyDeModelHandler {

	PathMatcher forsydeMLMatcher;
	PathMatcher graphMLMatcher;
	PathMatcher dotMatcher;
	PathMatcher linguaFrancaMatcher;
	PathMatcher amaltheaMatcher;

	public ForSyDeModelHandler() {
		forsydeMLMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{forxml,forsyde.xml,xml}");
		graphMLMatcher = FileSystems.getDefault().getPathMatcher("glob:**.graphml");
		dotMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{dot,gv,graphviz}");
		linguaFrancaMatcher = FileSystems.getDefault().getPathMatcher("glob:**.lf");
		amaltheaMatcher = FileSystems.getDefault().getPathMatcher("glob:**.amxmi");
	}

	public boolean canLoadModel(Path path) {
	    return forsydeMLMatcher.matches(path) ||
				graphMLMatcher.matches(path) ||
				dotMatcher.matches(path) ||
				linguaFrancaMatcher.matches(path) ||
				amaltheaMatcher.matches(path);
	}

	public ForSyDeModel loadModel(String filePath) throws Exception {
		return loadModel(Paths.get(filePath));
	}

	public ForSyDeModel loadModel(File file) throws Exception {
		return loadModel(file.toPath());
	}

	public ForSyDeModel loadModel(Path inPath) throws Exception {
		ForSyDeModelDriver driver;
		if (forsydeMLMatcher.matches(inPath)) {
			driver = new ForSyDeMLDriver();
		} else if(linguaFrancaMatcher.matches(inPath)) {
			driver = new ForSyDeLFDriver();
		} else if(amaltheaMatcher.matches(inPath)) {
			driver = new ForSyDeAmaltheaDriver();
		} else {
			throw new Exception("Supported read formats: ['forxml', 'forsyde.xml', 'lf'].");
		}
		return driver.loadModel(inPath);
	}
	
	public void writeModel(ForSyDeModel model, String filePath) throws Exception {
		writeModel(model, Paths.get(filePath));
	}

	public void writeModel(ForSyDeModel model, File file) throws Exception {
		writeModel(model, file.toPath());
	}

	public void writeModel(ForSyDeModel model, Path outPath) throws Exception {
		ForSyDeModelDriver driver;
		if (forsydeMLMatcher.matches(outPath)) {
			driver = new ForSyDeMLDriver();
		} else if(graphMLMatcher.matches(outPath)) {
			driver = new ForSyDeGraphMLDriver();
		}  else if(dotMatcher.matches(outPath)) {
			driver = new ForSyDeDOTDriver();
		} else {
			throw new Exception("Supported write formats: ['forxml', 'forsyde.xml', 'graphml', 'dot', 'gv', 'graphviz'].");
		}
		if (outPath.getParent() != null)
			Files.createDirectories(outPath.getParent());
		try {
			Files.createFile(outPath);
		} catch (FileAlreadyExistsException ignored) {}
		driver.writeModel(model, outPath);
	}
}
