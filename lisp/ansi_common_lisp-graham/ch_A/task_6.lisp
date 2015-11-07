(defmacro auto-reset (&rest args)
  (let ((vars (reverse (cdr (reverse args))))
	(body (last args)))
    `(funcall #'(lambda ,vars ,@body) ,@vars)))
;; better - ((lambda ...
;;(let ((i 0)) (auto-reset i (incf i)) i)
