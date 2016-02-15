;; core funcs

(defun mappend (f lst)
  (apply #'append (mapcar f lst)))
(defun random-elt (lst)
  (elt lst (random (length lst))))

(defun rule-rhs (rule) (rest (rest rule)))
(defun rewrites (cat) (rule-rhs (assoc cat *grammar*)))

(defun generate (phrase)
  (let ((choices nil))
    (cond ((listp phrase) (mappend #'generate phrase))
	  ((setf choices (rewrites phrase))
	   (generate (random-elt choices)))
	  (t (list phrase)))))

(defparameter *grammar*
  '((sentence -> (noun-phrase verb-phrase))
    (noun-phrase -> (Article Noun))
    (verb-phrase -> (Verb noun-phrase))
    (Article -> the a)
    (Noun -> man ball woman table)
    (Verb -> hit took saw liked)))

(format t "~A~%" (generate 'sentence))
  
