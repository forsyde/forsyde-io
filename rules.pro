v(V, T) :- vertex(V, T).
e(Y, X, 'constructed', 'constructor', 'Constructs') :- edge(Y, X, 'constructed', 'constructor', 'Constructs').
e(X, Y, 'constructor', 'constructed', 'ConstructedBy') :- edge(Y, X, 'constructed', 'constructor', 'Constructs').

vertexIsType(X, processT) :- vertex(X, processT).
vertexIsType(X, compoundProcessT) :- e(X, Y, Z, Z, expandsT),
  vertexIsType(X, processT), 
  vertexIsType(Y, processT).

sdfActor(X) :- v(X, 'Process'), e(Y, X, 'constructed', 'constructor', 'Constructs'), v(Y, 'SDFComb').
