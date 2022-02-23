---
title: ForSyDe IO XMI format
layout: default
permalink: /references/trait-hierarchy
nav_order: 1
parent: References
---

The specification is close to the [graphML](http://graphml.graphdrawing.org/primer/graphml-primer.html), 
but in performed in a XMI complaint way.

It can, however, be unambigously translated back and forth to the standard graphML whenever
necessary. The key difference between the standard specification, or schema, is that the
data attributes of every node cannot be nested, whereas in the ForSyDe IO specification
this is allowed, in order to make our life easier.

Check some examples in the [source repository]({{ site.sources.forsydeio }}/tree/master/examples)
to grasp the specification and schema better,
but rest assured that knowing the graphML one is more than enough to
comprehend ForSyDe IO's.