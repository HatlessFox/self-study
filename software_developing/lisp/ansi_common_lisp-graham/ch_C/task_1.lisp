(setf a '(a))

;; 1
(setf l1 (list a a a))

;; 2
(setf l2 (cons '(a) (list a a)))

;; 3
(setf l3 '((a) (a) (a)))
