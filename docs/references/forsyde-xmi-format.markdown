---
title: ForSyDe IO XMI format
layout: default
permalink: /references/trait-hierarchy
nav_order: 1
parent: References
---

The ForSyDe IO XMI format saves **only the system graph**, not the trait hierarchy!

the reasoning is that the trait hierarchy handling and construction is a compile-time issue and decision,
whereas the system graph manipulation is know before-hand and can be drastically differnet for each
use scenario. Alternatively, the different "types" of objects that must be supports is much lower
than the actual number of objects.

The XMI format is directly derived from an [Ecore model that represents the system graph meta model]({{site.sources.forsydeio}}/tree/master/eclipse/system.graph.model/model).
Note that the actual hand-made system graph code in each support library does not derive directly
from this Ecore model; not even the java one. The motivation was to keep the foundations as clean
and connected to chosen graph libraries as possible. As a matter of fact, the java libraries generate
the XMI as an XML file directly, without any reference to the Ecore model.

Check some examples in the [source repository]({{ site.sources.forsydeio }}/tree/master/examples)
to grasp the specification and schema better; Or, open the [eclipse submodule]({{site.sources.forsydeio}}/tree/master/eclipse) with Eclipse
to playa around with "dynamic instances" until you get a good graps of how the XMI is generated.