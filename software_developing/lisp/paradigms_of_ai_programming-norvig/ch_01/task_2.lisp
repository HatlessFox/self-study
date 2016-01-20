;; NB: can be implemented in log(N) time

(defun power (a x)
  "Computes a to the x power"
  (cond ((< x 0) (error "Power must be non-negative"))
	((= x 0) 1)
	(t (* a (power a (1- x))))))

(format t "~{~a~^, ~}"
	(mapcar #'power '(1 2 3 4) '(1 2 3 4)))
