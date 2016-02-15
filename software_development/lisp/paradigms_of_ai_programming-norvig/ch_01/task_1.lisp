(defparameter *titles*
  '(Md Jr I II III)
  "A list of titles that can appear at the name's end")

(defun last-name (full-name)
  (let ((last-word (first (last full-name))))
    (if (member last-word *titles*)
	(last-name (reverse (rest (reverse full-name))))
	last-word)))

(format t "~A~%"
	(mapcar #'last-name
		'((Gregory House MD) (Pavel Romanov I) (Robert Downey Jr))))
  
