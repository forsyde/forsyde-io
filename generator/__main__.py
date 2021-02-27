import common as common
import python as pygen
import java as jgen

pygen.generate(common.get_spec())
jgen.generate(common.get_spec())