package specs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class PropertySpec {

    public String name;
    @JsonProperty("default")
    public Object defaultValue;
    public PropertyTypeSpec type;
    public Optional<String> comment = Optional.empty();
}
