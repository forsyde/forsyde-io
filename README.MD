# ForSyDe IO

This repository holds the component model interpretation for the ForSyDe methodology so that code can easily be generated and managed for different programming languages.

Pragmatically, the "meta-model" is described in Ecore, the core part of the Eclipse Modelling Framework. For generation, the Epsilon framework is used as to make all the code as independent as possible from either Ecore or Epsilon while also trying to express them as naturally as possible in each target language.

## Guidelines

  1. Prefer Enumeration and "customization" over deeply nested inheritance.
     For example, the binding model basically has one `Binding` Component (class) which has reasonably detailed semantics attached to it by a "category" enumeration.
     Whatever else information required in this component, it can be added as `custom` value to be made sense by any tool which reads the model.
     The rationale for this is to keep things are easy as possible to be parsed and dumped from XML (or other format) and also for it to be kept reasonably
     close to functional (Algebraic data types like) description.
  2. The semantic versioning `x.y.z` has the following mentality for this project:
     - if `x` goes up, it means that _major_ scientific concepts have been implemented. Therefore, backwards compatibility is likely jeopardized.
     - if `y` goes up, it means that _minor_ scientific concepts have been implemented. Therefore, backwards compatibility is partially assured and require investigation.
     - if `z` goes up, it means that _no_ scientific concepts have been implemented, but only facilities for the end user/designer. Therefore, backwards compatibility should be maintained.
