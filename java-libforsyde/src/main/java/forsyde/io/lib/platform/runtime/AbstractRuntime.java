package forsyde.io.lib.platform.runtime;


import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.platform.hardware.GenericProcessingModule;

import java.util.Optional;
import java.util.Set;

/**
 * An AbstractRuntime element can be anything that is acting as a "middleware" for the platform and therefore
 * should be refined to be better useful.
 *
 * In any case, a runtime must have a "host" which executes its instructions and also processing elements
 * which it manages, which means that they are part of the runtime's controlled processing elements.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface AbstractRuntime extends VertexViewer {

    @OutPort
    GenericProcessingModule host();

    @OutPort
    Set<GenericProcessingModule> managed();
}
