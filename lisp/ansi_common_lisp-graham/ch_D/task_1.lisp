(declaim (notinline pl))
(defun pl (x y) (+ x y))
(defun pl-twice (x y) (pl (pl x y) (pl x y)))
(disassemble #'pl-twice)

(declaim (inline pl))
(defun pl (x y) (+ x y))
(defun pl-twice (x y) (pl (pl x y) (pl x y)))
(disassemble #'pl-twice)
