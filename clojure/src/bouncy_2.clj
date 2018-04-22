(ns bouncy-2
  (require [utils]))

;; Count non-bouncy number up to a power of 10
; Maths says that the answer is: Binomial(n + 10, 10) + Binomial(n + 9, 9) - 2 - 10n


(defn fact [n]
  (loop [x n acc 1]
    (if (zero? x)
      acc
      (recur (dec x) (*' x acc)))))

(defn binomial [n k]
  (/ (fact n) (*' (fact k) (fact (-' n k)))))

(defn total-inc-dec [x]
  (-' (+' (binomial (+' x 10) 10) (binomial (+' x 9) 9)) 1 (*' 10 x)))

(utils/arg-prn total-inc-dec 3)
(utils/arg-prn total-inc-dec 4)
(utils/arg-prn total-inc-dec 5)
(utils/arg-prn total-inc-dec 6)
(utils/arg-prn total-inc-dec 7)
(utils/arg-prn total-inc-dec 8)
(utils/arg-prn total-inc-dec 9)
(utils/arg-prn total-inc-dec 10)
(utils/arg-prn total-inc-dec 100)
(utils/arg-prn total-inc-dec 1000)
(utils/arg-prn total-inc-dec 1000000)
