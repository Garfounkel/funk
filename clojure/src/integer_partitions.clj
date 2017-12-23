(ns integer-partitions
  (require [clojure.test :refer :all]
           [utils :refer :all]
           [clojure.math.combinatorics :as combo]))

;; My version of enum, plus a little memorization
;; twist which drastically improves performances:
(declare enum-memo)

(defn seq-enum [n i]
  (cond
    (= n i) (list (list i))
    (= 1 i) (list (doall (repeat n 1)))
    :else (filter #(>= (first %) (second %)) (for [sub-array (enum-memo (- n i))] (conj sub-array i)))))

(defn enum [n]
  (loop [i n acc []]
    (if (< i 1)
      acc
      (recur (dec i) (concat acc (seq-enum n i))))))

(def enum-memo (memoize enum))

;; Other version, using combinatorics:
; (defn enum [n]
;   (for [sub-array (combo/partitions (repeat n 1))]
;     (map #(reduce + %) sub-array)))

(defn prod [n]
  (sort (distinct (map #(reduce * %) (enum n)))))

(defn part [n]
  (let [lst (prod n)
        cnt (count lst)
        diff (- (apply max lst) (apply min lst))
        average (/ (apply + lst) cnt)
        median  (let [mid (/ cnt 2)]
                    (if (odd? cnt) (nth lst mid) (/ (+ (nth lst mid) (nth lst (dec mid))) 2.0)))]
    (format "Range: %s Average: %.2f Median: %.2f" diff (double average) (double median))))


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