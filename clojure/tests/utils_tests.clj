(ns utils-tests
  (:require [clojure.test :refer :all]
            [utils :refer :all]))


; Tests example
(defn- foo [x y] (+ y (* x x)))

(deftest fn-name-test
  (is (= "utils-tests/foo" (fn-name foo))))

(deftest arg-prn-test
  (is (= "\"utils-tests/foo(3 4): 13\"\r\n" (with-out-str (arg-prn foo 3 4)))))

(run-tests)