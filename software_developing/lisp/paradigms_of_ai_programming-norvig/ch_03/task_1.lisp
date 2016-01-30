(format t "~A~%" (let* ((x 6) (y (* x x))) (+ x y)))
(format t "~A~%" ((lambda (x) ((lambda (y) (+ x y)) (* x x))) 6))
