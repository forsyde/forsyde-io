---
title: Read and write models
layout: default
permalink: /usage/read-write
parent: Usage
has_children: false
nav_order: 2
---

This section quickly describes how to get a model in memory and later how to dump it. If you manage to compile
any such simple programs, we highly recommend you to read first the [concepts]({{site.baseurl}}/concepts) and then [type-safe vieweing]().
By doing so, you'll certainly avoid a good deal of frustration for not understanding how the underlying model,
and its design, works.

# Python

[ForSyDe IO Python](https://pypi.org/project/forsyde-io-python/)
can be added
in your `pip` powered project (or `poetry` powered project) directly.

This most likely translates to,

    pip install forsyde-io-python-core

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

# Java 

This should apply reasonably well to all JVM languages, but only
Java (8+) and Scala (3+) have been tested.

If you have a maven-powered project, then you can add to your dependencies section,

    <dependency>
      <groupId>io.github.forsyde</groupId>
      <artifactId>forsyde-io-java-core</artifactId>
      <version>LATEST</version>
    </dependency>

If you otherwise have a gradle-powered gradle project, you can add to your dependencies section,

    implementation 'io.github.forsyde:forsyde-io-java-core:LATEST'

Then, at any point in your code, you can load a model to memory and save it later by,

    ..
    import forsyde.io.java.core.ForSyDeModel;
    import forsyde.io.core.ModelHandler;
    ..
    
    ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();
    ForSyDeModel model = forSyDeModelHandler.loadModel("<in_file>");
    ..
    // analysize and modify the model
    ..
    ForSyDeModelHandler.writeModel(model, "<out_file>");


where `<in_file>` and `<out_file>` will be the files you intend to read and write,
respectively and `LATEST` does not fully exist: you have to replace it with the latest
version available, or one you wish to use.

Note the difference between Java nad Python: since Java forbids package/module-level functions,
there is the usual holder class `ForSyDeModelHandler` which contains all read and write functionality.
It must be instanstiated due to [modular design that enables integration with other modeling frameworks]({{site.baseurl}}/usage/read-write#adding-integrations).

Just like in Python, a ForSyDeModel is a 
[jgrapht DirectedPseudograph](https://jgrapht.org/javadoc-1.3.1/org/jgrapht/graph/DirectedPseudograph.html),
and therefore you can use any [analysis routine from this library](https://jgrapht.org/guide/UserOverview#graph-algorithms)!

# Adding integrations

Java is used as the example in this section for simplicity's sake, but you would do the same in Python.

From Maven central, you can notice the existance of modules other than `forsyde-io-java-core`: `forsyde-io-java-amalthea`, `forsyde-io-java-sdf3`...

These extra modules can be combined with `forsyde-io-java-core` to enable the reading and writing functions to support more
models and formats in a plugin fashion. Indeed, this is exactly what [_ConverSyDe_]({{site.baseurl}}/usage/conversyde) does.

The flow is as follows. Still asumming you are in maven or gradle-powered project, and that you want to use the AMALTHEA integration,
you'd add the extra module in your dependencies,

    <dependency>
      <groupId>io.github.forsyde</groupId>
      <artifactId>forsyde-io-java-amalthea</artifactId>
      <version>LATEST</version>
    </dependency>

or for gradle,

    implementation 'io.github.forsyde:forsyde-io-java-amalthea:LATEST'

And later register the downloaded module with your model handler,

    ..
    import forsyde.io.java.core.ForSyDeModel;
    import forsyde.io.core.ModelHandler;
    import forsyde.io.java.amalthea.drivers.AmaltheaDriver;
    ..

    ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();
    forSyDeModelHandler.registerDriver(new ForSyDeAmaltheaDriver());
    ForSyDeModel model = forSyDeModelHandler.loadModel("<in_file with amxmi extension>");
    ..
    // analysize and modify the model
    ..
    ForSyDeModelHandler.writeModel(model, "<out_file with amxmi extension>");

Naturally this means you are subject to the model-to-model from AMALTHEA to ForSyDe present in `forsyde-io-java-amalthea`.
This logic also applies to all other modules already present in the ForSyDe IO repo.