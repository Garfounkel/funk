#!/usr/bin/clisp

(defun lambda_list (f l)
  (if (null l) nil
      (cons (funcall f (car l)) (lambda_list f (cdr l)))))

(print (lambda_list '1- '(0 2 3 -1 5 -3)))

(print (lambda_list '1- '(0 1 2 3 4 5)))
