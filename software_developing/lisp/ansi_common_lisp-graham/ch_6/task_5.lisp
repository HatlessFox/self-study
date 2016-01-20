(defun filter (fn lst)
  (let ((acc nil))
    (dolist (e lst)
      (if (funcall fn e) (push e acc)))
    (reverse acc)))

(defun my-rm-if (fn lst)
  (filter #'(lambda (x) (not (funcall fn x))) lst))
