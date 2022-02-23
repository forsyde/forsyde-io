---
title: The trait hierarchy
layout: default
permalink: /concept/trait-hierarchy
nav_order: 2
parent: Concepts
---

<script type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.3/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>

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

As classes impose static structures to objects in Object-oriented programming, so impose
the traits _minimum_ expected structures. A big difference is that a vertex can have multiple
_unrelated_ traits, giving it possible different aspects.

**UNDER CONSTRUCTION!**

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

