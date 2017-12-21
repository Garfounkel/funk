(ns decomp-square)

(defn- square [x] (* x x))

(defn decompose [n]
  (let [target (square n)]
    (loop [acc [(dec n)] diff (- target (square (dec n)))]
      (let [[xacc & xsacc] acc candidate (int (Math/sqrt diff))]
        (cond (zero? diff) acc
              (= candidate xacc) (let [newacc (update (vec (filter #(> % 4) xsacc)) 0 dec)]
                                   (recur newacc (- target (apply + (map square newacc)))))
              :else (recur (cons candidate acc) (- diff (square candidate))))))))

(prn (decompose 50))                                        ; 1 3 5 8 49
(prn (decompose 44))                                        ; 2 3 5 7 43
(prn (decompose 625))                                       ; 2 5 8 34 624
(prn (decompose 463))                                       ; 5 30 462
