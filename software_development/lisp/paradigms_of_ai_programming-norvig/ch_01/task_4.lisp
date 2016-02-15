;; NB: nil is both atom and list

(defun count-anywhere (sub-e e)
  (cond ((equal sub-e e) 1)
	((null e) 0)  ; null -> atom for compactness.
	((listp e)
	 (+ (count-anywhere sub-e (first e))
	    (count-anywhere sub-e (rest e))))
	(t 0)))

(let ((test-num 0))					   
  (defun run-test (sub-e e expected)
    (format t "~A: ~A~%" test-num
	    (if (= (count-anywhere sub-e e) expected) "OK" "FAIL"))
    (incf test-num)))

(run-test 'a '(a ((a) b) a) 3)
(run-test '(a b) '(a ((a b) b) (a b)) 2)
(run-test '() '(a ((a b) b) (a b)) 4)
