;; a

(defun my-copy-list (l) (reduce #'cons l :initial-value nil :from-end t))

;; b

(defun my-reverse (l) (reduce #'(lambda (x y) (cons y x)) l :initial-value nil))

