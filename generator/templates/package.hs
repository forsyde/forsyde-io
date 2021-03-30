module ForSyDe.IO.Haskell.Types (
  Vertex(..),
  Edge(..),
  Port(..),
  MapItem(..)
  )
    where

import Data.Typeable
import Data.Data
import Data.Map (Map)

data MapItem
  = StringMapItem String
  | IntegerMapItem Integer
  | FloatMapItem Float
  | ListMapItem [MapItem]
  | DictMapItem (Map String MapItem)

data Port i = Port i
{%- for type_name, type_data in portTypes.items() %}
  | {{type_name }} i
{%- endfor %}

data Vertex i = Vertex i
{%- for type_name, type_data in vertexTypes.items() %}
  | {{type_name }} i
    {%- if type_data and 'required_properties' in type_data %}
    {%- for req_property, req_property_data in type_data['required_properties'].items() -%}
    {{' '}}{{req_property_data["class"] | haskellify }}
    {%- endfor %}
    {%- endif %}
{%- endfor %}

data Edge i = Edge i
{%- for type_name, type_data in edgeTypes.items() %}
  | {{type_name }} (Vertex i) (Vertex i) (Maybe (Port i)) (Maybe (Port i))
    {%- if type_data and 'required_properties' in type_data %}
    {%- for req_property, req_property_data in type_data['required_properties'].items() -%}
    {{' '}}{{req_property_data["class"] | haskellify }}
    {%- endfor %}
    {%- endif %}
{%- endfor %}


