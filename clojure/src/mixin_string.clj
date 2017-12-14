(ns mixin-string)

(defn filter-freq [s] (->> s
                           (remove #(clojure.string/blank? (str %)))
                           (remove #(not (Character/isLetter %)))
                           (remove #(Character/isUpperCase %))
                           (frequencies)
                           (filter #(> (second %) 1))
                           (into (sorted-map))))

(defn pre-mix [s1 s2]
  (let [[fs1 fs2] (map filter-freq [s1 s2])]
    (flatten (sort-by #(second (second %))
                      #(compare %2 %1)
                      (sort-by #(first (second %))
                               (for [[k v] (seq (merge-with #(cond (> %1 %2) [\1 %1]
                                                                   (= %1 %2) [\= %2]
                                                                   :else [\2 %2])
                                                            fs1 fs2))]
                                 (if (vector? v)
                                   [k v]
                                   (if (contains? fs1 k)
                                     [k [\1 v]]
                                     [k [\2 v]]))))))))

(defn mix [s1 s2]
  (let [tab (pre-mix s1 s2)]
    (if (empty? tab)
      ""
      (apply str (loop [c (first tab) b (second tab) n (nth tab 2) s "" t tab]
                   (if (empty? t)
                     (drop-last s)
                     (let [nt (nthnext t 3)]
                       (recur (first nt) (second nt) (nth nt 2) (concat s (str b ":") (repeat n c) "/") nt))))))))