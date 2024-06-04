---
layout: default
title: {{ site.title }}
description: {{ site.description }}
isHome: true
---

Welcome to ForSyDe IO's documentation page.
Here you can find some quick links to help you navigate the documentation
and also to situate you about the tool and its methods.

* [ForSyDe IO for the general end-user: quick start](usage)
* [ForSyDe IO for the ForSyDe end-user: LibForSyDe quick start trait hierarchy catalog](usage/libforsyde-quick-start)
* [ForSyDe IO for the ForSyDe advanced end-user: LibForSyDe trait hierarchy catalog](usage/catalog)
* [ForSyDe IO: what it is and where it fits.](about)
* [ForSyDe IO and its meta-modelling for the general developer](extend)

The current status of the supporting libraries are:

|Programming language| Load SG* | Save SG* | Internal TH** generation | Internal TH** export | External TH** generation |
|--------------------|----------|----------|------------------------|--------------------|------------------------|
| Java (> 14) | [x] | [x] | [x] | [x] | [ ] |
| Rust | [x] | [x] | [ ] | [ ] | [x] |

* *SG: System graph
* **TH: Trait hierarchy

<!-- ### 3) I am both a developer and a designer and want to extend the models themselves!

Well-met! Then you can checkout [Concepts]({{ site.baseurl }}/concept) to see how the
supporting libraries are generated for different languages based on the underlying model and contribute there!

Unless you'd like to test out just reading to memory and dumping to disk.
This subproject is intended to be consumed by tools and tool "vendors", 
or tool developers, so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 

If you're using other [ForSyDe]({{ site.projects.forsyde }}) tools like [IDeSyDe]({{ site.projects.idesyde}}), you're already benefiting from ForSyDe IO! -->
