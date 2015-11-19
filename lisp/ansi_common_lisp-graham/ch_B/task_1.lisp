(defclass rectangle ()
  ((height :accessor rec-h
	   :initarg :h
	   :initform 1)
   (width  :accessor rec-w
	   :initarg :w
	   :initform 1)))

(defclass circle ()
  ((radius :accessor cir-rad
	   :initarg :r
	   :initform 1)))

(defmethod area ((x rectangle))
  (* (rec-h x) (rec-w x)))

(defmethod area ((x circle))
  (* pi (expt (cir-rad x) 2)))

(let ((r (make-instance 'rectangle :h 2 :w 3)))
  (area r))
