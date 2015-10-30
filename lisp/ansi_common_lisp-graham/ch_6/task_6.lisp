(let ((max nil)) ;; better -- default nil is implicit
  (defun his-max(x)
    (if (or (null max) (< max x)) (setf max x))
    max))
