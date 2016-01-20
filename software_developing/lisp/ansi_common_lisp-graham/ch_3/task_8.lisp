;; NB: nested lists are not supported
(defun showdots (lst)
  (if lst
      (progn
	(format t "(~A. " (car lst))
	(showdots (cdr lst))
	(format t ")"))
    (format t " NIL")))
