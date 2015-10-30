;;; a - iterative version

(defun precedes-itr (c str)
  (let  ((acc nil))
    (do ((i (1- (length str)) (1- i)))
	((< i 1) acc)
	(when (eq c (char str i))
	  (setf acc (adjoin (char str (1- i)) acc)))))) ; remove-duplicates can be used

;;(precedes-itr #\a "abracadabra")

;;; b - recursive version

(defun precedes-rec (c str)
  (case (length str)
	((0 1) '())
	(otherwise
	 (let* ((fst (char str 0))
		(snd (char str 1)))
	   (if (eq c snd)
	       (adjoin fst (precedes-rec c (subseq str 2)))
	     (precedes-rec c (subseq str 1)))))))

(precedes-rec #\a "abracadabra")
