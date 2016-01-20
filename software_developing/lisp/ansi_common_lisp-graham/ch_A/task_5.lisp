(defmacro n-of (n e)
  (let ((blank (gensym)))
    `(mapcar #'(lambda (blank) ,e) (make-list ,n))))

;;(let ((i 0) (n 4)) (n-of n (incf i)))
