---
layout: default
title: Index
isHome: true
---
Welcome to ForSyDe IO's documentation page! From here you can follow some links
to know about this [ForSyDe]({{ site.projects.forsyde }}) subproject and find some useful quick facts.

# Quick facts

ForSyDe IO has similar goals to what Intermediate Representations
do to conventional programming language compilation, except for model-driven engineering flows.
In particular, it has [ForSyDe]({{site.projects.forsyde}}) as the main target.

ForSyDe IO can be defined in three stacking levels. They are,
  1. A graph-based meta-model that accepts loops and multiple edges between nodes 
     and has notions of interfaces [[1](https://en.wikipedia.org/wiki/Multigraph), [2](https://www.merriam-webster.com/dictionary/pseudograph),
     [3](https://mathworld.wolfram.com/Pseudograph.html)],
  2. An exchange format to save and load this graph model in-memory,
  3. Supporting libraries implementing basic routines for a few languages.

# Quick links

1. If you wish to quickly transform between ForSyDe IO and other supported models, check [_ConverSyDe_]({{site.baseurl}}/usage/conversyde),
2. If you wish to read and write ForSyDe IO models within your tool, check [reading and writing]({{site.baseurl}}/usage/read-write),
3. If you wish to manipulate (or analyse) a ForSyDe IO model in a type-safe effective manner, check [type-safe vieweing]().
4. For more information about the model concept, specification and its implementation, check [Concepts]({{ site.baseurl }}/concept).



<!-- ### 3) I am both a developer and a designer and want to extend the models themselves!

Well-met! Then you can checkout [Concepts]({{ site.baseurl }}/concept) to see how the
supporting libraries are generated for different languages based on the underlying model and contribute there!

Unless you'd like to test out just reading to memory and dumping to disk.
This subproject is intended to be consumed by tools and tool "vendors", 
or tool developers, so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 


If you're using other [ForSyDe]({{ site.projects.forsyde }}) tools like [IDeSyDe]({{ site.projects.idesyde}}), you're already benefiting from ForSyDe IO! -->
