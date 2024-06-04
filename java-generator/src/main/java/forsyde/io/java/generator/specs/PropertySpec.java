package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonSerialize
public class PropertySpec {

    public String name;
    // @JsonProperty("default")
    @JsonAlias("html_description")
    public String htmlDescription = "";

    @JsonAlias("initialization_code")
    public Map<String, String> initializationCode = new HashMap<>();
    @JsonAlias("default_value")
    public Optional<Object> defaultValue = Optional.empty();
    @JsonAlias("property_type")
    public PropertyTypeSpec propertyType;

}
