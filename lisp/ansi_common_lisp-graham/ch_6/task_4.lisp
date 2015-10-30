(defun most-two (fn lst)
  (cond ((null lst) (values nil nil))
	((null (cdr lst)) (values nil (car lst)))
	(t
	 (let* ((v1 (funcall fn (car lst)))
		(v2 (funcall fn (cadr lst)))
		(fst (max v1 v2))
		(snd (min v1 v2)))
	   (dolist (e (cddr lst))
	     (let ((v (funcall fn e)))
	       (cond ((< fst v) (setf snd fst fst v))
		     ((< snd v) (setf snd v)))))
	   (values fst snd)))))

;;(most-two #'identity '(1 2 3 4 5 -1 2 5))

