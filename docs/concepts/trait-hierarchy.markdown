---
title: The trait hierarchy and viewers
layout: default
permalink: /concept/trait-hierarchy
nav_order: 2
parent: Concepts
---

<script type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.3/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>

## Trait hierarchy

A trait is a interface of labels that a vertex or edge must honor. 
Exemplifying with the current "standard" ForSyDe IO trait hierarchy, 
a trait `AbstractDigitalComponent` dictates that the node must possess an 
integer property “nominal frequency in HZ”; Whereas a trait of `SYComb` dictates that the node
must be connected to another node in a port “combinator”.

The traits form a hierarchy (a tree) from least to most specific. For the sake
of understanding, you can consider this trait hierarchy equal to class hierarchies from Object Oriented programming.
The next figure showcases an example trait hierarchy which contains the examples mentioned
in the previous paragraph.

<img width=500 src="{{ site.baseurl }}/assets/images/svg/examplehierarchy.svg" />

As classes impose static structures to objects in Object-oriented programming, traits impose
the _minimum_ expected structures. A big difference is that a vertex can have multiple
_unrelated_ traits, giving it possible different aspects.

For example, suppose a trait hierarchy with two unrelated traits, `Instrumented` and `Task`.
A vertex that conforms to `Instrumented` must have an integer property called "global WCET";
And a vertex that conforms to `Task` must have an integer property called "period".

Assume now a system graph with a vertex `Element1` that declares the traits `Instrumented` and `Task`.
Using the trait hierarchy just described, a _consistent_ model (system graph + trait hierarchy),
requires `Element1` to have at least the two properties listed. One such valid case would be the following
properties:
* period: an int of 20
* name: a string of "me"
* global WCET: an int of 10

## Viewers

Viewers build upon trait hierarchies to transform a generic system graph into a 
typed object graph. In other words, "viewing" a vertex enables one to access its properties and ports
just like it was a class in standard object oriented programming.

Take the same example previously given with `Element1`, `Instrumented` and `Task`.
Both `Instrumented` and `Task` traits have their viewers which temporarily transforms `Element1`
into an object of traits `Instrumented` and `Task`. The next image showcases with color coding
how the viewers enable one to know ahead-of-time the names of the properties and access them
in a type-safe manner.

<img width=800 src="{{ site.baseurl }}/assets/images/svg/viewerdiagram.svg" />

The viewers layer is a key abstraction for two reasons:
1. It enables cross-cutting modelling in the same model,
2. It enables simple typed consumption of the generic system graph in statically typed languages.


<!-- If the node does not honor such interfaces for every one of
its declared traits, then the model is inconsistent. This trait
hierarchy gives ForSyDe IO to have a “meta-model” which
is open, like MLIR [Lattner2019]. It also suits well the idea
of continuous refinement of the model, as the traits can be
refined to ever mode detailed interfaces in a design flow.
Methods (and its tools) in an automated design flow can pin-
point the different parts of a system graph. In a Design Space
Exploration (DSE) setting, this translates in grouping the
application from the platform sub-graphs in a system graph.
Additionally, the grouping can be elevated to a semantic level.
Say, in the same system graph, one could find sub applications
of different MoCs, such as Synchronous Dataflow (SDF)
applications and Lingua Franca applications. IDeSyDe 1 ,
the latest DSE tool in the ForSyDe tool-set, operates in this
grouping manner.
Figure 2 shows the visualization of a trait hierarchy that
would contain the traits discussed in the previous paragraphs.
Every arrow defines the outgoing trait is a refinement of the
incoming trait. For instance, the trait SYElem refines the
trait Application Layer, so that all required ports and
properties from Application Layer are also present in
SYElem. -->

