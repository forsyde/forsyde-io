---
title: Definitions
layout: default
permalink: /concept/definitions
nav_order: 2
parent: Concepts
---

# Inspiration

Most application models can be drawn or expressed as graph-like models. From [Kripke structures](https://en.wikipedia.org/wiki/Kripke_structure_(model_checking)) that are extensively
used in model checking to [Synchronous dataflow graphs](http://literature.cdn.keysight.com/litweb/pdf/ads15/ptolemy/pt093.html), we can always find a way to put all information we require
in a "some metadata". Nowadays this is usually called a [property graph](https://en.wikipedia.org/wiki/Graph_database#Labeled-property_graph). 
Mathematically, we could say it is a [labelled graph](https://en.wikipedia.org/wiki/Graph_labeling).

On the other hand, hardware diagrams are nearly universally drawn as some graph-like structures with ports.
VHDL and Verilog themselves can be argued to be a component-based environment to specify hardware. It follows
that this component styled models can also be subsumed in a graph model that has some notion of ports.

Therefore, our model is a graph that has notions of ports and properties. 

One last important aspect is the notion of node traits, or node contracts. Similar to what is usually
done in [Object Oriented Programming](https://en.wikipedia.org/wiki/Object-oriented_programming), specifically 
the [Prototype-based paradigm](https://en.wikipedia.org/wiki/Prototype-based_programming),
we want to know what "trait" each node and egde is associated with, so that we
can perform analysis and give them both semantics and syntatic correctness.

hence, our labelled port graph also has a notion of traits.

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
