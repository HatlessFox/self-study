(let (prev)
  (defun cmp-prev (x) ; better -- use prog1 
    (let ((res (if (or (null prev) (< x prev)) nil x)))
      (setf prev x)
      res)))
