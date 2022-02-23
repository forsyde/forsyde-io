---
title: Usage
layout: default
permalink: /usage
has_children: true
nav_order: 2
---

ForSyDe IO is mainly intended to be used as a library and to be used by tools and tool developers.
 Both in conceptually and in supporting library implementations. 
ForSyDe IO is designed like this so that [different steps of the design flow]({{ site.projects.forsyde }}#our-vision) can share a common model. 
For the sake of convenience, there exists a thin CLI application built on top of ForSyDe IO for quick model-to-model transformations.
You can find this tool in the ForSyDe IO repos with the name [_ConverSyDe_]({{site.baseurl}}/usage/conversyde).


The supporting libraries should quick start you in:

1. Reading ForSyDe IO Models from disk to memory.
2. Saving ForSyDe IO Models from memory to disk.
3. Querying and manipulating them in memory for any analysis purposes.
4. Creating model-to-text and model-to-model tools.

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

