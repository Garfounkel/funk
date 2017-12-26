(ns utils
  (:require [clojure.string :as s]
            [clojure.repl :refer [demunge]]))

(def red "\u001B[31m")
(def green "\u001B[32m")
(def blue "\u001B[34m")
(def yellow "\u001B[33m")
(def reset "\u001B[m")

(defn fn-name [f]
  (as-> (str f) $
        (demunge $)
        (or (re-find #"(.+)--\d+@" $)
            (re-find #"(.+)@" $))
        (last $)))

(defn print-call-info [color-start color-at-semicol f-res f args]
  (printf "%s%s%s%s: %s%s\n" color-start (fn-name f) args color-at-semicol f-res reset)
  (flush))

(defn arg-prn [f & args]
  (print-call-info blue reset (apply f args) f args))

(defmacro timeout [ms & body]
  `(let [f# (future (do ~@body))
         v# (gensym)
         result# (deref f# ~ms v#)]
     (if (= v# result#)
       (do
         (future-cancel f#)
         nil)
       result#)))

(defn with-timeout [ms f & args]
  (let [start (. System (nanoTime))
        res (timeout ms (apply f args))
        elapsed (/ (double (- (. System (nanoTime)) start)) 1000000.0)]
     (if (nil? res)
       (print-call-info red "" (format "timed out after %s msecs" ms) f args)
       (print-call-info blue reset (format "%s%s (Elapsed time: %s msecs)" res yellow elapsed) f args))))


;(defn with-timeout [ms f & args]
;  (let [p (promise)
;        h (future
;            (deliver p (apply f args)))
;        t (future
;            (Thread/sleep ms)
;            (future-cancel h)
;            (deliver p nil))
;        start# (. System (nanoTime))
;        res @p
;        elapsed (/ (double (- (. System (nanoTime)) start#)) 1000000.0)]
;    (if (nil? res)
;      (print-call-info red "" (format "timed out after %s msecs" ms) f args)
;      (print-call-info blue reset (format "%s%s (Elapsed time: %s msecs)" res yellow elapsed) f args))))

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
