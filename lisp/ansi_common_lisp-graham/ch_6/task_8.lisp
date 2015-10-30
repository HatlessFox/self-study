(defun expensive (x)
  (format t "do expensive for ~A~%" x)
  x)

(let ((memo '())) ; better hash map or vector
  (defun frugal (x)
    (let ((res (car (assoc x memo))))
      (if res res
	(let ((v (expensive x)))
	  (push (cons x v) memo)
	  v)))))
      
  
