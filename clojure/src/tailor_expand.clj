(ns tailor-expand
  (require [utils]
           [clojure.math.numeric-tower :as nt]))

;; exp(z) = Sum 0->k (z**k / k!) = 1 + z + z**2/2 + z**3/6
(defn fact [n]
  (reduce * (range 2 (inc n))))

(defn pow [n exp]
  (reduce * (repeat exp n)))

(defn num->digits [x]
  (map #(Character/digit ^char % 10) (str x)))


;(and (= x 1.25) (= nDigits 3)) [1289 384]
;(and (= x 10) (= nDigits 40)) [1239343290542858204293897268755807211243N 56266098342252742161373730812700463N]
;(and (= x 1.5) (= nDigits 10)) [1239343290542858204293897268755807211243N 56266098342252742161373730812700463N]

(defn expand [x nDigits]
  ;(prn [x nDigits])
  (loop [acc (inc x) k 2]
    (if (and (ratio? acc) (<= nDigits (count (num->digits (numerator acc)))))
      [(numerator acc) (denominator acc)]
      (recur (+ acc (/ (pow x k) (fact k))) (inc k)))))

(defn expand-float [x nDigits]
  ;(prn [x nDigits])
  (loop [acc (inc x) k 2]
    (if (and (ratio? acc) (<= nDigits (count (num->digits (numerator acc)))))
      [(numerator acc) (denominator acc)]
      (recur (+ acc (/ (float (pow x k)) (fact k))) (inc k)))))

;(utils/with-timeout 1000 expand 1 2)
;(utils/with-timeout 1000 expand 2 5)
;(utils/with-timeout 1000 expand 3 10)
;(utils/with-timeout 1000 expand 1.25 3)                     ; expected: [1289 384]
(utils/with-timeout 1000 expand-float 1.25 3)                     ; expected: [1289 384]
;(utils/with-timeout 1000 expand 10 40)
;(expand 1.25 3)                     ; expected: [1289 384]
