(defstruct (node-3
	    (:print-function
	     (lambda (n s d)
	       (format s "(<~A> ~A ~A ~A)"
		       (node-3-payload n) (node-3-l n) (node-3-m n) (node-3-r n)))))
  payload (l nil) (m nil) (r nil))

;; a

(defun node-3-copy (tree)
  (if tree
       (make-node-3 :payload (node-3-payload tree)
		    :l (node-3-copy (node-3-l tree))
		    :m (node-3-copy (node-3-m tree))
		    :r (node-3-copy (node-3-r tree)) )))
;; b

(defun node-3-srch (tree key)
  (if tree
      (or (eql (node-3-payload tree) key)
	  (node-3-srch (node-3-l tree) key)
	  (node-3-srch (node-3-m tree) key)
	  (node-3-srch (node-3-r tree) key))))
	  

;(setf test (make-node-3 :payload 'a
;	     :l (make-node-3 :payload 'b)
;	     :m (make-node-3 :payload 'c
;			     :l (make-node-3 :payload 'd)
;			     :r (make-node-3 :payload 'e))))
