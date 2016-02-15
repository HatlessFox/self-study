(defun dot-product (l1 l2)
  (apply #'+ (mapcar #'* l1 l2)))

(format t "~A~%" (dot-product '(10 20) '(3 4)))
