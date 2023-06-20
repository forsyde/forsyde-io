---
title: About
layout: default
permalink: /about/
---

# About

## What is ForSyDe IO

ForSyDe IO can be defined in three stacking levels. They are,
  1. A graph-based meta-model that accepts loops and multiple edges between nodes 
     and has notions of interfaces [[1](https://en.wikipedia.org/wiki/Multigraph), [2](https://www.merriam-webster.com/dictionary/pseudograph),
     [3](https://mathworld.wolfram.com/Pseudograph.html)],
  2. An exchange format to save and load this graph model in-memory,
  3. Supporting libraries implementing basic routines for a few languages.

## Goals

ForSyDe IO has similar goals to what Intermediate Representations
do to conventional programming language compilation, except for model-driven engineering flows.
In particular, it has [ForSyDe]({{site.projects.forsyde}}) as the main target.

ForSyDe IO is mainly intended to be used as a library and to be used by tools and tool developers.
 Both in conceptually and in supporting library implementations. 
ForSyDe IO is designed like this so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 
For the sake of convenience, there exists a thin CLI application built on top of ForSyDe IO for quick model-to-model transformations.
You can find this tool in the ForSyDe IO repos with the name [_ConverSyDe_]({{site.baseurl}}/usage/conversyde).




