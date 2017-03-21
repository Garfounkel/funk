#!/usr/bin/clisp

(defun sum_square (l)
  (reduce #'+ (mapcar (lambda (x) (* x x)) l)))


(print (sum_square '(1 2 3 4 5)))
