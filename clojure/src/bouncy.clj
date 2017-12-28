(ns bouncy
  (require [utils]))

;;; Euler 112
;(defn num->digits
;
;  ([n] (num->digits n (list)))
;  ([n digits]
;    (if (< n 10)
;      (conj digits n)
;      (num->digits (quot n 10) (conj digits (mod n 10))))))
(def num->digits
  (let [mem-n->d (memoize
                   (fn [digits n]
                     (if (< n 10)
                       (conj digits n)
                       (recur (conj digits (mod n 10)) (quot n 10)))))]
    (partial mem-n->d (list))))

;(defn num->digits [n] n)

(defn bouncy? [n]
  (let [digits (num->digits n)]
    (and (not (apply <= digits)) (not (apply >= digits)))))

;;; Euler 113
(defn total-inc-dec [x]
  (let [x (reduce * (repeat x 10))]
    (loop [bouncy-count 0
           n x]
      (if (<= n 100)
        (- x bouncy-count)
        (recur (if (bouncy? n) (inc bouncy-count) bouncy-count) (dec n))))))

;(time (num->digits 123))
;(time (num->digits 123456789))

(utils/with-timeout 1000 total-inc-dec 0)
(utils/with-timeout 1000 total-inc-dec 1)
(utils/with-timeout 1000 total-inc-dec 2)
(utils/with-timeout 1000 total-inc-dec 3)
(utils/with-timeout 1000 total-inc-dec 4)
(utils/with-timeout 1000 total-inc-dec 5)
(utils/with-timeout 12000 total-inc-dec 6)
;(utils/with-timeout 5000 total-inc-dec 7)
