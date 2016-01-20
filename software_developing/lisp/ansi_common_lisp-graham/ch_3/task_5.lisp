;;; a

(defun rec-hlpr (lst i)
  (and lst (cons (+ i (car lst)) (rec-hlpr (cdr lst) (1+ i)))))

(defun pos+-rec (lst) (rec-hlpr lst 0))

;;; b

(defun pos+-itr (lst)
  (let ((acc '()) (i 0))
    (dolist (e lst)
      (push (+ e i) acc)
      (incf i))
    (reverse acc)))

;;; c

(defun range (lst)
  (let ((acc '()))
    (do ((l lst (cdr l))
	 (i 0 (1+ i)))
	((null l) (reverse acc))
	(push i acc))))
	 
(defun pos+-map (lst)
  (mapcar #'+ lst (range lst))) ; let, lamda and incf can be used

;(pos+-map '(7 5 1 4))
