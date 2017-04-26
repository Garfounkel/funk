#!/usr/bin/clisp

(defun remove-divider (i l)
  (delete i l :test(lambda (x i)
                     (= (mod i x) 0))
          )
  )
