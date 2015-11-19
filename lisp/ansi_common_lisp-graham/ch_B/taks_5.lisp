(defclass rectangle () (height width))
(defclass circle () ((radius :initarg :r)))

(defmethod area ((x rectangle))
  (* (slot-value x 'height) (slot-value x 'width)))
(defmethod area ((x circle))
  (* pi (expt (slot-value x 'radius) 2)))

(defvar *cnt* 0)
(defmethod area :before (x)
  (incf *cnt*))
