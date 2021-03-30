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
    
    public {{req_port_data['class']}} get{{req_port | capitalize }}(ForSyDeModel model) {
        return null;
    }
    {% endfor %}
    {% endif %}
}
