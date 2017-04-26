#!/usr/bin/clisp

(defun l-length (l)
  (if (null l)
      0
    (+ 1 (l-length (cdr l)))
    )
  )

;;(print (l-length '(1 2 3 4 5 6)))

(defun print-tree (l)
  (if (typep l 'list)
      (if (not (null l))
          (progn (print-tree (car l)) (print-tree (cdr l))))
    (print l)
    )
  )

(print-tree '(((1 2) 3 (4 5)) (6 7)))
