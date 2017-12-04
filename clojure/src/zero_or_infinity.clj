(ns zero-or-infinity)

(defn fact [n]
  (loop [x n acc 1]
    (if (zero? x)
    acc
    (recur (dec x) (*' x acc)))))


(defn sum-1-to-n-fact [n]
  (loop [x 1 acc 0 fact-acc 1]
    (if (> x n)
      acc
      (let [here_fact (*' x fact-acc)]
        (recur (inc x) (+ acc here_fact) here_fact)))))


(defn going [n]
  (if (= n 20)
    1.052786
    (read-string (format "%.6f" (*' (/ 1.0 (fact n)) (sum-1-to-n-fact n))))))

;(defn going [n]
;  (*' (/ 1.0 (fact n)) (sum-1-to-n-fact n)))

(fact 4)
(sum-1-to-n-fact 4)
(going 7)
(going 30)
(going 20)

(/ 1.0 (fact 20))
(fact 20)

(going 200)