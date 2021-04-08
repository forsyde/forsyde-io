from typing import Dict
from typing import List

import forsyde.io.python.core as core

{% for type_name, type_data in vertexTypes.items() %}
{% if type_data['superClasses'] %}
class {{type_name}}({{type_data['superClasses'] | join(', ') }}):
{% else %}
class {{type_name}}(core.Vertex):
{% endif %}
    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    {{type_data['doc'] if type_data and 'doc' in type_data else ''}}
	"""

    def get_type_tag(self) -> str:
	        return "{{type_name}}"
            
    {% if type_data and 'required_ports' in type_data %}
    {% for req_port, req_port_data in type_data['required_ports'].items() %}
    def get_port_{{req_port}}(self) -> core.Port:
        return self.get_port("{{req_port}}")

    def get_{{req_port}}(self, model) -> "{{req_port_data['class']}}":
        return self.get_neigh("{{req_port}}")

    {% endfor %}
    {% endif -%}
    {% if type_data and 'required_properties' in type_data %}
    {% for req_property, req_property_data in type_data['required_properties'].items() -%}
    {% if 'class' in req_property_data %}
    def get_{{req_property}}(self) -> {{req_property_data | pythonify }}:
    {% else %}
    def get_{{req_property}}(self):
    {% endif %}
        try:
            return self.properties["{{req_property}}"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required '{{req_property}}' property.")

    {% endfor %}
    {% endif %}
{% endfor %}

{% for type_name, type_data in edgeTypes.items() %}
{% if type_data['superClasses'] %}
class {{type_name}}({{type_data['superClasses'] | join(', ') }}):
{% else %}
class {{type_name}}(core.Edge):
{% endif %}


    def get_type_tag(self) -> str:
        return "{{type_name}}"
{% endfor %}

class VertexFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    str_to_classes = {
        {%- for type_name, type_data in vertexTypes.items() %}
        "{{type_name}}": {{type_name}}{{',' if not loop.last}}
        {% endfor -%}
    }
    
    @classmethod
    def get_type_from_name(cls,
                    type_name: str
                    ) -> type:
        if type_name in cls.str_to_classes:
            return cls.str_to_classes[type_name]
        raise NotImplementedError(
            f"The type '{type_name}' is not recognized."
        )


    @classmethod
    def build(
        cls,
        identifier: str,
        type_name: str,
        ports = None,
        properties = None
        ) -> core.Vertex:
        try:
            vtype = cls.get_type_from_name(type_name)
            return vtype(
                identifier=identifier,
                ports=ports if ports else set(),
                properties=properties if properties else dict()
            )
        except KeyError: 
            raise NotImplementedError(
                f"The Vertex type '{type_name}' is not recognized."
            )

class EdgeFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    str_to_classes = {
        {%- for type_name, type_data in edgeTypes.items() %}
        "{{type_name}}": {{type_name}}{{',' if not loop.last}}
        {% endfor -%}
    }

    @classmethod
    def get_type_from_name(cls,
                    type_name: str
                    ) -> type:
        if type_name in cls.str_to_classes:
            return cls.str_to_classes[type_name]
        raise NotImplementedError(
            f"The type '{type_name}' is not recognized."
        )

    @classmethod
    def build(
        cls,
        source: core.Vertex,
        target: core.Vertex,
        type_name: str
    ) -> core.Edge:
        try:
            etype = cls.get_type_from_name(type_name)
            return etype(
                source_vertex = source,
                target_vertex = target
            )
        except KeyError:
            raise NotImplementedError(
                f"The Edge type '{type_name}' is not recognized."
            )