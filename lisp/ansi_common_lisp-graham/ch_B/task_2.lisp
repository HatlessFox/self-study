(defclass point ()
  ((x :accessor pnt-x :initarg :x :initform 0)
   (y :accessor pnt-y :initarg :y :initform 0)
   (z :accessor pnt-z :initarg :z :initform 0)))

(defclass surface ()
  ((color :accessor srf-clr)))

(defclass sphere (surface)
  ((radius :accessor sph-rad :initarg :r)
   (center :accessor sph-cnt :initarg :c)))

(defun defsphere (x y z r c)
  (let ((s (make-instance 'sphere
			  :r r
			  :c (make-instance 'point :x x :y y :z z)
			  :color c)))
    s))

(defun unit-vector (a b c) nil) ;; stub

(defmethod normal ((s sphere) (p point))
  (let ((c (sph-cnt s)))
    (unit-vector (- (pnt-x c) (pnt-x p))
		 (- (pnt-y c) (pnt-y p))
		 (- (pnt-z c) (pnt-z p)))))

(defmethod intersect ((s sphere) (p point) xr yr zr) nil)
