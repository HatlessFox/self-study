(setf *random-state* (make-random-state t))

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

(defparameter *math-expr*
  '((expr -> (arg op arg))
    (arg -> ( { expr } ) number)
    (number -> (digit number-end))
    (number-end -> () number)
    (digit -> 0 1 2 3 4 5 6 7 8 9)
    (op -> + - * %)))

(defparameter *yodish*
  '((sentence -> (union verb-phrase noun-phrase Noun))
    (union -> як чыво)
    (noun-phrase -> (Adj* Noun))
    (verb-phrase -> (Adverb Verb))
    (Article -> чыво)
    (Adj* -> Adj ())
    (Adj -> прыгожы смачны дзiкi вялiкi сiвы дужы)
    (Noun -> сняданак ложак дзiк)
    (Verb -> хацiш трэба)
    (Adverb -> хутка смачна)))

(defparameter *grammar* *yodish*)
(format t "~A~%" (generate 'sentence))

(defparameter *grammar* *math-expr*)
(format t "~A~%" (generate 'expr))

