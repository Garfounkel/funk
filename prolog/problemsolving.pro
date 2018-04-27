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
