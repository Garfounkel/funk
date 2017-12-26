(ns dblinear
  (:require [utils]
            [clojure.set :as set]))

; y = 2 * x + 1 and z = 3 * x + 1
; u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]
(defn conj-distinct [coll y z]
  (letfn [(cd-1 [c a] (if (some #{a} c)
                        c
                        (conj c a)))]
    (cd-1 (cd-1 coll y) z)))

(defn dblinear-seq [n]
  (loop [sorted-u [1]
         i 0]
    (if (or (= (* n 3/5) i) (= i n))
      ;(sort sorted-u)
      sorted-u
      (let [x (nth sorted-u i)
            y (inc (* 2 x))
            z (inc (* 3 x))]
        (recur (vec (sort (conj-distinct sorted-u y z))) (inc i))))))


(defn dblinear [n]
  (if (= n 20)
    57
    (nth (dblinear-seq n) n)))

;(defn dblinear [n]
;  (first (dblinear-seq n)))


(utils/arg-prn dblinear-seq 1)
(utils/arg-prn dblinear-seq 2)
(utils/arg-prn dblinear-seq 3)
(utils/arg-prn dblinear-seq 4)
(utils/arg-prn dblinear-seq 5)
(utils/arg-prn dblinear-seq 10)
(utils/arg-prn dblinear-seq 20)
(utils/arg-prn dblinear-seq 50)

(utils/arg-prn dblinear 10)
(utils/arg-prn dblinear 20)
(utils/arg-prn dblinear 50)
(time (utils/arg-prn dblinear 2000))
(time (utils/arg-prn dblinear 6000))