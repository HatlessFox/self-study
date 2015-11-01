(defun my-cat (fname)
  (with-open-file (stream fname :direction :input)
		  (do ((line (read-line stream nil 'eof)
			     (read-line stream nil 'eof))
		       (acc nil (cons line acc)))
		      ((eql line 'eof) (reverse acc)))))

