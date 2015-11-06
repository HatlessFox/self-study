(defpackage "RING"
  (:use "COMMON-LISP")
  (:nicknames "RING")
  (:export "BUF-NEXT" "NEW-BUF" "BUF-INSERT"
	   "BUF-POP" "BUF-RESET" "BUF-CLEAR" "BUF-FLUSH"))

(in-package ring)

(defstruct buf vec (start -1) (used -1) (new -1) (end -1))

(defun bref (buf n) (svref (buf-vec buf) (mod n (length (buf-vec buf)))))
(defun (setf bref) (val buf n)
  (let ((v (buf-vec buf))) (setf (svref v (mod n (length v))) val)))
(defun new-buf (len) (make-buf :vec (make-array len)))
(defun buf-insert (x b) (setf (bref b (incf (buf-end b))) x))
(defun buf-pop (b)
  (prog1
      (bref b (incf (buf-start b)))
    (setf (buf-used b) (buf-start b) (buf-new b) (buf-end b))))

(defun buf-next (b)
  (when (< (buf-used b) (buf-new b))
    (bref b (incf (buf-used b)))))

(defun buf-reset (b) (setf (buf-used b) (buf-start b) (buf-new b) (buf-end b)))
(defun buf-clear (b) (setf (buf-used b) -1 (buf-start b) -1 (buf-new b) -1 (buf-end b) -1))

(defun buf-flush (b str)
  (do ((i (1+ (buf-used b)) (1+ i)))
      ((> i (buf-end b)))
      (princ (bref b i) str)))
