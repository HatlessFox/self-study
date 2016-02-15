(defun mappend (f lst)
  (apply #'append (mapcar f lst)))

(defun cross-product (f l1 l2)
  (mappend #'(lambda (e2)
	       (mapcar #'(lambda (e1) (funcall f e1 e2)) l1))
	   l2))

(defun combine-all (l1 l2)
  (cross-product #'append l1 l2))

(format t "== Cross-product test ==~%")
(format t "~A~%" (cross-product #'+ '(1 2 3) '(10 20 30)))
(format t "~A~%" (cross-product #'list '(a b c d e f) '(1 2 3)))
(format t "== Combine-all test ==~%")
(format t "~A~%" (combine-all '((a) (b)) '((1) (2))))
