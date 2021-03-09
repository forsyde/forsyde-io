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