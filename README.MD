# ForSyDe IO

This repository contains information about the format used to persist and manipulate
a ForSyDe model that can be implemented and consumed in different languages.
It also contains (generated) supporting libraries that can serve as a stub for such
implementations.

## Using the supporting libraries

This source repository is intended to be used by a CI flow so that the necessary code is automatically generated every
new version upgrade and uploaded to the relevant package repositories each langauge uses.

For any `<language-io>` folder present in this repo, try to find in the follwoing package repositories:
 - [PyPI for Python](https://test.pypi.org/project/forsyde-io-python/)
 - bintray for Java
 - HackageDB for Haskell
The packages `forsyde-io-<language>` that provides the classes to dump and read models.

## Model overview

A ForSyDe model is given as a _typed-port-pseudo-property-graph_. This means that:

  1) every vertex can connect to itself (the _pseudo_). This enables us to properly describe dataflow models with
    feedback.
  2) every vertex can have an arbitrary number of properties attached to it (the _property_). These properties can
    mostly be anything and describe extra information that is particular to every vertex.
  3) every vertex has a set of ports and every edge can optionally connect two ports (the _port_ part). This helps
    to allieve any amibiguities when process are being constructed out of functions as the inputs and outputs can
    be properly connected during the final artifact generation. 
  4) every vertex, edge and port has an associated "type". This helps to define semantic on top of the graph
    as relationships and be defined over the types. Furthermore, these "types" can be considered contractual, which
    enables tranformations of the model via graph+type matching.

Diagramatically, this can be seen as:

          _________________________________________________
    ---->|T (type)                                         |------>
    ---->|{prop1 : tprop1 ,prop2 : tprop2 ,...} properties |------>
         |{port1: tport1, ...} ports                       |
    ---->|_________________________________________________|------>
          

    vertex1 : type1 (port1 : tport1) ------------------------> vertex2 : type2 (port2 : tport2)
                                            edge : T

## Exchange format

The ideal format captures easily this model and can be described in a single textual file so
that it can be easily be ported anywhere. After experimenting with XML, JSON and other custom formats,
the decision was made to represent the model directly in Datalog/Prolog format as follows.

    vertex(vertexName, vertexType).
    ...
    prop(vertexName, propName, propVal).
    ...
    port(vertexName, portName, protType).
    ...
    edge(sourceVertexName, targetVertexName, sPortName, tPortName, edgeType).
    ...

Which can be easily parsed in most languages or directly consumed by prolog/datalog engines. The latter
whom are intended to be the main deduction and database framework for working with these models.

The types are currently defined in an `ecore` file that hopefully enable easy generation of these types
in all major targeted langauges: Haskell, Java, C/C++, Prolog/Datalog.
The graph section of each programming language supporting library is free to use other graph libraries desired.

## Contribution

### New supporting libraries

New support of the model on other languages are always welcome! The goal is that the most famous and well-known repository
for packages in the new language contains your `forsyde-io` libraries. The canonical way to do this would be to add a new subfolder
in this source repo which can upload all code that is both generated and written by hand. 

As an example, the Python supporting package generates the `types` classes from the ecore `file` along the NetworkX-backed core classes before
uploading all the sources to PyPI.

### Versioning

The semantic versioning `x.y.z` has the following mentality for this project:
  - if `x` goes up, it means that _major_ concepts have been changed. Therefore, backwards compatibility is likely jeopardized.
  - if `y` goes up, it means that _minor_ concepts have been changed. Therefore, backwards compatibility requires investigation.
  - if `z` goes up, it means that the concepts have been incremented losslessly or the core libraries changed. Therefore, backwards compatibility is maintained.
