;; Define parametric equations
;; (x, y) = (x1, y1) + q * ((x2, y2) - (x1, y1))
;; (x, y) = (x3, y3) + p * ((x4, y4) - (x3, y3))
;;
;; x3 + p * (x4 - x3) = x1 + q * (x2 - x1)
;; y3 + p * (y4 - y3) = y1 + q * (y2 - y1)
;;
;; p = (x1 - x3 + q * (x2 - x1)) / (x4 - x3)
;; (y3 - y1) + (x1 - x3) * (y4 - y3) / (x4 - x3) = q * ((y2 - y1) - (x2 - x1) * (y4 - y3) / (x4 - x3))
;;
;; if x4 - x3 != 0
;; (y3 - y1) * (x4 - x3) + (x1 - x3) * (y4 - y3) =q * ((y2 - y1) * (x4 - x3) - (x2 - x1) * (y4 - y3))

(defun intersectp (x1 y1 x2 y2 x3 y3 x4 y4)
  (if (= 0 (- x4 x3))
      (error "Unimplemented condition")
    (let* ((y21 (- y2 y1)) (y31 (- y3 y1)) (y43 (- y4 y3))
	   (x13 (- x1 x3)) (x21 (- x2 x1)) (x43 (- x4 x3))
	   (d (- (* y21 x43) (* y43 x21))))
      (if (/= d 0)
	(let* ((q (float (/ (+ (* y31 x43) (* y43 x13)) d)))
	       (p (float (+ x13 (/ (* q x21) x43)))))
	  (if (and (<= 0 q 1) (<= 0 p 1))
	      (values (+ x1 (* q x21)) (+ y1 (* p y21)))))))))
		 
;(intersectp 1 1 -1 -1 1 -1 -1 1)      
;(intersectp 1 1 .5 .5 0 0 -1 -1)      	 
