#!/usr/bin/clisp

(print '(1 2 3))

(print '((1 2) (3 4)))

(defun my-list-length (l)
       (if (null l) 0
         (1+ (my-list-length (cdr l)))))

(print (my-list-length '(1 2 3 4)))
