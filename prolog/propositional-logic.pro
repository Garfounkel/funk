:- op(140, fy, -).                                                                  % ‘fy’ means right-associative - ‘- -X’ means ‘- (-X)’
:- op(160, xfy, [and, or, imp, impinv, nand, nor, nimp, nimpinv, equiv, nequiv]).   % ‘xfy’ means that ‘X and Y and Z’ means ‘X and (Y and Z)’

is_true(V, X and Y) :- is_true(V,X), is_true(V,Y).
is_true(V, X or _) :- is_true(V,X).
is_true(V, _ or Y) :- is_true(V,Y).
is_true(V, -X) :- not(is_true(V, X)). % link with Prolog’s negation
is_true(V, X equiv Y) :- is_true(V, X and Y); is_true(V, -(X and Y)).
is_true(V, X imp Y) :- is_true(V, -X or Y).

is_true(v0, a).
