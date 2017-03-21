#!/usr/bin/clisp

; filter_list::(a -> Bool) -> [a] -> [a]

(defun filter_list (f list)
  (cond ((null list) nil)
        ((funcall f (car list))
         (cons (car list) (filter_list f (cdr list))))
        (t (filter_list f (cdr list)))))

;; V2 ;;
(defun filter_list (fct list)
  (if (null list)
      nil
    (let ((e (car list))
          (l (cdr list)))
      (if (funcall fct e)
          (cons e (filter_list fct l))
        (filter_list fct l)
        )
      )
    )
  )

(print (filter_list (lambda (x) (= (mod x 2) 0)) '(0 1 2 3 4 5 6 7 8 9)))
