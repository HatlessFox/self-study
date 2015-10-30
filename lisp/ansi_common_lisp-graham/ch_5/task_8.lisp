(defun min-max (v)
  (case (length v)
	(0 (values nil nil)) ; empty vector
	(1 (values (svref v 0) (svref v 0)))
	(t (let ((curr (svref v 0))
		 (res (multiple-value-list (min-max (subseq v 1)))))
	     (values (if (< curr (car res)) curr (car res))
		     (if (< curr (cadr res)) (cadr res) curr))))))

;(min-max #1(1))
