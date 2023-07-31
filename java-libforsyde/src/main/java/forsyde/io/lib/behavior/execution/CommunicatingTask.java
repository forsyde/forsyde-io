package forsyde.io.lib.behavior.execution;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Map;

@RegisterTrait(IForSyDeHierarchy.class)
public interface CommunicatingTask extends Task {

    @Property
    Map<String, Long> portDataReadSize();

    @Property
    Map<String, Long> portDataWrittenSize();
}
