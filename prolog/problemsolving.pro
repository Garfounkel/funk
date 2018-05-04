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
