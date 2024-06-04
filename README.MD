# ForSyDe IO

This repository contains relevant implementation details for the
ForSyDe IO subproject in ForSyDe.
For further info, please refer to the [documentation website](https://forsyde.github.io/forsyde-io)! This readme won't contain
much as to no repeat information where not necessary.

The current status of the supporting libraries are:

|Programming language| Load SG* | Save SG* | Internal TH** generation | Internal TH** export | External TH** generation |
|--------------------|----------|----------|------------------------|--------------------|------------------------|
| Java (> 14) | [x] | [x] | [x] | [x] | [ ] |
| Rust | [x] | [x] | [ ] | [ ] | [x] |

* *SG: System graph
* **TH: Trait hierarchy

## Versioning

This package follows the [SemVer versioning scheme](https://semver.org/) and roughly follows the [Haskell PVP decision tree](https://pvp.haskell.org/#decision-tree)for
deciding when to bump new versions _as much as possible_.
