(defun has-list (lst)
  (and lst
       (or (listp (car lst))
	   (has-list (cdr lst))) ))
;(has-list '(a b c))
;(has-list '(a nil b))
