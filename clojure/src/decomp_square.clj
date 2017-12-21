(ns decomp-square
  (require [utils :refer :all]))

(defn- square [x] (* x x))

(defn decompose [n]
  (let [target (square n)]
    (loop [acc [(dec n)] diff (- target (square (dec n)))]
      (let [[xacc & xsacc] acc candidate (int (Math/sqrt diff))]
        (cond (zero? diff) acc
              (and (empty? xsacc) (< xacc 5)) ()
              (= candidate xacc) (let [newacc (update (vec (filter #(> % 4) xsacc)) 0 dec)]
                                   (recur newacc (- target (apply + (map square newacc)))))
              :else (recur (cons candidate acc) (- diff (square candidate))))))))

(arg-prn decompose 625)                                             ; 1 3 5 8 49
(arg-prn decompose 463)                                             ; 2 3 5 7 43
(arg-prn decompose 50)                                              ; 2 5 8 34 624
(arg-prn decompose 44)                                              ; 5 30 462
(arg-prn decompose 11)
(arg-prn decompose 4)
(arg-prn decompose 3)
(arg-prn decompose 2)
(arg-prn decompose 1)