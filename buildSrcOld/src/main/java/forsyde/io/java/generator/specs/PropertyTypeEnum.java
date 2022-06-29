package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PropertyTypeEnum {
    BOOLEAN,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    ARRAY,
    STRINGMAP,
    INTMAP,
    STRING;

    @JsonValue
    public String getRepresentation() {
        switch (this) {
            case BOOLEAN: return "boolean";
            case INTEGER: return "integer";
            case LONG: return "long";
            case FLOAT: return "float";
            case DOUBLE: return "double";
            case ARRAY: return "array";
            case STRINGMAP: return "stringDict";
            case INTMAP: return "intDict";
            default: return "string";
        }
    }

    @JsonCreator
    static public PropertyTypeEnum fromString(String rep) {
        switch (rep) {
            case "bool":
            case "boolean":
                return BOOLEAN;
            case "int":
            case "integer":
                return INTEGER;
            case "long":
                return LONG;
            case "double":
                return DOUBLE;
            case "float":
                return FLOAT;
            case "array":
                return ARRAY;
            case "intmap":
            case "integermap":
            case "intMap":
            case "integerMap":
                return INTMAP;
            case "strmap":
            case "stringmap":
            case "strMap":
            case "stringMap":
                return STRINGMAP;
            default:
                return STRING;
        }
    }
}
