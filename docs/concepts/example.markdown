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

## Base description

In the future, some, or all, of these elements would be produced from programming languages that would
function with DSLs from ForSyDe. For instance, by using [ForSyDe Shallow]({{ site.projects.forsyde }}/forsyde-shallow/), we could obtain the application
subsection of the whole system description. Similarly, maybe with a couple modifications, we could get
a platform model out of a [Chisel program](https://www.chisel-lang.org/) to be part of the whole system description.

For now, we are content in writting the ForSyDe IO model on disk directly as XML.

*Note that the [whole model is available already]({{site.sources.forsydeio}}/tree/master/examples/sobel2mpsoc.forxml) in case you want to have it handily open at all time.*

### Platform

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

Why do we have that you ask? Because **Everything that must be deeply analyzed in the graph needs to be a node or part of one**. 
No exceptions. We will retake this back when we model the signals in the
application as nodes and not edges. Additionally, this extra layer between HW and the application (SW) also permits us to
represent more flexible combinations. For example, the intermediate layers could be bigger, consisting of RTOS'es, partitions,
AUTOSAR tasks etc. In this example are only interested in a "Time Triggered Schedule", hence the name, inherited from the
paper in which it was published, of "order". Also note that have n+1 orders for n tiles: the extra time triggered
schedule will represent the messages to be communicated in the TDMA bus.

### Application

The same logic applies to the application elements with minor quirks: you need to describe things "the ForSyDe way".
To be more precise, though it does not apply to this particular example, applciations are describe by hierarchical
composition of components. Furthermore, since **everything that must be deeply analyzed in the graph needs to be a node or part of one**,
the signals/messages between the concurrent processes/functions should be also nodes, making them therefore can be analyzed
and synthetized.

In our example, we have the SDF actors and the SDF channels between them, which prompts us to write,

```
<node id="sobel/getPx" traits="SDFComb">
    <port name="combinator"/>
    <port name="imgInput"/>
    <port name="gx"/>
    <port name="gy"/>
    <data attr.name="production" attr.type="object">
        <data attr.name="gx" attr.type="integer">6</data>
        <data attr.name="gy" attr.type="integer">6</data>
    </data>
</node>
...
```

Note the additional `data` elements: in this case we need them so that we know the rate of consumption and production
for the SDF actor, *for each SDF actor*, otherwise our model is incomplete, [according to its definitions](https://ieeexplore.ieee.org/abstract/document/1458143).
As mentioned, the same is done for the SDF channels:

```
<node id="gxsig" traits="Signal">
    <port name="fifoIn"/>
    <port name="fifoOut"/>
    <data attr.name="size" attr.type="integer">8</data>
</node>
...
```

Followed by the logical connections between them:

```
<edge source="sobel/getPx" target="gxsig" traits="Output" sourceport="gx" targetport="fifoIn"/>
...
```

Note that this time we put more ports! Why is that? Just to pave the way for synthesis: Most analysis methods, including DSE, likely
need to know only that the channels and actors are connected. But a synthesis would also need to know the name of the inputs,
their "types", sizes etc... All the information required to generated proper object code for the target platform.

## Making the model synthetizable

Suppose we have now in hands the results of all analysis and optimization steps, and we know now where each SDF actor goes,
the time between each execution etc.

For instance, suppose we look now at one of the `TimeTriggeredScheduler`'s:

```
<node id="order_3" traits="TimeTriggeredScheduler">
    <data attr.name="start-time" attr.type="int">8507</data>
    <data attr.name="trigger-time" attr.type="object">
        <data attr.name="0" attr.type="string">Gy</data>
        <data attr.name="4146" attr.type="string">Abs</data>
    </data>
    <data attr.name="period" attr.type="int">5557</data>
    <data attr.name="time-scale" attr.type="int">1</data>
</node>
```

We could generate the following ANSI C code that represents this continous schedule:

```
// we first wait the initial starting time
time_t last_mark = tile_get_time()
wait_elapsed_from(last_mark, 8507);
while (1) {
    // the period is starting
    last_mark = tile_get_time()

    // since the first execution is at 0, we just do it immediatly
    read_SDF_Comb_Gy(input_SDF_Comb_Gy);
    execute_SDF_Comb_Gy(input_SDF_Comb_Gy, output_SDF_Comb_Gy);
    write_SDF_Comb_Gy(output_SDF_Comb_Gy);

    // we wait until a certain amount of time has elapsed since the period started
    wait_elapsed_from(last_mark, 4146);

    // execute the next in the time triggered queue
    read_SDF_Comb_Abs(input_SDF_Comb_Abs);
    execute_SDF_Comb_Abs(input_SDF_Comb_Abs, output_SDF_Comb_Abs);
    write_SDF_Comb_Abs(output_SDF_Comb_Abs);

    // wait to finish the period and repeat again, forever
    wait_elapsed_from(last_mark, 5557);
}
```

Which is none other than the template instanciation of the following overall template:

```
time_t last_mark = tile_get_time()
wait_elapsed_from(last_mark, <<starting-time>>);
while (1) {
    // the period is starting
    last_mark = tile_get_time()

    <<for all the executions>>
    wait_elapsed_from(last_mark, <<trigger-time>>);

    read_<<sdf_actor_name>>(input_<<sdf_actor_name>>);
    execute_<<sdf_actor_name>>(input_<<sdf_actor_name>>, output_<<sdf_actor_name>>);
    write_<<sdf_actor_name>>(output_<<sdf_actor_name>>);
    <</for>>

    // wait to finish the period and repeat again, forever
    wait_elapsed_from(last_mark, <<period>>);
}
```

which then raises the other question... where were the `execute` functions generated? Well, they need to be minimally added
in the model itself! Notice that the `SDFComb` components have the `combinator` port, which is jargon for the true
function that is executed underneath. They need to point to **another node** in the model which contains the code that
is the implementation of the actor being generated. For instance, one example would be,

```
<node id="sobel/AbsImpl" traits="Implementation">
    <port name="resx">
    <port name="resy">
    <port name="imgOutput"/>
    <data attr.name="types" attr.type="object">
        <data attr.name="resx" attr.type="string">int_16</data>
        <data attr.name="resy" attr.type="string">int_16</data>
        <data attr.name="imgOutput" attr.type="string">uint_8</data>
    </data>
    <data attr.name="inline-code" attr.type="object">
        <data attr.name="ANSI-C" attr.type="string">
            imgOutput = (resx < 0 ? -resx : resx) 
                      + (resy < 0 ? -resy : resy);
        </data>
    </data>
    <data attr.name="external-code" attr.type="object">
        <data attr.name="Python" attr.type="string">abs.py:Abs_func</data>
    </data>
</node>
...
<edge source="sobel/AbsImpl" target="sobel/Abs" targetport="combinator" traits="Input">
```

This then enables our synthetizer to wrap the given functionality targeting ANSI-C into the 
missing final functions, for example,

```
typedef struct {int_16 resx; int_16 resy;} input_SDF_Comb_Abs_t;
typedef uint_8 output_SDF_Comb_Abs_t;

void execute_SDF_Comb_Abs(
        const input_SDF_Comb_Abs_t& input_SDF_Comb_Abs, 
        const output_SDF_Comb_Abs_t& output_SDF_Comb_Abs) {
    int_16 resx;
    int_16 resy;
    uint_8 imgOutput;
    resx = input_SDF_Comb_Abs.resx;
    resy = input_SDF_Comb_Abs.resy;
    // injected code
    imgOutput = (resx < 0 ? -resx : resx) 
              + (resy < 0 ? -resy : resy);
    output_SDF_Comb_Abs.imgOutput = imgOutput;
}
```

and similarly more information can be added for the read and write phases, which are ideally available after all optimization
and analysis passes have been completed.