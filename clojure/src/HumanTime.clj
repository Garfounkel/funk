(ns HumanTime)

(defn human-readable [x]
  (let [h (quot x 3600) reminder (rem x 3600)]
    (let [m (quot reminder 60) s (rem reminder 60)]
      (letfn [(add-zero [x] (if (< x 10) (str 0 x) (str x)))]
        (str (add-zero h) \: (add-zero m) \: (add-zero s))))))