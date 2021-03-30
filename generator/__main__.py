import argparse

import generator.common as common
import generator.python as pygen
import generator.java as jgen
import generator.haskell as hsgen

parser = argparse.ArgumentParser(prog="generator")
parser.add_argument("SemVer", nargs='?', default="0.1.0", type=str)

args = parser.parse_args()


def main() -> None:
    version = args.SemVer
    # drop the 'v' prefix if present. Might usually be given from CI.
    if version[0] == 'v':
        version = version[1:]

    pygen.generate(common.get_spec())
    jgen.generate(common.get_spec())
    hsgen.generate(common.get_spec())

    pygen.fix_version(version)
    jgen.fix_version(version)
    hsgen.fix_version(version)
