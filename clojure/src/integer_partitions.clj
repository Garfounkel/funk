(ns integer-partitions
  (require [clojure.test :refer :all]
           [utils :refer :all]))

(declare enum)

(defn seq-enum [n i]
  (cond
    (= n i) (list (list i))
    (= 1 i) (list (doall (repeat n 1)))
    :else (for [sub-array (enum (- n i)) :when (or (= 1 (first sub-array)) (< 1 (count sub-array)))]
            (conj sub-array i))))

(defn enum [n]
  (loop [i n acc []]
    (if (< i 1)
      acc
      (recur (dec i) (concat acc (seq-enum n i))))))


(defn prod [n]
  (sort (distinct (map #(reduce * %) (enum n)))))

(defn part [n]
  (let [lst (prod n)
        cnt (count lst)
        diff (- (apply max lst) (apply min lst))
        average (float (/ (apply + lst) cnt))
        median (float (let [mid (/ cnt 2)]
                        (if (odd? cnt) (nth lst mid) (/ (+ (nth lst mid) (nth lst (dec mid))) 2.0))))]
    (format "Range: %s Average: %.2f Median: %.2f" diff average median)))


(time (part 21))


(testing "Enum:"
  (is (= (enum 1) [[1]]))
  (is (= (enum 2) [[2] [1 1]]))
  (is (= (enum 4) [[4] [3 1] [2 2] [2 1 1] [1 1 1 1]]))
  (is (= (enum 5) [[5] [4 1] [3 2] [3 1 1] [2 2 1] [2 1 1 1] [1 1 1 1 1]])))

(testing "Prod:"
  (is (= (prod 5) [1 2 3 4 5 6]))
  (is (= (prod 8) [1 2 3 4 5 6 7 8 9 10 12 15 16 18])))

(testing "Part:"
  (is (= (part 1) "Range: 0 Average: 1.00 Median: 1.00"))
  (is (= (part 2) "Range: 1 Average: 1.50 Median: 1.50"))
  (is (= (part 3) "Range: 2 Average: 2.00 Median: 2.00"))
  (is (= (part 4) "Range: 3 Average: 2.50 Median: 2.50"))
  (is (= (part 5) "Range: 5 Average: 3.50 Median: 3.50")))