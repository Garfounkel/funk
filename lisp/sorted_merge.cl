#!/usr/bin/clisp

(defun sorted_merge (l1 l2)
  (cond ((null l1) l2)
        ((null l2) l1)
        ((< (car l1) (car l2))
         (cons (car l1) (sorted_merge (cdr l1) l2)))
        (t (cons (car l2) (sorted_merge (cdr l2) l1)))))

(print (sorted_merge  '(0 2 4 6 8 10) '(1 3 5 7 9)))

(print (sorted_merge  '(0 1 2 3 4 5) '(6 7 8 9 10)))
