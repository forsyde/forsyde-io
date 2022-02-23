from typing import Any
from typing import Optional
from typing import List
import abc

from forsyde.io.python.core import ForSyDeModel


class ForSyDeModelDriver(abc.ABC):
    @abc.abstractmethod
    def get_supported_write_formats(self) -> List[str]:
        return []

    @abc.abstractmethod
    def get_supported_read_formats(self) -> List[str]:
        return []

    @abc.abstractmethod
    def write(self, model: ForSyDeModel, sink: str) -> None:
        pass

    @abc.abstractmethod
    def read(self, source: str) -> Optional[ForSyDeModel]:
        return None

    def type_to_str(self, t: type) -> str:
        if t is bool:
            return "boolean"
        elif t is int:
            return "int"
        elif t is float:
            return "double"
        elif t is str:
            return "string"
        elif t is list:
            return "array"
        elif t is dict:
            return "stringMap"
        else:
            return "object"

    def str_to_type(self, s: str) -> type:
        if s == "boolean":
            return bool
        elif s == "int":
            return int
        elif s == "integer":
            return int
        elif s == "long":
            return int
        elif s == "float":
            return float
        elif s == "double":
            return float
        elif s == "string":
            return str
        elif s == "array":
            return list
        elif s == "intMap" or s == "intmap":
            return dict
        elif s == "stringMap" or s == "stringmap" or s == "strMap" or s == "strmap":
            return dict
        else:
            return dict

    def typename_from_val(self, v) -> str:
        if isinstance(v, bool):
            return "boolean"
        elif isinstance(v, int):
            # TODO: Find a robust way to distinguish between int and long
            if v.bit_length() > 32:
                return "long"
            else:
                return "int"
        elif isinstance(v, float):
            return "double"
        elif isinstance(v, str):
            return "string"
        elif isinstance(v, list):
            return "array"
        elif isinstance(v, dict):
            # TODO: find a non hac to fix the dict difference
            if len(v) > 0 and all(isinstance(k, int) for k in v):
                return "intMap"
            else:
                return "stringMap"
        else:
            return "object"
