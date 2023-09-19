package forsyde.io.posix.synth;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public interface Synthetizable {

    default Map<Path, String> getPathsAndContent() {
        return new HashMap<>();
    }
}
