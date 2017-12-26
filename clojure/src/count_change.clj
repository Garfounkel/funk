(ns count-change
  (:require [utils]))

(defn count-change
  "Gives the number of ways to make change for some money given a set of coins"
  [money coins]
  (cond
    (zero? money)
    (or (empty? coins) (< money 0)) 0
    :else (+ (count-change (- money (first coins)) coins)
             (count-change money (rest coins)))))

(utils/arg-prn count-change 4 [1 2])
(utils/arg-prn count-change 10 [5 2 3])

; cc 4 [1 2] = 3
; +
;  -> cc 3 [1 2] = 2
;     +
;      -> cc 2 [1 2] = 2
;         +
;          -> cc 1 [1 2] = 1
;             +
;              -> cc 0 [1 2] = 1
;              -> cc 1 [2]   = 0
;                 +
;                  -> cc -1 [2] = 0
;                  -> cc 1 []   = 0
;          -> cc 2 [2]   = 1 (see below)
;      -> cc 3 [2]   = 0
;         +
;          -> cc 1 [2] = 0 (see before)
;          -> cc 3 []  = 0
;  -> cc 4 [2]   = 1
;     +
;      -> cc 2 [2] = 1
;         +
;          -> cc 0 [2] = 1
;          -> cc 2 []  = 0
;      -> cc 4 []  = 0
