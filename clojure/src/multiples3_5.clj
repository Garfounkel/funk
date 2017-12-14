(ns multiples3-5)

(defn solution [number]
  (loop [i 3 acc 0]
    (if (>= i number)
      acc
      (if (or (= 0 (mod i 3)) (= 0 (mod i 5)))
        (recur (inc i) (+ acc i))
        (recur (inc i) acc)))))