package specs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

public class PropertyTypeSpec {
    public PropertyTypeEnum name;
    public Optional<PropertyTypeSpec> valueType = Optional.empty();

}
