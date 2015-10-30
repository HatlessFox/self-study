(defconstant +net+ '((a b c) (b c) (c d)))

(defun new-paths (path node net)
  (mapcar #'(lambda (n) (cons n path)) (cdr (assoc node net))))

;;; catch-throw

(defun shortest-path-ct (s e net)
  (catch 'result
    (bfs-ct e (list (list s)) net)))

(defun bfs-ct (e q net)
  (if (null q) nil
    (let* ((path (car q))
	   (node (car path)))
      (if (eql node e) (throw 'result (reverse path))
	(bfs-ct e (append (cdr q) (new-paths path node net)) net)))))

;;(shortest-path-ct 'a 'd +net+)

;; w/o catch-throw

(defun shortest-path (s e net) (bfs e (list (list s)) net))

(defun bfs (e q net)
  (if (null q) nil
    (let* ((path (car q))
	   (node (car path)))
      (if (eql node e) (return-from bfs (reverse path))
	(bfs e (append (cdr q) (new-paths path node net)) net)))))
