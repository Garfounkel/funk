(ns counting-duplicates)

(defn duplicate-count [text]
  (->> text
       (clojure.string/lower-case)
       (frequencies)
       (vals)
       (filter #(> % 1))
       (count)))