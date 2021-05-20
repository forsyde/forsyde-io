---
title: Concepts
layout: default
permalink: /concept/
nav_order: 3
has_children: true
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

To see how this subproject fits in the one such design flow, check [the extended examples]({{ site.baseurl }}/concept/example).