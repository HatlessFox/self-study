(defconstant +net+ '((a b c) (b c) (c d)))

(defun new-pathes (path node map)
  (let ((acc '()))
    (dolist (e (cdr (assoc node map)))
      (or (member e path)
	  (push (cons e path) acc)))
    acc))

(defun my-bfs (dst q map max-path)
  (if q
      (let* ((path (car q)) (curr (car path)))
	(my-bfs dst (append (cdr q) (new-pathes path curr map)) map
		(if (eql curr dst) (reverse path) max-path)))
    max-path))
	   
(defun longest (st dst map)
  (my-bfs dst (list (list st)) map nil))

(longest 'a 'd +net+)
