---
title: Example
layout: default
permalink: /concept/example
nav_order: 1
parent: Concepts
---

In this example section, whenever a file is refeered to, it means implicitly those that
can be found in the [project's example folder]({{site.sources.forsydeio}}/tree/master/examples). For example
if it is mentioned here "look at `sobel2mpsoc.forxml`, it means [the corresponding file in the source code
repository]({{site.sources.forsydeio}}/tree/master/examples/sobel2mpsoc.forxml).

# An SDF application to a standard MPSoC

Our design instance is a small scale version of a standard application to MPSoC formulation.
As usual, our application model permits a parallelizable and pipelineable solution, which
we'd like to exploit, whereas our platform model is a time-triggerable architecture, for 
both CPU computation and interconnect communication. 

Specifically, our application is an SDF model out of the [Sobel filter](https://en.wikipedia.org/wiki/Sobel_operator) and our platform is a tile-based architecture
with 4 tiles connected by a TDMA bus. The figure (extracted from the DATE'21 paper) below shows this visually.

<img src = "{{ site.baseurl }}/assets/images/svg/problem_instance.svg" width="700">

Now, naturally, we need to represent this nice mental representation in a way a computer com understand. This
is where ForSyDe IO comes in.

In the future, some, or all, of these elements would be produced from programming languages that would
function with DSLs from ForSyDe. For instance, by using [ForSyDe Shallow]({{ site.projects.forsyde }}/forsyde-shallow/), we could obtain the application
subsection of the whole system description. Similarly, maybe with a couple modifications, we could get
a platform model out of a [Chisel program](https://www.chisel-lang.org/) to be part of the whole system description.

For now, we are content in writting the ForSyDe IO model on disk directly as XML.

*Note that the [whole model is available already]({{site.sources.forsydeio}}/tree/master/examples/sobel2mpsoc.forxml) in case you want to have it handily open at all time.*

The platform can be written down directly as components with the corresponding traits that one finds
in the ["meta" model]({{site.sources.forsydeio}}/tree/master/meta.json): they are `AbstractProcessingElement`s at a minimum.
So we write:

```
<node id="tile1" traits="AbstractProcessingComponent">
</node>
<node id="tile2" traits="AbstractProcessingComponent">
</node>
...
```
**Note that all nodes in the system graph are identified uniquely**. ForSyDe IO has no name scoping as of now.
And we can say the same for the communication element, except now we know it is more than an `AbstractCommunicationElement`, for it is
also a `TimeDivisionMultiplexer`:

```
<node id="tdmabus" traits="TimeDivisionMultiplexer">
</node>
```

And now, of course, we want to connect the processing elements to the communication! We do that by adding `edge`s between them:
```
<edge source="tile1" target="tdmabus" traits="AbstractPhysicalConnection"/>
<edge source="tdmabus" target="tile1" traits="AbstractPhysicalConnection"/>
```
why both ways, you ask? Because all ForSyDe IO system graphs are directed by definition, which means that if any conceivable
"information" or can flow back and forth, the two components must be connected both ways.

Yes, it is a bit tedious, *but please keep in mind that on-disk ForSyDe IO is meant to be generated, and not hand-made*.
We just do it now due to the lack of "front-end" tools that can generate the models.

The same logic we just did applies to the time-triggered representations. 

```
<node id="order1" traits="TimeTriggeredScheduler">
</node>
...
```

Why do we have that you ask? Because **Everything that must be analyzed in the graph needs to be a node**. 
No exceptions. We will retake this back when we model the signals in the
application as nodes and not edges. Additionally, this extra layer between HW and the application (SW) also permits us to
represent more flexible combinations. For example, the intermediate layers could be bigger, consisting of RTOS'es, partitions,
AUTOSAR tasks etc. In this example are only interested in a "Time Triggered Schedule", hence the name, inherited from the
paper in which it was published, of "order". Also note that have n+1 orders for n tiles: the extra time triggered
schedule will represent the messages to be communicated in the TDMA bus.

