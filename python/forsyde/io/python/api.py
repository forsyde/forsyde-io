import pathlib
import warnings
from typing import Optional

from forsyde.io.python.core import ForSyDeModel
from forsyde.io.python.drivers import ForSyDeMLDriver
from forsyde.io.python.drivers import ForSyDeXMLDriver


def load_model(source: str,
               other_model: Optional[ForSyDeModel] = None) -> ForSyDeModel:
    """TODO: Docstring for read.

    :source: TODO
    :returns: TODO

    """
    # if '.db' in source:
    #     self.read_db(source)
    if '.forxml' in source:
        return ForSyDeMLDriver().read(source, other_model)
    elif '.xml' in source:
        warnings.warn(
            "The 'xml' python driver is deprecated. Read from 'forxml' instead.",
            DeprecationWarning)
        return ForSyDeXMLDriver().read(source, other_model)
    else:
        ext = pathlib.Path(source).suffix
        raise NotImplementedError(f"Format {ext} is unkown.")


def write_model(model: ForSyDeModel, sink: str) -> None:
    if '.forxml' in sink:
        ForSyDeMLDriver().write(model, sink)
    elif '.xml' in sink:

        ForSyDeXMLDriver().write(model, sink)
    else:
        ext = pathlib.Path(sink).suffix
        raise NotImplementedError(f"Format {ext} is unkown.")
