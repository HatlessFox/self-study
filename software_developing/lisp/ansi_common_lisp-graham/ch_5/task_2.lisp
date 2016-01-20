(defun mistery (x y)
  (cond ((null y) nil)
	((eql x (car y)) 0)
	(t (let ((z (mistery x (cdr y)))) (when z (+ 1 z))))))

;(mistery 1 '(a b c))
;(mistery 1 '(0 1 2))
	 
