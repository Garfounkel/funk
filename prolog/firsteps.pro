%%%%%%%%%%%%%%%%%%%%%%%%%
%% Family
sibling(X,Y) :-
  parent(Z,X),
  parent(Z,Y),
  X \== Y.

uncle_aunt(X,Y) :-
  parent(Z,Y),
  sibling(X,Z).

aunt(X,Y) :-
  uncle_aunt(X, Y),
  female(X).

cousin(X,Y) :-
  uncle_aunt(Z, X),
  child(Y, Z).

father(X,Y) :-
  parent(X,Y),
  male(X).

halfsibling(X,Y) :-
  father(Z, X),
  father(Z, Y),
  mother(A, X),
  mother(B, Y),
  A \== B.
halfsibling(X,Y) :-
  father(A, X),
  father(B, Y),
  mother(Z, X),
  mother(Z, Y),
  A \== B.

%%%%%%%%%%%%%%%%%%%%%%%%%
%% lists1
even([]).
even([_,_|T]) :- even(T).

odd(X) :- not(even(X)).

last_elt(X, [X|[]]).
last_elt(X, [_|L]) :- last_elt(X, L).

attach([], L, L).
attach([H|T], L2, [H|RES]) :- attach(T, L2, RES).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% lists2
multiplex([A], [C], [[A,C]]).
multiplex([A|B], [C|D], [[A,C]|T]) :- multiplex(B, D, T).

% multiplexing an arbitrary number of lists
firsts([], [], []).
firsts([[F|L1]|Lists], [F|R], [L1|Remainders]) :-
  firsts(Lists, R, Remainders).

multiplex1([[]|_], []). % list lengths must be equal to (or larger than) the first one
multiplex1(Lists, [First|R]) :-
  firsts(Lists, First, Remainders),
multiplex1(Remainders, R).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Concatenation
assemble([], [], L, L).
assemble([], L2, L3, L) :- attach(L2, L3, L).
assemble([H|T], L2, L3, [H|RES]) :- assemble(T, L2, L3, RES).

% Idiomatic concatenation:
assemble1(L1, L2, L3, Res) :-
  attach(L1, L2, L4),
  attach(L4, L3, Res).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Sublists
sub_list_annexe([], _).
sub_list_annexe([X|T1], [X|T2]) :- sub_list_annexe(T1, T2).

sub_list([], _).
sub_list([X|T1], [X|T2]) :- sub_list_annexe(T1, T2).
sub_list(L, [_|T2]) :- sub_list(L, T2).

% Idiomatic sublists:
sub_list1(L1, L2) :-
  attach(_, L, L2),
  attach(L1, _, L).

% or:
sub_list2(L1, L2) :- assemble(_, L1, _, L2).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Perec
remove(_, [], []).
remove(E, [E|T], RES) :- !, remove(E, T, RES).
remove(E, [A|T], [A|RES]) :- remove(E, T, RES).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% PrologAndlists
duplicate([], []).
duplicate([H|T], [H,H|RES]) :- duplicate(T, RES).
