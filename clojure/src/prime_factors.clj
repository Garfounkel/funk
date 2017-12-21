(ns prime-factors
  (require [utils]))

(defn prime-factors [n]
  (loop [start 2 n (Math/abs n) result []]
    (cond
      (> start (Math/sqrt n)) (if (= n 1) [] (conj result n))
      (zero? (mod n start)) (recur start (/ n start) (conj result start))
      :else (recur (inc start) n result))))

(utils/arg-prn prime-factors 315)
(utils/arg-prn prime-factors 24)


(defn add-value-to-keys [m ks v]
  (reduce #(update %1 %2 (fn [x] (+ (or x 0) v))) m ks))

(defn sum-of-divided [lst]
  (loop [[x & xs] lst acc {}]
    (if x
      (recur xs (add-value-to-keys acc (distinct (prime-factors x)) x))
      (sort-by first (into [] acc)))))

(utils/arg-prn sum-of-divided [12 15])
(utils/arg-prn sum-of-divided [15 30 -45])
