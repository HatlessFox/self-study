(defun occurrences (lst)
  (let ((occ '()))
    (dolist (e lst)
      (let ((entry (assoc e occ)))
	(if entry
	    (setf (cdr entry) (1+ (cdr entry))) ; better incf
	  (setf occ (cons (cons e 1) occ)))))   ; better push
    (sort occ #'(lambda (x y) (> (cdr x) (cdr y)))))) ; better :key

(occurrences '(a b a d a c d c a))
