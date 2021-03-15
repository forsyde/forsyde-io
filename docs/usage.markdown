---
title: Usage
layout: default
permalink: /usage/
nav_order: 2
---

You can consume the supporting libraries code depending on your favorite language and
development environment.

# Minimal features

The libraries should quick start you in:

1. Reading a [ForSyDe IO Model on disk](/concepts/#textual-specification).
2. Saving a [ForSyDe IO Model on disk](/concepts/#textual-specification).
3. Manipulating them in memory for any analysis purposes.
4. Creating model-to-text and model-to-model tools based in the model.

# Python quickstart

[ForSyDe IO Python](https://pypi.org/project/forsyde-io-python/)
already exists and you can simply use it
in your `pip` powered project (or `poetry` powered project) directly.

This most likely translates to,

    pip install forsyde-io-python=={{ site.versions.forsydeio }}

and then declaring it as a required library in your project build tool.

Then, in your code, you can load the model to memory and later save it again by,

    ..
    from forsyde.io.python.api import load_model, write_model
    ..

    ..
    model_in = load_model(<in_file>)
    ..
    # analisys and modifications
    ..
    write_model(model_out, <out_file>)

where `<in_file>` and `<out_file>` will be the files you intend to read and write,
respectively. 

Note that a ForSyDeModel is a 
[networX MultiDiGraph](https://networkx.org/documentation/stable//reference/classes/multidigraph.html),
and therefore you can use any [analysis routine from this library](https://networkx.org/documentation/stable//reference/algorithms/index.html)!

# Java quickstart

This should apply reasonably well to other JVM languages as well, but only
plain Java (with 8+ syntax) has been tested.

We try to make the [ForSyDe IO Java](https://search.maven.org/artifact/io.github.forsyde/forsyde-io-java)
supporting library as up-to-date as possible with
the [upstream reference]({{ site.sources.forsydeio }}), but sometimes
it can take some weeks to catch up. 

If you have a maven-powered project, then you can add to your dependencies section,

    <dependency>
      <groupId>io.github.forsyde</groupId>
      <artifactId>forsyde-io-java</artifactId>
      <version>{{ site.versions.forsydeio }}</version>
    </dependency>

If you otherwise have a gradle-powered gradle project, you can add to your dependencies section,

    implementation 'io.github.forsyde:forsyde-io-java:{{ site.versions.forsydeio }}'

Then, at any point in your code, you can load a model to memory and save it later by,

    ..
    import forsyde.io.java.core.ForSyDeModel;
    import forsyde.io.java.drivers.ForSyDeModelHandler;
    ..
    
    ForSyDeModel model = ForSyDeModelHandler.loadModel("<in_file>");
    ..
    // analysize and modify the model
    ..
    ForSyDeModelHandler.writeModel(model, "<out_file>");


where `<in_file>` and `<out_file>` will be the files you intend to read and write,
respectively.

Note that a ForSyDeModel is a 
[jgrapht DirectedPseudograph](https://jgrapht.org/javadoc-1.3.1/org/jgrapht/graph/DirectedPseudograph.html),
and therefore you can use any [analysis routine from this library](https://jgrapht.org/guide/UserOverview#graph-algorithms)!
