# ForSyDe IO

This repository contains relevant implementation details for the
ForSyDe IO subproject in ForSyDe. This includes:

 1. The type meta-model for proper syntatic consistency in the graph models,
 2. Code that generates and packages each of the supporting libraries implemented,
 3. Examples for the model specification showing how a model is saved on disk.

For further info, please refer to the [documentation website](https://forsyde.github.io/forsyde-io)! This readme won't contain
much as to no repeat information where not necessary.

## Contributing

### New supporting libraries

New support of the model on other languages are always welcome! The goal is that the most famous and well-known repository
for packages in the new language contains your `forsyde-io` libraries. The canonical way to do this would be to add a new subfolder
in this source repo which can upload all code that is both generated and written by hand. 

As an example, the Python supporting package generates the `types` classes from the graph.json `file` along the NetworkX-backed core classes before
uploading all the sources to PyPI.

### Versioning

This package follows the [SemVer versioning scheme](https://semver.org/) and roughly follows the [Haskell PVP decision tree](https://pvp.haskell.org/#decision-tree)for
deciding when to bump new versions _as much as possible_.

