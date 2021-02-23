package forsyde.io.java.types;

{% if type_data or not type_data['superClasses'] %}
import forsyde.io.java.Vertex;
{% endif %}
{% if type_data and 'required_ports' in type_data %}
import forsyde.io.java.Port;
import forsyde.io.java.ForSyDeModel;
{% endif %}

{% if type_data and type_data['superClasses'] %}
class {{type_name}} extends {{type_data['superClasses'][0]}} {
{% else %}
class {{type_name}} extends Vertex {
{% endif %}

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