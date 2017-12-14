(ns pyramid-slide-down)

(defn longest-slide-down [pyramid]
  (-> pyramid
      reverse
      (#(reduce (fn [first_list second_list]
                  (loop [l first_list l-1 second_list acc []]
                    (if (empty? l-1)
                      (reverse acc)
                      (recur (rest l) (rest l-1) (cons (+ (first l-1) (max (first l) (second l))) acc)))))
                %1))
      first))

(print (longest-slide-down [[3] [7 4] [2 4 6] [8 5 9 3]]))
