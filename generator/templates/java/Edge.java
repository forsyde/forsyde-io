package forsyde.io.java.types.edge;

import java.util.Optional;
{% if type_data or not type_data['superClasses'] %}
import forsyde.io.java.Edge;
{%- endif %}
{% if type_data and 'required_ports' in type_data %}
import forsyde.io.java.Port;
import forsyde.io.java.ForSyDeModel;
{%- endif %}
import forsyde.io.java.Port;
import forsyde.io.java.Vertex;

{% if type_data and type_data['superClasses'] %}
class {{type_name}} extends {{type_data['superClasses'][0]}} {
{% else %}
class {{type_name}} extends Edge {
{% endif %}

    /**
	   * @param type
	   * @param target
	   * @param source
	   */
	  public {{type_name}}(Vertex target, Vertex source) {
        super(target, source);
	  }
	  
	  /**
	   * @param type
	   * @param target
	   * @param source
	   * @param targetPort
	   * @param sourcePort
	   */
	  public {{type_name}}(Vertex source, Vertex target, Port sourcePort, Port targetPort) {
        super(target, source, sourcePort, targetPort);
	  }
	  
	  /**
	   * @param type
	   * @param target
	   * @param source
	   * @param targetPort
	   * @param sourcePort
	   */
	  public {{type_name}}(Vertex source, Vertex target, Optional<Port> sourcePort, Optional<Port> targetPort) {
        super(target, source, sourcePort, targetPort);
	  }

    @Override
    public String getTypeName() {
        return "{{type_name}}";
    };

}
