;; a
(setf y '(5))
((lambda (x) (cons x x)) (car y))

;; b
(setf x '(1 2) z 3)

((lambda (w)
   ((lambda (y) (cons w y)) (+ w z)))
 (car x))
