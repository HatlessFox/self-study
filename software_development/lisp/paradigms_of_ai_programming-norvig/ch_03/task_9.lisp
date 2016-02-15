(defun red-length (lst)
  (reduce #'(lambda (x _) (1+ x)) lst :initial-value 0))

(princ (red-length '()))
(terpri)
(princ (red-length '(1 0 5)))
(terpri)
(princ (red-length '(1 0 (3 1 2) 5)))
(terpri)
