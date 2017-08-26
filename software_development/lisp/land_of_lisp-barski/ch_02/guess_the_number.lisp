(defparameter *SMALL* 0)
(defparameter *BIG* 0)

(defun guess-the-number ()
  (ash (+ *SMALL* *BIG*) -1))

(defun smaller ()
  (setf *BIG* (1- (guess-the-number)))
  (guess-the-number))

(defun bigger ()
  (setf *SMALL* (1+ (guess-the-number)))
  (guess-the-number))

(defun setup ()
  (defparameter *SMALL* 0)
  (defparameter *BIG* 100)
  (guess-the-number))
