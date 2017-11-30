(ns parse-html-color)

(def preset-colors)

(defn parse-6-digit [s]
  {:r (Integer/parseInt (subs s 1 3) 16)
   :g (Integer/parseInt (subs s 3 5) 16)
   :b (Integer/parseInt (subs s 5 7) 16)})

(defn parse-3-digit [s]
  (parse-6-digit (str "#" (nth s 1) (nth s 1) (nth s 2) (nth s 2) (nth s 3) (nth s 3))))

(defn parse-html-color [color]
  (cond
    (.startsWith color "#") (if (= 4 (count color))
                              (parse-3-digit color)
                              (parse-6-digit color))
    :else (parse-6-digit (get preset-colors (clojure.string/lower-case color)))))


;(defmulti parse-html-color (fn [[h & t]] [h (count t)]))
;(defmethod parse-html-color [\# 6] [[_ & hex]] (to-rgb hex))
;(defmethod parse-html-color [\# 3] [[_ & hex]] (to-rgb (interleave hex hex)))
;(defmethod parse-html-color :default [color] (parse-html-color (preset-colors (clojure.string/lower-case color))))
