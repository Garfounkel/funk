parent(marge, lisa).
parent(marge, bart).
parent(marge, maggie).
parent(homer, lisa).
parent(homer, bart).
parent(homer, maggie).
parent(abraham, homer).
parent(abraham, herb).
parent(mona, homer).
parent(jackie, marge).
parent(clancy, marge).
parent(jackie, patty).
parent(clancy, patty).
parent(jackie, selma).
parent(clancy, selma).
parent(selma, ling).

female(mona).
female(jackie).
female(marge).
female(ann).
female(patty).
female(selma).
female(ling).
female(lisa).
female(maggie).
male(abraham).
male(herb).
male(homer).
male(bart).
male(clancy).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Monkey problem
action(state(middle, onbox, middle, not_holding), grab, state(middle, onbox, middle, holding)).
action(state(P, floor, P, T), climb, state(P, onbox, P, T)).
action(state(P1, floor, P1, T), push(P1, P2), state(P2, floor, P2, T)).
action(state(P1, floor, B, T), walk(P1, P2), state(P2, floor, B, T)).

success(state(_, _, _, holding), []).
success(State1, [Action | Plan]) :-
  action(State1, Action, State2),
  % write('Action : '), write(A), nl, write(' --> '), write(State2), nl,
  success(State2, Plan).

ape :-
  success(state(door,floor,window,not_holding), Plan),
  write(Plan).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Accumulation
mirror2(Left, Right) :-
    invert(Left, [ ], Right).
invert([X|L1], L2, L3) :-    % the list is ‘poured’
    invert(L1, [X|L2], L3).    % into the second argument
invert([ ], L, L).        % at the deepest level, the result L is merely copied

palindrome(L1) :- mirror2(L1, L1).

palindrome2(L1) :-
  pal(L1, []).
pal(L, L).
pal(L, [_|L]).
pal([X|L1], L2) :-
  pal(L1, [X|L2]).

%%%%%%%%%%%%%%%%%%%%%%%%%
%% Prolog’s memory
myprint :-
    retract(well_known(X)),
    !,  % avoids unwanted behahaviour if some predicate fails after a call to myprint1
    myprint,
    write(X),nl,
    asserta(well_known(X)).  % asserta = version of assert that adds item on top of the memory stack
myprint.

empty(X) :-
  retract(X), fail.
empty(_).

findany(Var, Pred, _) :-
  Pred, assert(found(Var)), fail.
findany(_, _, Results) :-
  assert(found(last)), collect_found(Results).

collect_found(L) :-
  retract(found(X)), !,
  (X == last, !, L = []; L = [X | Rest], collect_found( Rest)).

%--------------------------------
%       semantic networks
%--------------------------------
isa(bird, animal).
isa(albert, albatross).
isa(albatross, bird).
isa(kiwi, bird).
isa(willy, kiwi).
isa(crow, bird).
isa(ostrich, bird).

:- dynamic(locomotion/2).	% for tests

locomotion(bird, fly).
locomotion(kiwi, walk).
locomotion(X, Loc) :-
	isa(X, SuperX),
	locomotion(SuperX, Loc).

food(albatross,fish).
food(bird,grain).

/* drawback : n particular inheritance rules */
/* solution: one general predicate : "known" */

known(Fact) :-
	Fact,
	!.
known(Fact) :-
	Fact =.. [Rel, Arg1, Arg2],
	isa(Arg1, SuperArg1),
	SuperFact =.. [Rel, SuperArg1, Arg2],
	known(SuperFact).

habitat(Animal, continent) :-
  known(locomotion(Animal, L)),
  L \== fly,
  !.
habitat(_, unknown).

%%%%%%%%%%%%%%%%%%%%%%%
% Hanoi
hanoi :-
  % 1 is the smallest disk and 5 the largest
  move([5,4,3,2,1], a, c, b).

move(_, [ ], _, _, _).
move(D, [D1|Stack], Start, Goal, Interm) :-
  Dn is D + 1,
  move(Dn, Stack, Start, Interm, Goal),  % this is a (central) recursive call
  drawDepth(D),
  write('move disk '), write(D1), write(' from '), write(Start), write(' to '), write(Goal), nl,
  move(Dn, Stack, Interm, Goal, Start).  % yet another recursive call

drawDepth(0) :- !.
drawDepth(Depth) :-
  write(';- '),
  Depth1 is Depth -1,
  drawDepth(Depth1).
