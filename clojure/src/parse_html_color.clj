(ns parse-html-color
  (:use [preset-colors])
  (:use [clojure.pprint]))


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

(pprint (parse-html-color "#80FFA0"))   ; => {:r 128 :g 255 :b 160}
(pprint (parse-html-color "#3B7"))      ; => {:r 51  :g 187 :b 119}
(pprint (parse-html-color "LimeGreen")) ; => {:r 50  :g 205 :b 50 }

