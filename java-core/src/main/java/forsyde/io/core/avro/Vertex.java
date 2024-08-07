/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package forsyde.io.core.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Vertex extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3900709946080597003L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Vertex\",\"namespace\":\"forsyde.io.core.avro\",\"fields\":[{\"name\":\"identifier\",\"type\":\"string\"},{\"name\":\"ports\",\"type\":{\"type\":\"array\",\"items\":\"string\",\"default\":[]}},{\"name\":\"traits\",\"type\":{\"type\":\"array\",\"items\":\"string\",\"default\":[]}},{\"name\":\"properties\",\"type\":{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"VertexProperty\",\"fields\":[{\"name\":\"value\",\"type\":[\"int\",\"long\",\"float\",\"double\",\"string\",{\"type\":\"array\",\"items\":\"VertexProperty\"},{\"type\":\"map\",\"values\":\"VertexProperty\"}]}]},\"default\":{}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Vertex> ENCODER =
      new BinaryMessageEncoder<Vertex>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Vertex> DECODER =
      new BinaryMessageDecoder<Vertex>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Vertex> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Vertex> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Vertex> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Vertex>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Vertex to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Vertex from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Vertex instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Vertex fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence identifier;
   private java.util.List<java.lang.CharSequence> ports;
   private java.util.List<java.lang.CharSequence> traits;
   private java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> properties;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Vertex() {}

  /**
   * All-args constructor.
   * @param identifier The new value for identifier
   * @param ports The new value for ports
   * @param traits The new value for traits
   * @param properties The new value for properties
   */
  public Vertex(java.lang.CharSequence identifier, java.util.List<java.lang.CharSequence> ports, java.util.List<java.lang.CharSequence> traits, java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> properties) {
    this.identifier = identifier;
    this.ports = ports;
    this.traits = traits;
    this.properties = properties;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return identifier;
    case 1: return ports;
    case 2: return traits;
    case 3: return properties;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: identifier = (java.lang.CharSequence)value$; break;
    case 1: ports = (java.util.List<java.lang.CharSequence>)value$; break;
    case 2: traits = (java.util.List<java.lang.CharSequence>)value$; break;
    case 3: properties = (java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'identifier' field.
   * @return The value of the 'identifier' field.
   */
  public java.lang.CharSequence getIdentifier() {
    return identifier;
  }


  /**
   * Sets the value of the 'identifier' field.
   * @param value the value to set.
   */
  public void setIdentifier(java.lang.CharSequence value) {
    this.identifier = value;
  }

  /**
   * Gets the value of the 'ports' field.
   * @return The value of the 'ports' field.
   */
  public java.util.List<java.lang.CharSequence> getPorts() {
    return ports;
  }


  /**
   * Sets the value of the 'ports' field.
   * @param value the value to set.
   */
  public void setPorts(java.util.List<java.lang.CharSequence> value) {
    this.ports = value;
  }

  /**
   * Gets the value of the 'traits' field.
   * @return The value of the 'traits' field.
   */
  public java.util.List<java.lang.CharSequence> getTraits() {
    return traits;
  }


  /**
   * Sets the value of the 'traits' field.
   * @param value the value to set.
   */
  public void setTraits(java.util.List<java.lang.CharSequence> value) {
    this.traits = value;
  }

  /**
   * Gets the value of the 'properties' field.
   * @return The value of the 'properties' field.
   */
  public java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> getProperties() {
    return properties;
  }


  /**
   * Sets the value of the 'properties' field.
   * @param value the value to set.
   */
  public void setProperties(java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> value) {
    this.properties = value;
  }

  /**
   * Creates a new Vertex RecordBuilder.
   * @return A new Vertex RecordBuilder
   */
  public static forsyde.io.core.avro.Vertex.Builder newBuilder() {
    return new forsyde.io.core.avro.Vertex.Builder();
  }

  /**
   * Creates a new Vertex RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Vertex RecordBuilder
   */
  public static forsyde.io.core.avro.Vertex.Builder newBuilder(forsyde.io.core.avro.Vertex.Builder other) {
    if (other == null) {
      return new forsyde.io.core.avro.Vertex.Builder();
    } else {
      return new forsyde.io.core.avro.Vertex.Builder(other);
    }
  }

  /**
   * Creates a new Vertex RecordBuilder by copying an existing Vertex instance.
   * @param other The existing instance to copy.
   * @return A new Vertex RecordBuilder
   */
  public static forsyde.io.core.avro.Vertex.Builder newBuilder(forsyde.io.core.avro.Vertex other) {
    if (other == null) {
      return new forsyde.io.core.avro.Vertex.Builder();
    } else {
      return new forsyde.io.core.avro.Vertex.Builder(other);
    }
  }

  /**
   * RecordBuilder for Vertex instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Vertex>
    implements org.apache.avro.data.RecordBuilder<Vertex> {

    private java.lang.CharSequence identifier;
    private java.util.List<java.lang.CharSequence> ports;
    private java.util.List<java.lang.CharSequence> traits;
    private java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> properties;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(forsyde.io.core.avro.Vertex.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.identifier)) {
        this.identifier = data().deepCopy(fields()[0].schema(), other.identifier);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.ports)) {
        this.ports = data().deepCopy(fields()[1].schema(), other.ports);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.traits)) {
        this.traits = data().deepCopy(fields()[2].schema(), other.traits);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.properties)) {
        this.properties = data().deepCopy(fields()[3].schema(), other.properties);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Vertex instance
     * @param other The existing instance to copy.
     */
    private Builder(forsyde.io.core.avro.Vertex other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.identifier)) {
        this.identifier = data().deepCopy(fields()[0].schema(), other.identifier);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.ports)) {
        this.ports = data().deepCopy(fields()[1].schema(), other.ports);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.traits)) {
        this.traits = data().deepCopy(fields()[2].schema(), other.traits);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.properties)) {
        this.properties = data().deepCopy(fields()[3].schema(), other.properties);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'identifier' field.
      * @return The value.
      */
    public java.lang.CharSequence getIdentifier() {
      return identifier;
    }


    /**
      * Sets the value of the 'identifier' field.
      * @param value The value of 'identifier'.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder setIdentifier(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.identifier = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'identifier' field has been set.
      * @return True if the 'identifier' field has been set, false otherwise.
      */
    public boolean hasIdentifier() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'identifier' field.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder clearIdentifier() {
      identifier = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'ports' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getPorts() {
      return ports;
    }


    /**
      * Sets the value of the 'ports' field.
      * @param value The value of 'ports'.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder setPorts(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[1], value);
      this.ports = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'ports' field has been set.
      * @return True if the 'ports' field has been set, false otherwise.
      */
    public boolean hasPorts() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'ports' field.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder clearPorts() {
      ports = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'traits' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getTraits() {
      return traits;
    }


    /**
      * Sets the value of the 'traits' field.
      * @param value The value of 'traits'.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder setTraits(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[2], value);
      this.traits = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'traits' field has been set.
      * @return True if the 'traits' field has been set, false otherwise.
      */
    public boolean hasTraits() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'traits' field.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder clearTraits() {
      traits = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'properties' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> getProperties() {
      return properties;
    }


    /**
      * Sets the value of the 'properties' field.
      * @param value The value of 'properties'.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder setProperties(java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty> value) {
      validate(fields()[3], value);
      this.properties = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'properties' field has been set.
      * @return True if the 'properties' field has been set, false otherwise.
      */
    public boolean hasProperties() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'properties' field.
      * @return This builder.
      */
    public forsyde.io.core.avro.Vertex.Builder clearProperties() {
      properties = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Vertex build() {
      try {
        Vertex record = new Vertex();
        record.identifier = fieldSetFlags()[0] ? this.identifier : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.ports = fieldSetFlags()[1] ? this.ports : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[1]);
        record.traits = fieldSetFlags()[2] ? this.traits : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[2]);
        record.properties = fieldSetFlags()[3] ? this.properties : (java.util.Map<java.lang.CharSequence,forsyde.io.core.avro.VertexProperty>) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Vertex>
    WRITER$ = (org.apache.avro.io.DatumWriter<Vertex>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Vertex>
    READER$ = (org.apache.avro.io.DatumReader<Vertex>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










