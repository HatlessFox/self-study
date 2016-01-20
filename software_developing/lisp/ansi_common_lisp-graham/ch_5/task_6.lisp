;; a - iterative

(defun intersperse-itr (sep lst)
  (do ((l lst (cdr l))
	(acc '() (append (list (car l) sep) acc)))
       ((null l) (cdr (reverse acc)))))

;; b - recursive

(defun intersperse-rec (sep lst)
  (case (length lst)
	((0 1) lst)
	(t (append (list (car lst) '-)
		   (intersperse-rec sep (cdr lst))))))
