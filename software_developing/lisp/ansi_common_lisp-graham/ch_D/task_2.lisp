(declaim (optimize (speed 3) (safe 0)))

(defun foo (x) (if (zerop x) 0 (1+ (foo (1- x)))))
(defun foo-tr (x)
  (labels ((foo-acc (x acc)
		 (if (zerop x)
		     acc
		   (foo-acc (1- x) (1+ acc)))))
	  (foo-acc x 0)))
(defun self-foo (x acc)
  (if (zerop x) acc (self-foo (1- x) (1+ acc))))


;; There is no difference on CLISP
(time (foo 3000))
(time (foo-tr 3000))
(time (self-foo 3000 0))
