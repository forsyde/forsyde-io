---
layout: page
---
Welcome to ForSyDe IO's documentation page! From here you can follow some links
to know about this [ForSyDe]({{ site.projects.forsyde }}) subproject and find some useful quick facts.

# Quick facts

## What is ForSyDe IO?

  1. A graph model that accepts loops and multiple edges between nodes 
     [[1](https://en.wikipedia.org/wiki/Multigraph), [2](https://www.merriam-webster.com/dictionary/pseudograph),
     [3](https://mathworld.wolfram.com/Pseudograph.html)],
  2. An exchange format to save and load this graph model in-memory,
  2. Supporting libraries implemting this save and load feature for a few languages.

For more information about the model concept and specification, check --.
For more information about the implemenations, check --.

## How do I use it?

Directly? You don't! (Likely)

Unless you'd like to test out just reading to memory and dumping to disk.
This subproject is intendend to be consumed by tools and tool "vendors", 
or tool developers, so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 

ForSyDe IO's has similar goals to what Intermediate Representations
do to conventional programming language compilation, except at system-level descriptions.

If you're using other [ForSyDe]({{ site.projects.forsyde }}) tools like [IDeSyDe]({{ site.projects.idesyde}}), you're already benefiting from ForSyDe IO!
