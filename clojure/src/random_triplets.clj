(ns random-triplets)

(defn build-set [c tri]
  (let [c-index (.indexOf tri (int c))]
    (if (> c-index -1)
      (set (nthrest tri (inc c-index)))
      nil)))

(defn followers-set [c triplets]
  (loop [tri (first triplets) xs (rest triplets) fol-set #{}]
    (if (empty? xs)
      (clojure.set/union fol-set (build-set c tri))
      (recur (first xs) (rest xs) (clojure.set/union fol-set (build-set c tri))))))

(defn map-followers [triplets]
  (loop [tri (first triplets) rest-tri (rest triplets) acc-map {}]
    (cond
      (and (empty? tri) (empty? rest-tri)) acc-map
      (empty? tri) (recur (first rest-tri) (rest rest-tri) acc-map)
      (contains? acc-map (first tri)) (recur (rest tri) rest-tri acc-map)
      :else (recur (rest tri) rest-tri (assoc acc-map (first tri) (followers-set (first tri) triplets))))))

(defn remove-key [followers key]
  (let [fol (dissoc followers key)]
    (zipmap (keys fol) (map #(disj % key) (vals fol)))))

(defn recover-secret [triplets]
  (let [followers (map-followers triplets)]
    (loop [fol followers acc []]
      (if (empty? fol)
        (apply str (reverse acc))
        (let [key (first (first (filter #(empty? (second %)) fol)))]
          (recur (remove-key fol key) (conj acc key)))))))
