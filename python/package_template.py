from enum import Enum
from enum import auto

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
