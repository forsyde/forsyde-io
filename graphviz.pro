writeBracket(L) :- write('{'), writeBracketI(L).
writeBracketI([H|L]) :- write(H), write(' '), writeBracketI(L).
writeBracketI([]) :- write('}').

vertexout(V, L) :- vertex(V, _), findall(E, edge(V, E, _, _, _), L).

drawvertex(V) :- vertex(V, _), write(V), write(';\n').
drawedge(S, V) :- edge(S, V, _, _, T), 
  write(S), 
  write(' -> '),
  write(V),
  write(' [label='),
  write(T),
  write('];\n').

drawoutedges(V) :- 
  vertex(V, _), 
  vertexout(V, L),
  write(V),
  write(' --> '),
  writeBracket(L),
  write(';\n').

printGraphviz :- 
  write('digraph {\n'), 
  findall(V, drawvertex(V), _), 
  findall(_, drawedge(_, _), _),
  write('}').

