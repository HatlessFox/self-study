(defconstant +net+ '((a b c) (b c) (c d)))

;;; catch-throw

(defun shortest-path-ct (s e net)
  (catch 'result
    (bfs-ct e (list (list s)) net)))

(defun bfs-ct (e q net)
  (if (null q) nil
    (let* ((path (car q))
	   (node (car path)))
      (if (eql node e) (reverse path)
	(bfs-ct e (append (cdr q) (new-paths-ct path node net e)) net)))))


(defun new-paths-ct (path node net e)
  (mapcar #'(lambda (n)
	      (if (eq n e)
		  (throw 'result (reverse (cons n path)))
		(cons n path))) (cdr (assoc node net))))


;;(shortest-path-ct 'a 'd +net+)

;; w/o catch-throw

(defun new-paths (path node net e)
  (values nil (mapcar
	       #'(lambda (n)
		   (if (eq n e)
		       (return-from new-paths (values t (reverse (cons n path))))
		     (cons n path))) (cdr (assoc node net)))))

(defun shortest-path (s e net) (bfs e (list (list s)) net))

(defun bfs (e q net)
  (if (null q) nil
    (let* ((path (car q))
	   (node (car path)))
      (if (eql node e) (reverse path)
	(let* ((new-paths (multiple-value-list (new-paths path node net e)))
	       (found (car new-paths))
	       (paths (cadr new-paths)))
	  (if found paths
	    (bfs e (append (cdr q) paths) net)))))))
