(defstruct node
  yes
  no
  value)

(defparameter *db*
  (make-node
   :value 'ANIMAL
   :yes (make-node
	 :value 'MAMMAL)
   :no (make-node
	:value 'VEGETABLE)))

(defun game-round(&optional (node *db*))
  (cond ((null node)
	 (princ "I give up, what is the answer?")
	 (terpri)
	 (make-node :value (read)))
	(t
	 (format t "Is it ~A?~%" (node-value node))
	 (case (read)
	   ((n no)
	    (setf (node-no node) (game-round (node-no node)))
	    node)
	   ((y yes)
	    (setf (node-yes node) (game-round (node-yes node)))
	    node)
	   (it
	    (princ "I know it")
	    (terpri)
	    node)
	   (otherwise
	    (princ "Please answer yes, no or it")
	    (terpri)
	    (game-round node))))))

(dotimes (i 5)
  (game-round))
