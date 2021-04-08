package forsyde.io.java.types.vertex;

import java.util.Map;
import java.util.List;
import forsyde.io.java.core.Port;
{% if not type_data or not type_data['superClasses'] %}
import forsyde.io.java.core.Vertex;
{% endif %}
{% if type_data and 'required_ports' in type_data %}
import forsyde.io.java.core.ForSyDeModel;
{% endif %}

{% if type_data and type_data['superClasses'] %}
public class {{type_name}} extends {{type_data['superClasses'][0]}} {
{% else %}
public class {{type_name}} extends Vertex {
{% endif %}

    /**
     * {@inheritDoc}
     */
    public {{type_name}}(String identifier) {
         super(identifier);
    }
 
    /**
     * {@inheritDoc}
     */
    public {{type_name}}(String identifier, List<Port> ports, Map<String, Object> properties) {
         super(identifier, ports, properties);
    }

    @Override
    public String getTypeName() {
        return "{{type_name}}";
    };

    {% if type_data and 'required_ports' in type_data %}
    {% for req_port, req_port_data in type_data['required_ports'].items() %}
    public Port getPort{{req_port | capitalize }}() {
        return getPort("{{req_port}}").get();
    }
    
    public {{req_port_data | javify}} get{{req_port | capitalize }}(ForSyDeModel model) {
        {% if req_port_data['default'] %}
        return {{req_port_data['default']}};
        {% else %}
        return null;
        {% endif %}
    }
    {% endfor %}
    {% endif %}

    {% if type_data and 'required_properties' in type_data %}
    {% for req_property, req_property_data in type_data['required_properties'].items() %}
    public {{req_property_data | javify}} get{{req_property | snake_to_pascal }}(ForSyDeModel model) 
    {%- if not 'default' in req_property_data -%}
    throws IllegalStateException
    {%- endif %} {
        if (properties.has("{{req_property}}")) {
            return ({{req_property_data | javify}}) properties.get("{{req_property}}");
        } else {
            {%- if 'default' in req_property_data %}
            return {{req_property_data['default']}};
            {%- else %}
            throw new IllegalStateException("Object of type '{{type_name}}' has no required property '{{req_property | snake_to_pascal }}'");
            {%- endif %}
        }
    }
    {% endfor %}
    {% endif %}
}
