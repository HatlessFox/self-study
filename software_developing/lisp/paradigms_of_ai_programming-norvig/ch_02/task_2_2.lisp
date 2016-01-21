(defun mappend (f lst)
  (apply #'append (mapcar f lst)))
(defun random-elt (lst)
  (elt lst (random (length lst))))

(defun rule-rhs (rule) (rest (rest rule)))
(defun rewrites (cat) (rule-rhs (assoc cat *grammar*)))
(defun non-terminal-p (cat) (not (null (rewrites cat))))

(defun generate (phrase)
  (cond ((listp phrase) (mappend #'generate phrase))
	((non-terminal-p phrase)
	 (generate (random-elt (rewrites phrase))))
	(t (list phrase))))


(defparameter *grammar*
  '((sentence -> (noun-phrase verb-phrase))
    (noun-phrase -> (Article Noun))
    (verb-phrase -> (Verb noun-phrase))
    (Article -> the a)
    (Noun -> man ball woman table)
    (Verb -> hit took saw liked)))

(format t "~A~%" (generate 'sentence))
  
