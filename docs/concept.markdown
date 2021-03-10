---
title: Concepts
layout: default
permalink: /concept/
nav_order: 3
---

# Prelude

The main driver for an Intermediate Representation at a system-level is
to be able to perform automated [Design Space Exploration]({{site.projects.idesyde}})
and use that to generate optimized and/or correct code for the models
being considered.

Yes, very much like optimizing compilers (e.g [LLVM Clang](https://clang.llvm.org/)) do for "back-end".

The problem is that going down such a high-level model (specification) down to a [ISA](https://en.wikipedia.org/wiki/Instruction_set_architecture)
level implementation can be [quite tough for our best algorithms and computers](https://en.wikipedia.org/wiki/NP-hardness).
So, sadly, we have to make a compromise. Don't worry, [we're not the only ones doing so](https://mlir.llvm.org/).

Our sweet spot is then this: we start as high as communication, synchronization, time etc allows, and stop
at the arithmetic-logical level. For example, we take a bunch of communicating C code in a platform and consider
all transactions and synchronizations, but consider the C functions that "do the job" as black boxes with inputs
and outputs.

Of course, if one day we break this [pesky complexity barrier](https://en.wikipedia.org/wiki/P_versus_NP_problem) that
binds us back, maybe we can consider _everything at once_. Until then (and likely forever), we do our best by focusing in some areas.

Our main niche area is safety-oriented system design, that is, where platforms have rigid service garantees and
the application models are analyzable. 

# Inspiration

Most application models can be drawn or expressed as graph-like models. From [Kripke structures](https://en.wikipedia.org/wiki/Kripke_structure_(model_checking)) that are extensively
used in model checking to [Synchronous dataflow graphs](http://literature.cdn.keysight.com/litweb/pdf/ads15/ptolemy/pt093.html), we can always find a way to put all information we require
in a "some metadata". Nowadays this is usually called a [property graph](https://en.wikipedia.org/wiki/Graph_database#Labeled-property_graph). 
Mathematically, we could say it is a [labelled graph](https://en.wikipedia.org/wiki/Graph_labeling).

On the other hand, hardware diagrams are nearly universally drawn as some graph-like structures with ports.
VHDL and Verilog themselves can be argued to be a component-based environment to specify hardware. It follows
that this component styled models can also be subsumed in a graph model that has some notion of ports.

Therefore, our model is a graph that has notions of ports and properties. 

One last important aspect is the notion of node families, or node types. Similar to what is usually
done in [Object Oriented Programming](https://en.wikipedia.org/wiki/Object-oriented_programming), we want to know what "kind" is each node and egde associated with, so that we
can perform analysis and give them both semantics and syntatic correctness.

hence, our labelled port graph also has a notion of types, or kinds.

# Formal definition

## Mathematicals

It follows closely the inspiration mentioned and has been published in paper (put ref here). 

## Textual specification

The specification is a variant of the [graphML](http://graphml.graphdrawing.org/primer/graphml-primer.html), neither a subset or a superset.

It can, however, be unambigously translated back and forth to the standard graphML whenever
necessary. The key difference between the standard specification, or schema, is that the
data attributes of every node cannot be nested, whereas in the ForSyDe IO specification
this is allowed, in order to make our life easier.

Check some examples in the [source repository]({{ site.sources.forsydeio }}/tree/master/examples)
to grasp the specification and schema better,
but rest assured that knowing the graphML one is more than enough to
comprehend ForSyDe IO's.

# Supporting libraries

The concepts enables some nice streamlining for the parser and writer libraries
pertaining the model. The following image showcases the idea visually.

<img src="{{ site.baseurl }}/assets/images/svg/forsydeio-impl.svg" />

All core graph sub-libraries are hand-coded to use relevant graphing
libraries available at the implementation language (e.g. NetworkX for Python
or jgrapht for Java). Note that relevant does not imply the most performant for
huge graphs!

We also have a meta model for our "types" or "kinds" of vertexes, edges etc, which
are injected into the soon-to-be library so that it gets realised with the graphing
features _and_ the types that a ForSyDe IO model contains.

