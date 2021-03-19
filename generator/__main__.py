import generator.common as common
import generator.python as pygen
import generator.java as jgen
import generator.haskell as hsgen


def main():
    pygen.generate(common.get_spec())
    jgen.generate(common.get_spec())
    hsgen.generate(common.get_spec())


main()
