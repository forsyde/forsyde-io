package forsyde.io.java.libforsyde.behavior.sdf;

import forsyde.io.java.core.annotations.GenerateViewer;

import java.util.Map;

@GenerateViewer
public interface SDFActor {

    Map<String, Integer> consumption();

    Map<String, Integer> production();

}
