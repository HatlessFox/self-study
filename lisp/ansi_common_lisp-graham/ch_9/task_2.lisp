(defun make-change (num)
  (labels ((div (a b) (/ (- a (mod a b)) b)))
	  (values (div num 25)
		  (div (mod num 25) 10)
		  (div (mod (mod num 25) 10) 5)
		  (mod num 5))))
;; may be better -- inner recursion over list on coints

;;(make-change 35)
	  
