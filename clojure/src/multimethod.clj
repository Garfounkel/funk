(ns multimethod
  (:use [clojure.pprint]))

(defmulti dispatch (fn [[h & t]] [h (count t)]))  ; multimethod definition
(defmethod dispatch [\# 6] [[_ & hex]] (println hex "is 6 char long '#' excluded"))
(defmethod dispatch [\# 3] [[_ & hex]] (println hex "is 4 char long '#' included"))
(defmethod dispatch :default [color] (println color "doesn't start by '#' or is neither 4 nor 7 char long"))

(dispatch "#123456")
(dispatch "#123")
(dispatch "LimeGreen")
