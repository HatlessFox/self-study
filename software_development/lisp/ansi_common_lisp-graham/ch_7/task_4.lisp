(defun pretty-flt-print (mtx)
  (let* ((mtx-sz (array-dimensions mtx))
	 (rows (car mtx-sz)) (cols (cadr mtx-sz)))
    (do ((i 0 (1+ i))) ; better - use dotimes
	((eq i rows))
	(do ((j 0 (1+ j)))
	    ((eq j cols))
	    (format t "~10,2F " (aref mtx i j)))
	(terpri))))

(setf test_4 #2A((1.235 2.35 4.7) (1.445 -3.31 4.65)))
