---
title: Conversyde
layout: default
permalink: /usage/conversyde
parent: Usage
nav_order: 2
---

# Model transformations with _ConverSyDe_

_ConverSyDe_ is shipped as a Java "uberJar" that contains all non-standard java dependencies. This means
that all you need in order to use it is a Java > 8 runtime installed in your system. Both linux (debian, specifically) and
windows (10 or newer) have been tested with the jar.

First, simply download the jar from [ForSyDe IO's release pages]({{site.sources.forsydeio}}/releases). 
_ConverSyDe_ will likely be named either `conversyde-*.*.*-all.jar` or `conversyde-all-cli-*.*.*.jar`. 
Once it is downloaded, it is enough that you run the jar to launch it. Here's the an example in a Linux machine:

    > java -jar conversyde-*.*.*-all.jar 
    Usage: conversyde [-hV] [-o=<outputFiles>]... [<inputFiles>...]
    Perform conversions in the standard ForSyDe IO library.
          [<inputFiles>...]   Input model files to be converted.
      -h, --help              Show this help message and exit.
      -o, --output=<outputFiles>
                              Output models of the conversion.
      -V, --version           Print version information and exit.

As the help suggests, _ConverSyDe_ takes _n_ input models, merges them, and outputs the final transformed model to _m_ different outputs.

For example, you could pass it three different models (say, each representing an aspect of your system design), merge them and output
the final transformed model as a ForSyDe XMI serialized model, an AMALTHEA model and a visualizable Dot graph:

    > java -jar conversyde-*.*.*-all.jar \
        -o output_in_forsyde.forsyde.xmi \
        -o output_in_amalthea.amxmi \
        -o visuals.gv \
        application.forsyde.xmi \
        hardware.amxmi \
        constraints.forsyde.xmi

Until a more streamlined way is developed to update this documentation with all the models already supported
within the ForSyDe IO "standard" trait hierarchy and libraries, please refer to the [repository itself](https://github.com/forsyde/forsyde-io);
As a rule of thumb, every java module represents total or partial support for another model and its format. 
Say, the module `java-amalthea` indicates total or partial integration of AMALTHEA models and its formats with
ForSyDe IO.