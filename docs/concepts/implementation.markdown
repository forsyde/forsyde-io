---
title: Implementations
layout: default
permalink: /concept/implementations
nav_order: 3
parent: Concepts
---

# Supporting libraries

The concepts enables some nice streamlining for the parser and writer libraries
pertaining the model. The following image showcases the idea visually.

<img src="{{ site.baseurl }}/assets/images/svg/forsydeio-impl.svg" />

All core graph sub-libraries are hand-coded to use relevant graphing
libraries available at the implementation language (e.g. NetworkX for Python
or jgrapht for Java). Note that relevant does not imply the most performant for
huge graphs!

We also have a meta model for our "types" or "kinds" of vertexes, edges etc, which
are injected into the soon-to-be library so that it gets realised with the graphing
features _and_ the types that a ForSyDe IO model contains.

