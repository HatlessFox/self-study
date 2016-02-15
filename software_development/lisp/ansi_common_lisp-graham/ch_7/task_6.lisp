;;; Buffer implementation

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

;;; Substitute

(defun file-subst (old new file1 file2)
  (with-open-file (in file1 :direction :input)
		  (with-open-file (out file2 :direction :output :if-exists :supersede)
				  (stream-subst old new in out))))

(defun char-match (c pattern)
  (cond ((eq pattern 'dig) (digit-char-p c))
	((eq pattern 'any) t)
	((eq pattern 'dig-let) (graphic-char-p c))
	((char= c pattern) t)
	(t nil)))

(defun stream-subst (old new in out)
  (let* ((pos 0) (len (length old)) (buf (new-buf len)) (from-buf nil)
	 (curr-old old))
    (do ((c (read-char in nil :eof)
	    (or (setf from-buf (buf-next buf))
		(read-char in nil :eof))))
	((eql c :eof))
	(cond ((char-match c (car curr-old))
	       (setf curr-old (cdr curr-old))
	       (cond ((null curr-old) (princ new out) (setf curr-old old) (buf-clear buf)) ; full match
		     ((not from-buf) (buf-insert c buf)))) ; add to buffer
	      ((eql curr-old old) (princ c out) (when from-buf (buf-pop buf) (buf-reset buf))) ; no match
	      (t (unless from-buf (buf-insert c buf)) ; match failed
		 (princ (buf-pop buf) out)
		 (buf-reset buf)
		 (setf curr-old old))))
    (buf-flush buf out)))
