;;;; Code from section 5.2

;;; Constants

(defconstant +fail+ nil "Indicates pat-match failure")
(defconstant +no-bindings+ '((t . t))
  "Indicates pat-match success, with no variables.")

;;; Abstract away from assoc

(defun get-binding (var bindings)
  "Find a (variable . value) pair in a binding list."
  (assoc var bindings))

(defun binding-val (binding)
  "Get the value part of a single binding."
  (cdr binding))

(defun lookup (var bindings)
  "Fet the value part (for var) from a binding list."
  (binding-val (get-binding var bindings)))

(defun extend-bindings (var val bindings)
  "Add a (var . value) pair to a binding list"
  (cons (cons var val)
	(if (eq bindings +no-bindings+) nil bindings)))
  

;;; ----------

;;; Pattern matching

(defun variable-p (x)
  "Is x a variable (a symbol beginning with '?')?"
  (and (symbol p) (equal (char (symbol-name x) 0) #\?)))

(defun segment-pattern-p (pattern)
  "Is this a segment matching pattern: ((?* var) . pat)"
  (and (consp pattern)
       (starts-with (first pattern '?*))))
	      
(defun pat-match (pattern input &optional (bindings +no-bindings+))
  "Match pattern against input in the context of the bindings"
  (cond ((eq bindings +fail+) +fail+)
	((variable-p pattern) (match-variable pattern input bindings))
	((segment-pattern-p pattern)
	 (segment-match pattern input bindings))
	((eql pattern input) bindings)
	((and (consp pattern) (consp input))
	 (pat-match (rest pattern) (rest input)
		    (pat-match (first pattern) (first input) bindings)))
	(t +fail+)))

(defun match-variable (var input bindings)
  "Does VAR match input? Uses (or updates) and returns bindins"
  (let ((binding (get-binding var bindings)))
    (cond ((not binding) (extend-bindings var input bindings))
	  ((equal input (binding-val binding)) bindings)
	  (t +fail+))))

      
