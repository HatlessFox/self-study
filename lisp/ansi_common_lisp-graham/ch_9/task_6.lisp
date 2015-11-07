(defun gorner (x &rest coeff)
  (if (null coeff) 0
    (do ((c coeff (cdr c))
	 (sum 0 (* x (+ sum (car c))))) ; nb - add prev c
	((null (cdr c)) (+ sum (car c))))))

;;(gorner 1 1 2 3)
