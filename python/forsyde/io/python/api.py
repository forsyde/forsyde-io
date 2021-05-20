import pathlib
import logging
import warnings
from typing import Optional

from forsyde.io.python import LOGGER_NAME
from forsyde.io.python.core import ForSyDeModel
from forsyde.io.python.drivers import ForSyDeMLDriver
from forsyde.io.python.drivers import ForSyDeXMLDriver
from forsyde.io.python.drivers import ForSyDeDotDriver
from forsyde.io.python.drivers import ForSyDeGraphMLDriver


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
    logger = logging.getLogger(LOGGER_NAME)
    ext = pathlib.Path(sink).suffix
    if ext == '.forxml':
        ForSyDeMLDriver().write(model, sink)
    elif ext == '.xml':
        logger.warning(
            f"Ad hoc xml is not supported. Converting to '.forxml' instead.")
        ForSyDeMLDriver().write(model, sink.replace('.xml', '.forxml'))
    elif ext == '.dot' or ext == ".gv":
        ForSyDeDotDriver().write(model, sink)
    elif ext == ".graphml":
        ForSyDeGraphMLDriver().write(model, sink)
    else:
        raise NotImplementedError(f"Format {ext} is unkown.")
