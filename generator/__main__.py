import generator.common as common
import generator.python as pygen
import generator.java as jgen

pygen.generate(common.get_spec())
jgen.generate(common.get_spec())