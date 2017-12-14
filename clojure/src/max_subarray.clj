(ns max-subarray)

(defn max-sequence [xs]
  (if (empty? xs)
    0
    (loop [l (rest xs) max_ending_before (first xs) max_so_far (first xs)]
      (if (empty? l)
        max_so_far
        (let [x (first l)]
          (let [max_ending_here (max x (+ x max_ending_before))]
            (recur (rest l) max_ending_here (max max_ending_here max_so_far))))))))

