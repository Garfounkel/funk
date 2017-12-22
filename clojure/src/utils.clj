(ns utils
  (:require [clojure.string :as s]
            [clojure.repl :refer [demunge]]))

(defn fn-name [f]
  (as-> (str f) $
        (demunge $)
        (or (re-find #"(.+)--\d+@" $)
            (re-find #"(.+)@" $))
        (last $)))

(defn arg-prn [f & args]
  (printf "%s%s: %s\n" (fn-name f) args (print-str (apply f args))))
