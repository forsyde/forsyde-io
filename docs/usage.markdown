---
title: Usage
layout: default
permalink: {{ site.baseurl }}/usage/
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

# Java quickstart

This should apply reasonably well to other JVM languages as well, but only
plain Java (with 8+ syntax) has been tested.

We try to make the [ForSyDe IO Java](https://search.maven.org/artifact/io.github.forsyde/forsyde-io-java)
supporting library as up-to-date as possible with
the [upstream reference]({{ site.sources.forsydeio }}), but sometimes
it can take some weeks to catch up. 

## Maven projects

    <dependency>
      <groupId>io.github.forsyde</groupId>
      <artifactId>forsyde-io-java</artifactId>
      <version>{{ site.versions.forsydeio }}</version>
    </dependency>

## Gradle projects

    implementation 'io.github.forsyde:forsyde-io-java:{{ site.versions.forsydeio }}'
