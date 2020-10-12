% compound processes
vertex('sobel', processT).
% constructed processes
vertex(getPx, processT).
vertex(gx, processT).
vertex(gy, processT).
vertex(abs, processT).
% constructors
vertex(getPxC, mapSDF).
vertex(gxC, mapSDF).
vertex(gyC, mapSDF).
vertex(absC, mapSDF).
% ports definitions
port(getPxC, combinator, functionT).
port(getPxC, constructed, processT).
port(gxC, combinator, functionT).
port(gxC, constructed, processT).
port(gyC, combinator, functionT).
port(gyC, constructed, processT).
port(absC, combinator, functionT).
port(absC, constructed, processT).

port(getPx, input, sobelInT).
port(getPx, gxOut, numeric_6).
port(getPx, gyOut, numeric_6).
port(getPx, constructor, vertexT).

port(gx, constructor, vertexT).

port(gy, constructor, vertexT).

port(abs, constructor, vertexT).
port(abs, output, sobelOutT).

port('sobel', input, sobelInT).
port('sobel', output, sobelOutT).

edge('sobel', getPx, input, input, expandsT).
edge('sobel', abs, output, output, expandsT).
edge(getPxC, getPx, constructed, constructor, constructsT).
edge(gxC, gx, constructed, constructor, constructsT).
edge(gyC, gy, constructed, constructor, constructsT).
edge(absC, abs, constructed, constructor, constructsT).

edge(X, Y, constructor, constructed, constructedBy) :- edge(Y, X, constructed, constructor, constructsT).

vertexIsType(X, processT) :- vertex(X, processT).
vertexIsType(X, compoundProcessT) :- edge(X, Y, Z, Z, expandsT),
  vertexIsType(X, processT), 
  vertexIsType(Y, processT).

sdfActor(X) :- vertex(X, processT), edge(Y, X, constructed, constructor, constructsT), vertex(Y, mapSDF).
