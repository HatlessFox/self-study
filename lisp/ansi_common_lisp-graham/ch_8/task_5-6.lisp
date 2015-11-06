(defparameter *words* (make-hash-table :size 100000))
(defparameter *words-bwd* (make-hash-table :size 100000))

(defconstant +maxword+ 100)

;; Read original text

(defun read-text (pathname)
  (with-open-file (strm pathname :direction :input)
		  (let ((buffer (make-string +maxword+)) (pos 0))
		    (do ((c (read-char strm nil :eof) (read-char strm nil :eof)))
			((eql c :eof))
			(if (or (alpha-char-p c) (char= c #\'))
			    (progn (setf (aref buffer pos) c) (incf pos))
			  (progn
			    (unless (zerop pos)
			      (add-word (intern (string-downcase (subseq buffer 0 pos))))
			      (setf pos 0))
			    (let ((p  (punc c)))
			      (if p (add-word c)))))))))

(defun punc (c) (case c (#\. '|.|) (#\, '|,|) (#\; '|;|) (#\! '|!|) (#\? '|?|)))

(defun add-word-gen (src dst table)
  (let ((pair (assoc src (gethash dst table))))
    (if (null pair)
	(push (cons src 1) (gethash dst table))
      (incf (cdr pair)))))

(let ((prev '|.|))
  (defun add-word (symb)
    (add-word-gen symb prev *words*)
    (add-word-gen prev symb *words-bwd*)
    (setf prev symb)))

;; Generate text from the original one

(defun generate-text (n &optional (prev '|.|))
  (if (zerop n)
      (terpri)
    (let ((next (random-next prev *words*)))
      (format t "~A " next)
      (generate-text (1- n) next))))

(defun random-next (prev table)
  (let* ((choices (gethash prev table))
	 (i (random (reduce #'+ choices :key #'cdr))))
    (dolist (pair choices)
      (if (minusp (decf i (cdr pair))) (return (car pair))))))

;; Verify generated text (task 5)

(defun find-non-blank (str &key (start 0))
  (unless (null start)
    (position-if #'(lambda (x) (char/= #\  x)) str :start start)))

(defun check-cite (str)
  (do* ((token-start 0 (find-non-blank str :start token-end))
	(token-end (position #\  str :start token-start)
		   (position #\  str :start token-start))
	(prev nil (intern token))
	(token (subseq str token-start token-end)
	       (subseq str token-start token-end)))
       ((null (find-non-blank str :start token-end)) t)
       (if (and prev (not (member (intern token) (gethash prev *words*)
				  :key #'car :test #'eql)))
		(return nil))))

;; Generate seq in the middle (task 6)

(defun generate-text-bwd (next &optional (acc nil))
  (if (and (< 0 (length (gethash next *words-bwd*))) (< (length acc) 10))
      (let ((prev (random-next next *words-bwd*)))
	(generate-text-bwd prev (cons prev acc)))
    (progn
      (dolist (e acc) (format t "~A " e))
      (length acc))))

(defun generate-next-middle (word)
  (let ((middle (intern word)))
    (generate-text (generate-text-bwd middle (list middle)))))
		    
