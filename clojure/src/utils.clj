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
  (prn (str (fn-name f) args ": " (apply f args))))
