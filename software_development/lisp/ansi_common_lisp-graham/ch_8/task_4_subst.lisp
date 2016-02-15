(defpackage "FILE"
  (:use "COMMON-LISP" "RING")
  (:nicknames "FILE")
  (:export "FILE-SUBST"))

(in-package file)

(defun file-subst (old new file1 file2)
  (with-open-file (in file1 :direction :input)
		  (with-open-file (out file2 :direction :output :if-exists :supersede)
				  (stream-subst old new in out))))

(defun stream-subst (old new in out)
  (let* ((pos 0) (len (length old)) (buf (new-buf len)) (from-buf nil))
    (do ((c (read-char in nil :eof)
	    (or (setf from-buf (buf-next buf))
		(read-char in nil :eof))))
	((eql c :eof))
	(cond ((or (char= c (char old pos)) (char= #\+ (char old pos)))
	       (incf pos)
	       (cond ((= pos len) (princ new out) (setf pos 0) (buf-clear buf)) ; full match
		     ((not from-buf) (buf-insert c buf)))) ; add to buffer
	      ((zerop pos) (princ c out) (when from-buf (buf-pop buf) (buf-reset buf))) ; no match
	      (t (unless from-buf (buf-insert c buf)) ; match failed
		 (princ (buf-pop buf) out)
		 (buf-reset buf)
		 (setf pos 0))))
    (buf-flush buf out)))
 
