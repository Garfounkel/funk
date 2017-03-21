

(defun klist(l k)
       (cond ((null k) 0)
             ((== k 0) (car l)
             (t(klist (cdr l) (- k &))))))
