(defun alist-to-hash (alist)
  (let ((hash (make-hash-table :size (length alist))))
    (dolist (e alist)
      (setf (gethash (car e) hash) (cdr e)))
  hash))

(defun hash-to-alist (hash)
  (let ((alist nil))
    (maphash #'(lambda (k v) (setf alist (cons (cons k v) alist))) hash)
  alist)) ; better in lambda - setf+cons -> push

;(alist-to-hash '((a . b)(c . d)))
(hash-to-alist (alist-to-hash '((a . b) (c . d))))
