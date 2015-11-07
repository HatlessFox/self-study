(defun gen-stat ()
  (labels ((num-of-ones (num)
			(if (= num 0) 0
			  (+ (mod num 2)
			     (num-of-ones (floor (/ num 2)))))))
	   (dotimes (i 10)
	     (format t "~A " (num-of-ones (random (expt 2 11)))))
	   (format t "~%"))))
	     
(defun fairp (lst)
  (= 5.0 (/ (reduce #'+ lst) (length lst))))
