package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class PropertyTypeSpec {

    @JsonSerialize
    public static class IntegerPropertyType extends PropertyTypeSpec {
        public int bits = 32;
        public boolean unsigned = false;

        public IntegerPropertyType(int bits, boolean unsigned) {
            this.bits = bits;
            this.unsigned = unsigned;
        }
    }

    @JsonSerialize
    public static class RealPropertyType extends PropertyTypeSpec {
        public int bits = 32;

        public RealPropertyType(int bits) {
            this.bits = bits;
        }
    }

    @JsonSerialize
    public static class BooleanPropertyType extends PropertyTypeSpec {

    }

    @JsonSerialize
    public static class StringPropertyType extends PropertyTypeSpec {

    }

    @JsonSerialize
    public static class ArrayPropertyType extends PropertyTypeSpec {
        @JsonAlias("value_type")
        PropertyTypeSpec valueType;

        public ArrayPropertyType(PropertyTypeSpec valueType) {
            this.valueType = valueType;
        }
    }

    @JsonSerialize
    public static class MapPropertyType extends PropertyTypeSpec {
        @JsonAlias("key_type")
        PropertyTypeSpec keyType;
        @JsonAlias("value_type")
        PropertyTypeSpec valueType;

        public MapPropertyType(PropertyTypeSpec keyType, PropertyTypeSpec valueType) {
            this.keyType = keyType;
            this.valueType = valueType;
        }
    }



}
