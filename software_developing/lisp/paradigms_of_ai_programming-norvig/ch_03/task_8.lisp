(defun find-all (item seq
		 &rest keyword-args &key (test #'eql) &allow-other-keys)
  (apply #'remove item seq
	 (append keyword-args (list :test (complement test)))))

(princ (find-all 5 '(1 2 3 6 5) :test #'<))
