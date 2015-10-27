;;; a

(defun dots-rec (n)
  (if (< 0 n)
      (progn
	(format t ".")
	(dots-rec (- n 1)))
      (format t "~%") ))

(defun dots-itr (n)
  (do ((i 0 (+ i 1)))
      ((<= n i) (format t "~%"))
      (format t ".") ))

;;; b

(defun a-in-lst-rec (lst)
  (if lst
      (+ (if (eq (car lst) 'a) 1 0) (a-in-lst-rec (cdr lst)))
    0))

(defun a-in-lst-itr (lst)
  (let ((cnt 0))
    (dolist (e lst)
      (if (eq e 'a) (setf cnt (1+ cnt))))
    cnt))

;(a-in-lst-rec '(b c))
;(a-in-lst-rec '(a b c a d n a))
