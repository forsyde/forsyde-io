from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

class VertexTrait(core.Trait, Enum):
{%- for type_name, type_data in vertexTraitSuper.items() %}
    {{type_name}} = auto()
{%- endfor %}

    @classmethod
    def refines_static(cls, one, other):
    {%- for type_name, type_data in vertexTraitSuper.items() %}
    {%- for super_trait in type_data %}
        if one is cls.{{type_name}} and other is cls.{{super_trait}}:
            return True
    {%- endfor %}
    {%- endfor %}
        return False

    def refines(self, o):
        return VertexTrait.refines_static(self, o)

class EdgeTrait(core.Trait, Enum):
{%- for type_name, type_data in edgeTraitSuper.items() %}
    {{type_name}} = auto()
{%- endfor %}

    @classmethod
    def refines_static(cls, one, other):
    {%- for type_name, type_data in edgeTraitSuper.items() %}
    {%- for super_trait in type_data %}
        if one is cls.{{type_name}} and other is cls.{{super_trait}}:
            return True
    {%- endfor %}
    {%- endfor %}
        return False

    def refines(self, o):
        return EdgeTrait.refines_static(self, o)

{% for type_name, type_data in vertexTraits.items() %}
{%- if type_data['superTraits']|length > 0 %}
class {{type_name}}({{ type_data['superTraits'] | join(', ') }}):
{%- else %}
class {{type_name}}(core.Vertex):
{%- endif %}

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

{%- for prop_name, prop_data in vertexTraitProps[type_name].items() %}
    @property
    def {{prop_name}}(self) -> {{prop_data}}:
        return self.properties["{{prop_name}}"]
        {# if "{{prop_name}}" in v.properties:
            return v.properties["{{prop_name}}"]
        else: #}
        {# {%- for trait_name in prop_data[0] %}
        {%- if prop_name in default_property_map %}
            if v.has_trait(VertexTrait.{{trait_name}}):
                return {{default_property_map[prop_name]}}
        {%- else %}
            if v.has_trait(VertexTrait.{{trait_name}}):
                raise ValueError("Property {{prop_name}} should exist in vertex '" + v.identifier + "' with trait {{trait_name}}, but does not.")
        {%- endif %}
        {%- endfor %}
            else:
                return None #}
{% endfor %}
{% endfor %}