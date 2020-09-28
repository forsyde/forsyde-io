# ForSyDe IO

This repository holds the graph model view of ForSyDe,
specially the definition of the exchange formats that
comply to this view so that supporting libraries
can be generated for different programming languages.

In a sense, this is a "meta-model" for ForSyDe system models.
The meta-model itself should be _as flat as possible_  due to:
 1) its graph foundation: different classes should really mean different _kinds_ of elements,
 2) its paradigm portability: OOP supports lots of hierarchies, but FP is stricter and supports
    better flat and disjoint types.

## Formats

These formats are defined in a way that the libraries generated from `forsyde-io`
can depend on already made tools, for each programming language targeted, as much
as possible. For example, trying to generate XML that is complaint to `jackson` xml.

### XML

1. The name of the tag _is_ the type of the object whenever the data is not a reference.
   For example, the XML would start as,

        <ForSyDeIO revision="..."...>
          <contents ...>
          ...
          <ConstructProcess identifier="greatProcess" ...>
          <ConstructProcess/>
          ...
        <ForSyDeIO/>

    Otherwise, the tag refers to the property inside the class and references another object.

## Guidelines

  1. The semantic versioning `x.y.z` has the following mentality for this project:
     - if `x` goes up, it means that _major_ scientific concepts have been implemented. Therefore, backwards compatibility is likely jeopardized.
     - if `y` goes up, it means that _minor_ scientific concepts have been implemented. Therefore, backwards compatibility is partially assured and requires investigation.
     - if `z` goes up, it means that _no_ scientific concepts have been implemented, but only facilities for the end user/designer. Therefore, backwards compatibility should be maintained.

<!--- Comments

As component models are quite easy to be translated to the object-oriented paradigm, one of the main sources for what can be found in a ForSyDe model is the ECore model, that can be read and modified however you like as long as you don't use any specific OOP concepts like abstract classes, interfaces etc. Consider the components as simple information structures that have a precise and unambigous meaning once you read them.

The prefeered way to interface different parts of a flow based on ForSyDe models is to parse and dump the models
described here. For that, there is a "flat" representation that is nearly just text-based and, as the name implies,
flat. Since the model is basically a graph, this is easily done. There is also a XMI representation of the model, which
is intended to be used to dialog with external users of the ForSyDe domain who usually prefers Eclipse or XML based
representations. Otherwise, stick to the "flat" ForSyDe-IO. Also, once a proper front-end exists for the tooling
ecosystem, the "flat" representation shall be default choice for generation and interaction between different
tools.
--->
