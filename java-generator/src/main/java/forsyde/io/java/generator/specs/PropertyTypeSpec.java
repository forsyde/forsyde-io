package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")   
@JsonSubTypes({
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.IntegerPropertyType.class, name = "IntegerPropertyType"),
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.RealPropertyType.class, name = "RealPropertyType") ,
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.BooleanPropertyType.class, name = "BooleanPropertyType"),
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.StringPropertyType.class, name = "StringPropertyType"),
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.ArrayPropertyType.class, name = "ArrayPropertyType"),
    @JsonSubTypes.Type(value = forsyde.io.java.generator.specs.PropertyTypeSpec.MapPropertyType.class, name = "MapPropertyType")
})
public abstract class PropertyTypeSpec {

    // abstract public String getCategory();

    public Optional<PropertyTypeSpec> getValueType() {
        if (this instanceof ArrayPropertyType arrayPropertyType) {
            return Optional.of(arrayPropertyType.valueType);
        } else if (this instanceof MapPropertyType mapPropertyType) {
            return Optional.of(mapPropertyType.valueType);
        }
        return Optional.empty();
    }

    public Optional<PropertyTypeSpec> getKeyType() {
        if (this instanceof MapPropertyType mapPropertyType) {
            return Optional.of(mapPropertyType.keyType);
        }
        return Optional.empty();
    }


    @JsonSerialize
    public static class IntegerPropertyType extends PropertyTypeSpec {
        public int bits = 32;
        public boolean unsigned = false;

        public IntegerPropertyType(int bits, boolean unsigned) {
            this.bits = bits;
            this.unsigned = unsigned;
        }

        // @Override
        // public String getCategory() {
        //     return "Integer";
        // }
    }

    @JsonSerialize
    public static class RealPropertyType extends PropertyTypeSpec {
        public int bits = 32;

        public RealPropertyType(int bits) {
            this.bits = bits;
        }

        // @Override
        // public String getCategory() {
        //     return "Real";
        // }
    }

    @JsonSerialize
    public static class BooleanPropertyType extends PropertyTypeSpec {

        // @Override
        // public String getCategory() {
        //     return "Boolean";
        // }
    }

    @JsonSerialize
    public static class StringPropertyType extends PropertyTypeSpec {

        // @Override
        // public String getCategory() {
        //     return "String";
        // }
    }

    @JsonSerialize
    public static class ArrayPropertyType extends PropertyTypeSpec {
        @JsonAlias("value_type")
        PropertyTypeSpec valueType;

        public ArrayPropertyType(PropertyTypeSpec valueType) {
            this.valueType = valueType;
        }

        // @Override
        // public String getCategory() {
        //     return "Array";
        // }

        public void setValueType(PropertyTypeSpec valueType) {
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

        // @Override
        // public String getCategory() {
        //     return "Map";
        // }

        public void setKeyType(PropertyTypeSpec keyType) {
            this.keyType = keyType;
        }

        public void setValueType(PropertyTypeSpec valueType) {
            this.valueType = valueType;
        }
    }



}
