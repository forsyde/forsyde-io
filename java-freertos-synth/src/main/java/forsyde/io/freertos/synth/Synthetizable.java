package forsyde.io.freertos.synth;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public interface Synthetizable {

    default Map<Path, String> getPathsAndContent() {
        return new HashMap<>();
    }
}
