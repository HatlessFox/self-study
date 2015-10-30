(defun leap? (y)
  (when (zerop (mod y 4))
    (or (zerop (mod y 400))
	(not (zerop (mod y 100))))))

(defun month-num (m y)
  (case m
	((2) (+ 28 (if (leap? y) 1 0)))
	((1 3 5 8 10 12) 31)
	((4 6 7 9 11) 30)))

(mapcar (lambda (x) (funcall #'month-num x 2000)) '(0 1 2 3 4 5 6 7 8 9 10 11 12 13))
