#!/usr/bin/clisp

(defun my_take (n l)
       (if (or (null l) (= n 0)) nil
         (cons (car l) (my_take (- n 1) (cdr l)))))

(print (my_take 3 '(1 2 3 4 5 6 7)))
