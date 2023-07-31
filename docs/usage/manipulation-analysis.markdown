---
title: Manipulation and analysis
layout: default
permalink: /usage/manipulation-analysis
parent: Usage
nav_order: 3
---

Java is used as the example in this section for simplicity’s sake, but you would do the same in Python, in case it is up-to-date.

## Direct system graph manipulation

[Once you have a system graph in memory]({{site.baseurl}}/usage/read-write), you could potentially access its properties directly and
make appropriate casts,

{% highlight java lineos %}

ForSyDeSystemGraph g = handler.load("model.forsyde.xmi");
// this code could NEP. But assume it does not for now. 
// it could also be an equivalent for-loop search ...
Vertex v1 = g.vertexSet().stream().filter(v -> v.getIdentifier().equals("vertex1")).findAny().get();
Integer intProp1 = (Integer) v1.getProperties().get("propertyIKnowToBeInt").unwrap();
v1.getProperties().put("propertyIKnowToBeInt", VertexProperty.create(intProp1 + 2));
handler.write("modified_model.forsyde.xmi");

{% endhighlight %}

This approach also requires you to handle the possible errors yourself, if they are not caught by
the standard model consistency checks. 

Such direct of the system graph is **not** recommended except in two cases. 

The first case is when the properties you want to save, read and manipulate in the system graph are _absolutely_ not covered by the trait hierarchy shipped with ForSyDe IO.
So you have to get around it via direct access until the corresponding trait is added to the trait hierarchy.

The second case is when you are expanding the features, validations and other internal routines that the ForSyDe IO provides. Indeed,
these conversions and castings are automatically generated when the trait hierarchy code is create and injected on top of the system graph code.
However, other pieces of additional code are also generated to ensure that the model is always consistent whenever it is successfully loaded in memory.

## Trait viewer manipulation

A more effective way to use the system graph with type guarantees is to make heavy use of [the viewers layer]({{site.baseurl}}/concept/trait-hierarchy#viewers).
If a viewer can successfully view a vertex, then you can use as if the vertex is just like any other classed object. 
Using the example before, suppose that atrait `HasInt` requires the vertex to have a property `propertyIKnowToBeInt` of integer type.
Then the loading and manipulation could would change to,


{% highlight java lineos %}

ForSyDeSystemGraph g = handler.load("model.forsyde.xmi");
hasInt v1WithInt = g.vertexSet().stream().filter(v -> v.getIdentifier().equals("vertex1"))
    .findAny().flatMap(v -> HasInt.safeCast(v)).get();
Integer intProp1 = v1WithInt.getPropertyIKnowToBeInt();
v1WithInt.setPropertyIKnowToBeInt(intProp1 + 2);
handler.write("modified_model.forsyde.xmi");

{% endhighlight %}

Some of the effort has been moved to the `safeCast` call, which checks if the vertex declares the appropriate
trait and returns the `HasInt` view of this vertex. From then on, the vertex can be treated from the viewer
with getters and setters as one expects for normal classes.

Every trait interface provides three major methods: `safeCast`, `conforms` and `enforce`.
`Trait.safeCast` returns a `Trait` viewer if the vertex declares (or refines) the `Trait`; otherwise and empty result is returned.
`Trait.conforms` returns true if the vertex declares (or refines) the `Trait`.
`Trait.enforce` returns a `Trait` viewer of the vertex by adding all required ports and initializing properties if they have default values
declared by `Trait`.

