# ForSyDe IO

This repository holds the component model interpretation, and more specifically a graph model, for the ForSyDe methodology so that code can easily be generated and managed for different programming languages.

As component models are quite easy to be translated to the object-oriented paradigm, one of the main sources for what can be found in a ForSyDe model is the ECore model, that can be read and modified however you like as long as you don't use any specific OOP concepts like abstract classes, interfaces etc. Consider the components as simple information structures that have a precise and unambigous meaning once you read them.

The prefeered way to interface different parts of a flow based on ForSyDe models is to parse and dump the models
described here. For that, there is a "flat" representation that is nearly just text-based and, as the name implies,
flat. Since the model is basically a graph, this is easily done. There is also a XMI representation of the model, which
is intended to be used to dialog with external users of the ForSyDe domain who usually prefers Eclipse or XML based
representations. Otherwise, stick to the "flat" ForSyDe-IO. Also, once a proper front-end exists for the tooling
ecosystem, the "flat" representation shall be default choice for generation and interaction between different
tools.

#Pragmatically, the "meta-model" is described in Ecore, the core part of the Eclipse Modelling Framework. For generation, the Epsilon framework is used as to make all the code as independent as possible from either Ecore or Epsilon while also trying to express them as naturally as possible in each target language.

## Guidelines

  1. Use the graph baseline descriptions as much as possible and try to specify very well, and as small as possible,
    any final specifications for the DSE-able models. For instance, the SY model is constructed by the primitive functions
    SYComb, SYDelay and SYClock, since this can be used to describe completely a multi-period Synchronous system.
    With enough hope, this won't translate very badly to other paradigms such as the functional one, which is also a
    major target for these models.
  2. The semantic versioning `x.y.z` has the following mentality for this project:
     - if `x` goes up, it means that _major_ scientific concepts have been implemented. Therefore, backwards compatibility is likely jeopardized.
     - if `y` goes up, it means that _minor_ scientific concepts have been implemented. Therefore, backwards compatibility is partially assured and requires investigation.
     - if `z` goes up, it means that _no_ scientific concepts have been implemented, but only facilities for the end user/designer. Therefore, backwards compatibility should be maintained.
