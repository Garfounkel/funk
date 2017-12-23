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


(defn naming []
  (println
    "Functions:
    \tf, g, h - function input
    \tn - integer input usually a size
    \tindex, i - integer index
    \tx, y - numbers
    \txs - sequence
    \tm - map
    \ts - string input
    \tre - regular expression
    \tcoll - a collection
    \tpred - a predicate closure
    \t& more - variadic input
    \txf - xform, a transducer\nMacros:
    \texpr - an expression
    \tbody - a macro body
    \tbinding - a macro binding vector"))
