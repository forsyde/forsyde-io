edge(X, Y, 'constructor', 'constructed', 'ConstructedBy') :- edge(Y, X, 'constructed', 'constructor', 'Constructs').

vertexIsType(X, processT) :- vertex(X, processT).
vertexIsType(X, compoundProcessT) :- edge(X, Y, Z, Z, expandsT),
  vertexIsType(X, processT), 
  vertexIsType(Y, processT).

sdfActor(X) :- vertex(X, 'Process'), edge(Y, X, 'constructed', 'constructor', 'Constructs'), vertex(Y, 'SDFComb').
