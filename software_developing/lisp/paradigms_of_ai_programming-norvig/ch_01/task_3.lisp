(defun count-atoms (expr)
  (cond	
    ((atom expr) 1)
    (t (apply #'+ (mapcar #'count-atoms expr)))))

(format t "~{~a ~}~%"
	(mapcar #'count-atoms
		'(a                 ; 1
		  (a)               ; 1
		  (a b)             ; 2
		  (a (b))           ; 2
		  (a b (c d))       ; 4
		  (a nil b)         ; 3
		  (a () b)          ; 3
		  )))

