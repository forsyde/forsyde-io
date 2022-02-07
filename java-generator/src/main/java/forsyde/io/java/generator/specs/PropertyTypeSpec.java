package forsyde.io.java.generator.specs;

import java.util.Optional;

public class PropertyTypeSpec {
    public PropertyTypeEnum name;
    public Optional<PropertyTypeSpec> valueType = Optional.empty();

}
