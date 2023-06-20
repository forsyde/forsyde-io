---
title: Usage
layout: default
permalink: /usage
---

# Basic use for reading and manipulating ForSyDe models

This quick start focuses on the Java supporting library. 
Currently, this is the main supporting library of ForSyDe IO.

Topics covered in this tutorial:
  1. Reading ForSyDe IO Models from disk to memory.
  2. Saving ForSyDe IO Models from memory to disk.
  3. Querying and manipulating them in memory for any analysis purposes.


## Requirements

Regardless if you are checking this repository from a Linux or Windows OS, you need at least Java 11 to run the I-modules and E-modules.
On Linux you can use the distrubition's package manager, e.g. `apt-get` or `dnf`, to install the JVM system wide.
On Windows you can likely find official installer on all major JVM distributions like [Oracle](https://www.oracle.com/java/technologies/downloads/).

We recommend using a versioning installer like [jabba](https://github.com/shyiko/jabba) and installing the lastest stable JVM possible.
The modules were tested with [Amazon Coretto](https://aws.amazon.com/corretto/?filtered-posts.sort-by=item.additionalFields.createdDate&filtered-posts.sort-order=desc) and the [Graal VM](https://www.graalvm.org/).

You also need [Gradle](https://gradle.org/) installed as well.

## Setting up your tool project

After navigating to a folder you wish to start your tool or script, and issue

    gradle init

which ask you what type of project you wish to create and then create a handful of files in the directory that we will work with.
Ideally you should choose `application` as the most basic one, in case you do not fully know how to work with Maven/Gradle based JVM projects.
Among the questions asked, you can choose the default for most, except for the minimum java version, which should be 17.
Don't worry if this number seems high to you, it is the latest LTS after 11, and it will be supported for many years; it is just
that Java moves slowly but surely, specially its using companies.

You should have now a directory like

    │   .gitattributes
    │   .gitignore
    │   gradlew
    │   gradlew.bat
    │   settings.gradle
    │
    ├───.gradle
    │       file-system.probe
    │
    ├───app
    │   │   build.gradle
    │   │
    │   └───src
    │       ├───main
    │       │   ├───java
    │       │   │   └───temp
    │       │   │           App.java
    │       │   │
    │       │   └───resources
    │       └───test
    │           ├───java
    │           │   └───temp
    │           │           AppTest.java
    │           │
    │           └───resources
    └───gradle
        └───wrapper
                gradle-wrapper.jar
                gradle-wrapper.properties

The most import file for now is `build.gradle`, which declaritely defines the project's dependencies and also fetches them.
To enable and fetch ForSyDe IO in your new project, you need to add to the `dependencies` section of this file:

    dependencies {
        implementation 'io.github.forsyde:forsyde-io-java-core:0.7.0' // or newer
        implementation 'io.github.forsyde:forsyde-io-java-libforsyde:0.7.0' // or newer
    }

The first added dependency is actually independent of ForSyDe (as in, the umbrella project).
It provides the meta-modelling abstractions of system graphs and trait hierarchies as describe in [this academic paper](https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=9925666),
including classes, exporters, importers among others.
The second dependency adds the "ForSyDe Lib" trait hierarchy to the ClassPath so that it can be used for making sense of the ForSyDe umbrella project traits.
For example, it is within "ForSyDe Lib" that the trait `SDFActor` exists; otherwise, you can **still load the model successfully**, but `SDFActor` will only be an opaque trait.

Just for this tutorial, you can also download the small example `toy_sdf_tiny.fiodl` from the `forsyde-io/examples/sdf` folder (in the repo).
We will read this serialized model in memory and print its contents just to see it works.

Now, on your `main` method inside the `App` class (or equivalent in your bigger project), you can do the following:

    public static void main(String[] args) {
      var handler = new ModelHandler();
      handler.addTraitHierarchy(new ForSyDeHierarchy());
      var model = handler.loadModel("path/to/toy_sdf_tiny.fiodl");
      System.out.println(model.toString());
    }

This likely won't compile becuase loading models can throw exceptions, so you can add a `throws` exception to the main signature.
If this worked fine, you should get a big one line that is simply the string representation of the system graph in question.

    SystemGraph(...includes p1 and p2...)

You could naturally also write the model out again for fun and see how the "standard" identation of a `fiodl` file is:

    public static void main(String[] args) {
      var handler = new ModelHandler();
      handler.addTraitHierarchy(new ForSyDeHierarchy());
      var model = handler.loadModel("path/to/toy_sdf_tiny.fiodl");
      handler.writeModel(model, "itworksnice.fiodl");
    }

From which you should get a `itworksnice.fiodl` file in your executing folder.

Besides from managing to put a ForSyDe IO model in memory and later write it back to disk, this tutorial
show a key element of the tooling process: `ModelHandler`.
The `ModelHandler` is essentially the assigned API for reading and writing models from and to disk,
it automates things like model-to-model transformations, validations (if they exist) and transforming traits
from opaque to known entities. This is the reason you can find many `registerX` where `X` is a variety of things
in the `ModelHandler`. For example, if we would add the `java-core-bridge-sdf3` dependency to this project,
then the `SDF3Driver` becomes available, and we can spice up the model handler via,

    handler.registerDriver(new SDF3Driver());

which now enables the `loadModel` function to load [SDF3](https://www.es.ele.tue.nl/sdf3/manuals/xml/sdf/) files, i.e. `.sdf.xml` files! 
We shall not cover it in this tutorial, but enabling bridges to other MDE framework boils down to developing drivers
and adapters that can be registered in the `ModelHandler`, which then takes care of it automatically in any code that
is already using it.

## Programmatic creation, manipulation and querying

The previous example is nice to show how things can be used from a very broad perspective, but we are interested
in inspecting the elements a bit more closely and creating scripts that can process these model elements.
Here, there are two key elements for us to known and remember: `SystemGraph` and `ForSyDeHierarchy`.

`SystemGraph` is basically what some would call the "backbone" or the "database" of the entire operation.
You can create just like any normal class:

    var m = new SystemGraph();

from where you can create vertexes and edges contained in this graph,

    var v1 = m.newVertex("v1");
    var v2 = m.newVertex("IPreferLongIds");
    m.connect(v1, v2);

which creates two trait-less vertexex in `m` and connects them with a trait-less edge.
What we just did is good for educational purposes but useless from any system analysis perspective.
These two vertexes are simply two data components lying in the model without any indication of their purposes.
Now we will use the `ForSyDeHierarchy` to change that, by using layers of useful information within the vertexes and edges.

Let's say we want to create a Synchronous process in the system graph `m` that is neither `v1` or `v2` (and their respective IDs).
We could do the following:

    var newProc = ForSyDeHierarchy.SYComb.enforce(m, m.newVertex("syproc1"));
  
You might notice that `ForSyDeHierarchy` has many inner classes in it: they are not real classes, but shortcuts to the actual trait viewers
required to pull out this operation. That is to say, the line given is *not the only way to achieve this* but it is the recommended one.

We could also decide that `v1` is actually an `SDFActor` and also `enforce` that is becomes one:

    var v1Actor = ForSyDeHierarchy.SDFActor.enforce(m, v1);

Now `v1` is being acessed as if it was a `SDFActor` class! But in fact, we are "viewing" `v1` under the lenses of the `SDFActor` trait.
We could for example now change the production and consumption rates of `v1` via the `v1Actor` viewer; to Java, we are simply manipulating
an `SDFActor` object,

    v1Actor.addPorts("in", "out");
    v1Actor.production().put("out", 1);
    v1Avtor.consumption().put("in", 2);

and we could save this again to check out the final product,

    modelHandler.writeModel("created_in_memory.fiodl");

if would want to query for vertexes of a certain ID, or find all vertexes that have a certain trait, we could do it with simple traversals and `tryView` methods,

    var query1 = m.queryVertex("IdontExist");
    System.out.println(query1.toString());
    var query2 = m.queryVertex("v1"); // I do exist :D
    System.out.println(query2.toString());
    // get all SDF actors
    for (var v : m.vertexSet()) {
      ForSyDeHierarchy.SDFActor.tryView(m, v).ifPreset(a -> {
        System.out.println(a.toString());
      })
    }

note that most of the methods coming from ForSyDe IO are made to work out fine with Java 8+ Streams, which enable you to build near-declarative code at times,
specially when dealing with collection filtering and creation. 
An important final word of wisdom where is the **cross-cutting** part of ForSyDe IO that might not have been evident in the foregoing tutorial.
A vertex can have *many* traits that define cross-cutting concerns on that vertex; let's say, for example, that we will also add a `Visualizable` trait, which
tell us that `v1` should appear in a visualization framework later on:

    ForSyDeHierarchy.Visualizable.enforce(m, v1) // if you don't want the resulting viewer
    var v1visu = ForSyDeHierarchy.Visualizable.enforce(m, v1) // if you want it
    var v1visu2 = ForSyDeHierarchy.Visualizable.enforce(v1Actor) // if you provide a viewer, you don't need to tell which system graph is being viewed.

You can then check that `v1visu` and `v1visu2` are in fact viewing the same underlying vertex!

<!-- Here is a quick listing of the libraries and the formats which they support, even if partial. -->

<!-- <table>
<thead>
  <tr>
    <th>Programming<br>Language</th>
    <th>Latest version<br><a href="https://github.com/forsyde/forsyde-io/releases"><img src="https://badgen.net/github/release/forsyde/forsyde-io?icon=github"></a></th>
    <th>ForSyDeML<br>(.forxml)</th>
    <th>GraphML<br>(.graphml)</th>
    <th>Dot<br>(.dot, .gv)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Python</td>
    <td><a href="https://pypi.org/project/forsyde-io-python/"><img src="https://badgen.net/pypi/v/forsyde-io-python?icon=pypi"/></a>
    </td>
    <td>R/W</td>
    <td>W</td>
    <td>W*</td>
  </tr>
  <tr>
    <td>Java</td>
    <td><a href="https://search.maven.org/artifact/io.github.forsyde/forsyde-io-java"><img src="https://badgen.net/maven/v/maven-central/io.github.forsyde/forsyde-io-java?icon=maven"></a>
    </td>
    <td>R/W</td>
    <td>W</td>
    <td></td>
  </tr>
  <tr>
    <td>Julia</td>
    <td></td>
    <td>R-</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>Haskell</td>
    <td></td>
    <td>R-</td>
    <td></td>
    <td></td>
  </tr>
</tbody>
<caption>Overview table of supporting formats for each supporting library.
<br>Legend: R is "Read", W is "Write", * means it is not a trivial translation and - means it is partial implementation.
</caption>
</table> -->

