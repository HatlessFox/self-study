(defun cp-comm (in-fname out-fname)
  (with-open-file (in-strm in-fname :direction :input)
		  (with-open-file (out-strm out-fname :direction :output
					    :if-exists :supersede)
				  (do ((line (read-line in-strm nil 'eof)
					     (read-line in-strm nil 'eof)))
				      ((eql line 'eof) nil)
				      (unless (char= #\% (char line 0))
					(format out-strm "~A~%" line))))))
