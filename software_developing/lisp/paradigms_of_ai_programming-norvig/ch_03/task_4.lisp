(defun clever-list-print (lst &optional (list-tail nil))
  (cond ((atom lst) (princ lst))
	(t (let ((fst (first lst))
		 (rst (rest lst)))
	     (when (not list-tail)
	       (princ "("))
	     (clever-list-print fst nil)
	     (when (not (null rst))
	       (princ (if (atom rst) " . " " "))
	       (clever-list-print rst t))
	     (when (not list-tail)
	       (princ ")"))))))

(defun run-test (data)
  (clever-list-print data)
  (terpri))

(run-test nil)
(run-test 'a)
(run-test '(a b c))
(run-test '(a . b))
	     
		    
		    
