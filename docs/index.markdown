---
layout: default
title: Index
nav_order: 1
---
Welcome to ForSyDe IO's documentation page! From here you can follow some links
to know about this [ForSyDe]({{ site.projects.forsyde }}) subproject and find some useful quick facts.

# Quick facts

## What is ForSyDe IO?

  1. A graph model that accepts loops and multiple edges between nodes 
     [[1](https://en.wikipedia.org/wiki/Multigraph), [2](https://www.merriam-webster.com/dictionary/pseudograph),
     [3](https://mathworld.wolfram.com/Pseudograph.html)],
  2. An exchange format to save and load this graph model in-memory,
  2. Supporting libraries implementing this save and load feature for a few languages.

For more information about the model concept and specification, check [Concepts]({{ site.baseurl }}/concept).
For more information about the implementations, check [Implementation]({{ site.baseurl }}/implementation).

## What is the motivation behind it?

ForSyDe IO's has similar goals to what Intermediate Representations
do to conventional programming language compilation, except for model-based engineering flows.

In particular, it has [ForSyDe]({{site.projects.forsyde}}) as the main target.

## How do I use it?

It depends on who you are and what are your goals! Let's break it down into three main scenarios.

### 1) I am a designer and want to do a ForSyDe powered design!

Then you don't use this directly at all!

ForSyDe IO is intended to be used by tools and tool developers, 
so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 
If you're using other [ForSyDe]({{ site.projects.forsyde }}) tools 
like [IDeSyDe]({{ site.projects.idesyde }}), you're already benefiting from ForSyDe IO!

### 2) I am a developer and want to make ForSyDE powered tool!

Howdy! Then checkout [Usage]({{ site.baseurl }}/usage) and [Concepts]({{ site.baseurl }}/concept) so that you can see how to consume the models in your favorite environment!

### 3) I am both a developer and a designer and want to extend the models themselves!

Well-met! Then you can checkout [Concepts]({{ site.baseurl }}/concept) and [Implementation]({{ site.baseurl }}/implementation) to see how the
supporting libraries are generated for different languages based on the underlying model and contribute there!

Unless you'd like to test out just reading to memory and dumping to disk.
This subproject is intendend to be consumed by tools and tool "vendors", 
or tool developers, so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 


If you're using other [ForSyDe]({{ site.projects.forsyde }}) tools like [IDeSyDe]({{ site.projects.idesyde}}), you're already benefiting from ForSyDe IO!
