(ns human-readable)

(def words {:y "year", :d "day", :h "hour", :m "minute", :s "second"})

(defn str-words [values]
  (let [amount-words (filter some? (map #(if-let [amount (if (zero? (% values)) nil (% values))]
                                           (str amount " " (words %) (if (> amount 1) "s" "")))
                                        (keys values)))]
    (apply str (clojure.string/join ", " (if (>= (count amount-words) 2)
                                           (flatten (list (doall (drop-last 2 amount-words))
                                                          (clojure.string/join " and " (take-last 2 amount-words))))
                                           amount-words)))))

(defn duration [secs]
  (if (zero? secs)
    "now"
    (let [_m (quot secs 60)
          _h (quot _m 60)
          _d (quot _h 24)
          y (quot _d 365)
          s (mod secs 60)
          m (mod _m 60)
          h (mod _h 24)
          d (mod _d 365)]
      (str-words {:y y, :d d, :h h, :m m, :s s}))))

; Cool looking recursion:
(loop [[x & xs] [1 2 3 4 5 6 7 8 9]
       results []]
  (if x
      (recur xs (conj results x))
      results))