(defun check-raising (l &optional (prev nil))
  (cond ((null l) t)
	((null prev) (check-raising (cdr l) (car l)))
	((<= prev (car l)) (check-raising (cdr l) (car l)))
	(t nil)))

;; better -- more elegant (not (apply #'> lst))

;;(check-raising (list -3.3 9.2 10.3 11.4))
;;(check-raising (list -31 2 22 3))
