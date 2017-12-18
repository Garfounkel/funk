(ns nim)

(defn zero-sum? [board]
  (= 0 (apply bit-xor board)))

(defn modify-board [board pile-index number-of-straws]
  (assoc board pile-index (- (nth board pile-index) number-of-straws)))

(defn choose-move
  "Picks a move to play given a game-state ISeq"
  [game-state]
  (loop [pile-index 0 number-of-straws 1]
    (cond
      (> pile-index (count game-state)) [0, 0]
      (zero-sum? (modify-board game-state pile-index number-of-straws)) [pile-index, number-of-straws]
      (>= number-of-straws (nth game-state pile-index)) (recur (inc pile-index) 1)
      :else (recur pile-index (inc number-of-straws)))))
