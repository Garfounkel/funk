(ns vowel-indices)

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(defn vowel-indices [word]
  (loop [i 1 w (clojure.string/lower-case word) acc []]
    (cond
      (empty? w) acc
      (in? #{\a \e \i \o \u \y} (first w)) (recur (inc i) (rest w) (conj acc i))
      :else (recur (inc i) (rest w) acc))))


(println (vowel-indices "Mmmm"))
(println (vowel-indices "Super"))
(println (vowel-indices "Apple"))
(println (vowel-indices "YoMama"))
