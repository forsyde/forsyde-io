import pathlib
import logging
import warnings
from typing import Optional
from typing import List

from forsyde.io.python import LOGGER_NAME
from forsyde.io.python.core import ForSyDeModel
from forsyde.io.python.drivers import ForSyDeModelDriver


class ForSyDeModelHandler(object):
    def __init__(self, extra_drivers: List[ForSyDeModelDriver] = None) -> None:
        self.drivers: List[ForSyDeModelDriver] = []
        if extra_drivers:
            self.drivers = self.drivers + extra_drivers

    def register_driver(self, driver: ForSyDeModelDriver) -> "ForSyDeModelHandler":
        self.drivers.append(driver)
        return self

    def load_model(self, source: str) -> Optional[ForSyDeModel]:
        """TODO: Docstring for read.

        :source: TODO
        :returns: TODO

        """
        for driver in self.drivers:
            if any(source.endswith(ext) for ext in driver.get_supported_read_formats()):
                return driver.read(source)
        # if '.db' in source:
        #     self.read_db(source)
        # if '.forxml' in source or '.forsyde.xml' in source:
        #     return ForSyDeMLDriver().read(source, other_model)
        # elif '.xml' in source:
        #     warnings.warn(
        #         "The 'xml' python driver is deprecated. Read from 'forxml' instead.",
        #         DeprecationWarning)
        #     return ForSyDeXMLDriver().read(source, other_model)
        # else:
        #     ext = pathlib.Path(source).suffix
        raise NotImplementedError(f"File {source} has no compatible driver.")

    def write_model(self, model: ForSyDeModel, sink: str) -> None:
        for driver in self.drivers:
            if any(sink.endswith(ext) for ext in driver.get_supported_write_formats()):
                return driver.write(model, sink)
        # logger = logging.getLogger(LOGGER_NAME)
        # if sink.endswith(".forxml") or sink.endswith(".forsyde.xml"):
        #     ForSyDeMLDriver().write(model, sink)
        # elif sink.endswith(".xml"):
        #     logger.warning(
        #         "Ad hoc xml is not supported. Converting to '.forxml' instead."
        #     )
        #     ForSyDeMLDriver().write(model, sink.replace(".xml", ".forxml"))
        # elif sink.endswith(".dot") or sink.endswith(".gv"):
        #     ForSyDeDotDriver().write(model, sink)
        # elif sink.endswith(".graphml"):
        #     ForSyDeGraphMLDriver().write(model, sink)
        # else:
        raise NotImplementedError(f"File {sink} has no compatible driver.")
