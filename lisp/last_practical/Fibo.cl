#!/usr/bin/clisp

(defun fibo (n)
  (if (or (= n 0) (= n 1))
      1
    (+ (fibo (- n 1)) (fibo (- n 2)))
    )
  )

(print (fibo 5))
(print (fibo 6))
(print (fibo 7))
(print (fibo 8))
