(setf a 'global-a)
(defvar *b* 'global-a)

(defun fn () *b*)

(let ((a 'local-a)
      (*b* 'local-b))
  (format t "~{~A~^, ~}"
	  (list a *b* (fn) (symbol-value 'a) (symbol-value '*b*))))
