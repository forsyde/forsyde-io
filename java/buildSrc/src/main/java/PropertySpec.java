import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class PropertySpec {

    public String name;
    @JsonProperty("default")
    public Object defaultValue;
    public Object type;
}
