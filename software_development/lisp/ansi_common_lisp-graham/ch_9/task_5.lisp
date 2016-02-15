(defun binsrch (f min max eps)
  (let ((prev nil))
    (do ((guess (float (+ min (/ (- max min) 2)))
		(float (+ min (/ (- max min) 2)))))
	((and (not (null prev)) (< (abs (- guess prev)) eps)) guess)
	(format t "~A ~A ~A~%" min guess max)
	(let ((guess-v (funcall f guess)))
	  (setf prev guess)
	  (cond ((= guess-v 0) (return guess))
		((< guess-v 0) (setf min guess))
		(t (setf max guess)))))))

;;(defun sqrt-3 (x)
;;
		 
			    
