(ns utils-tests
  (:require [clojure.test :refer :all]
            [utils :refer :all]))


; Tests example
(defn- foo [x y] (+ y (* x x)))

(deftest fn-name-test
  (is (= (fn-name foo) "utils-tests/foo")))

(deftest arg-prn-test
  (is (= (with-out-str (arg-prn foo 3 4)) "utils-tests/foo(3 4): 13\n")))

(run-tests)
