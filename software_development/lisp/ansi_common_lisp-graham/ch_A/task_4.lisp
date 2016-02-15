(defmacro ntimes (n &rest body)
  (let ((rec (gensym)))
    `(labels ((,rec (n)
		   (unless (zerop n)
		     ,@body
		     (,rec (1- n)))))
	     (,rec ,n))))
