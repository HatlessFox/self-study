;; a - recursion

(defun check-subseq-r (lst)
  (cond ((null lst) t)
	((null (cdr lst)) t)
	((= (1+ (car lst)) (cadr lst)) (check-subseq-r (cdr lst)))
	(t nil)))

;; b - do

(defun check-subseq-do (lst)
  (do ((l (cdr lst) (cdr l))
       (c (car lst) (car l)))
      ((or (null l) (/= (1+ c) (car l)))
       (or (null l) (= (1+ c) (car l))))))

;; c - mapc & return

(defun check-subseq-mr (lst)
  (block nil
	 (mapc #'(lambda (x y) (if (/= (1+ x) y) (return nil)))
	       lst (cdr lst))
	 t))

	       
