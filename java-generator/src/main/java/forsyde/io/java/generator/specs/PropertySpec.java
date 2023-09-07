package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonSerialize
public class PropertySpec {

    public String name;
    // @JsonProperty("default")
    @JsonAlias("initialization_code")
    public Map<String, String> initializationCode = new HashMap<>();
    @JsonAlias("default_value")
    public Optional<Object> defaultValue = Optional.empty();
    public PropertyTypeSpec type;

}
