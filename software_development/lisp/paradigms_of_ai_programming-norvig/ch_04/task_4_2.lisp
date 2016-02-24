;;;; == 4.2 ==
;;;;
;;;; Write a function that generates all permutations of it input
;;;;

(defun permutations (lst)
  (if (= 0 (length lst)) '(())
      (mapcan #'(lambda (e)
		  (mapcar #'(lambda (p) (cons e p))
			  (permutations (remove e lst :count 1 :test #'eq))))
	      lst)))
