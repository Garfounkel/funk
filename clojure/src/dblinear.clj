(ns dblinear
  (:require [utils]
            [clojure.set :as set]))

; y = 2 * x + 1 and z = 3 * x + 1
; u = [1, 3, 4, 7, 9, 10, 13, 15, 19, 21, 22, 27, ...]
(defn dblinear-seq [n]
  (loop [sorted-u (sorted-set 1)
         i 0]
    (if (= i n)
      sorted-u
      (let [x (first sorted-u)
            xs (set/difference sorted-u #{x})
            y (inc (* 2 x))
            z (inc (* 3 x))]
        (recur (into xs #{y z}) (inc i))))))

(defn dblinear [n]
  (first (dblinear-seq n)))


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
(utils/with-timeout 2000 dblinear 2000)
(utils/with-timeout 3000 dblinear 6000)
(utils/with-timeout 3000 dblinear 60000)
