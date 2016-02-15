(defstruct (bst (:print-function
		 (lambda (n s d)
		   (format s "(~A <~A> ~A)"
			   (bst-left n) (bst-key n) (bst-right n)))))
  key (left nil) (right nil))

(defun bst-insert (bst data)
  (if bst
      (let* ((key (bst-key bst))
	     (left (bst-left bst))
	     (right (bst-right bst)))
	(cond
	 ((= data key) bst)
	 ((< data key) (make-bst :key key :left (bst-insert left data) :right right))
	 (t (make-bst :key key :left left :right (bst-insert right data)))))
    (make-bst :key data)))

(defun bst-right-trav (bst)
  (and bst
       (let* ((key (bst-key bst))
	      (left (bst-right-trav (bst-left bst)))
	      (right (bst-right-trav (bst-right bst))))
	 (append right (list key) left))))
  
;(reduce #'bst-insert '(1 4 6 7 4 3 2) :initial-value nil)
