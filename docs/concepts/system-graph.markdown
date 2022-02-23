---
title: The system graph
layout: default
permalink: /concept/system-graph
nav_order: 1
parent: Concepts
---

<script type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.3/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>

The system graph is a [directed graph supporting loops and multiple edges between vertexes](https://en.wikipedia.org/wiki/Multigraph).

The graph is defined as the combination of a set of vertexes and a set of edges, as usual. 
Both vertexes and edges are defined next.

In terms of structure, a vertex is a combination of:
1. a unique identifier within the system graph,
2. a set of ports that are simple names,
3. a set of properties that are dictionaries of nested data, relevant _only_ to the vertex itself,
4. a set of trait identifiers that are simple names.
it is important to remark that the data contained in a vertex _does not_ refer
directly to any other vertex or edge in the system graph.

If the term "dictionary of nested data" is a bit abstract, just think that it each vertex has a JSON object attached to it.
This type of "graph with data" is usually called a [property graph](https://en.wikipedia.org/wiki/Graph_database#Labeled-property_graph) in
software parlance, and a [labelled graph](https://en.wikipedia.org/wiki/Graph_labeling) in mathematical formalism.

An edge is a combination of:
1. the unique identifiers of both the source and target vertexes,
2. mutually optional port identifiers, one for the source and one for the target vertexes,
3. a set of trait identifiers that are simple names.

To clarify point number 2, suppose there are two vertexes in the system graph, $$v_1$$ and $$v_2$$. Also suppose that they have ports $$p_1$$ and $$p_2$$, respectively.
The possible edges from $$v_1$$ to $$v_2$$, given a set of trait identifiers $$T$$, is, in tuple notation,
* $$(v_1, v_2, p_1, p_2, T)$$
* $$(v_1, v_2, \emptyset, p_2, T)$$
* $$(v_1, v_2, p_1, \emptyset, T)$$
* $$(v_1, v_2, \emptyset, \emptyset, T)$$

The port-to-vertex edge combinations are considered in order to support "more vertical" relations between vertexes. For example, it is natural to
think of a chain of functions writing to each others' ports; But, if one of these functions is composed of smaller functions, it would also make
sense for the parent function to have a "containment" relationship to its inner smaller functions. 

The traits were not discussed as they are the key to giving different aspects to the System graph. 
They are discussed in-depth in the [trait hierarchy section]().

