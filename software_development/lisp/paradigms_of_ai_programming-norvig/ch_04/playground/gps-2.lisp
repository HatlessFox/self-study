; page 125+

;; Implementation

;; [Utils]

;; [Utils][Dbg]

(defvar *dbg-ids* nil "Identifiers used by dbg")

(defun dbg (id format-string &rest args)
  "Print debugging info if (DEBUF ID) has been specified."
  (when (member id *dbg-ids*)
    (fresh-line *debug-io*)
    (apply #'format *debug-io* format-string args)))

(defun endebug (&rest ids)
  "Start dbg output on the given ids."
  (setf *dbg-ids* (union ids *dbg-ids*)))

(defun undebug (&rest ids)
  "Stop dbg output on the given ids."
  (setf *dbg-ids* (if (null ids) nil
		      (set-differentce *dbg-ids* ids))))

(defun dbg-indent (id indent format-string &rest args)
  "Print indented debugging info if (DEBUG ID) has been specified."
  (when (member id *dbg-ids*)
    (fresh-line *debug-io*)
    (dotimes (i indent) (princ " " *debug-io*))
    (apply #'format *debug-io* format-string args)))

;; [Utils][Common]

(setf (symbol-function 'find-all-if) #'remove-if-not)

(defun find-all (item sequence &rest keyword-args
		 &key (test #'eql) test-not &allow-other-keys)
  "Find all those elements of sequence that match item,
   according to the keywords. Doesn't alter sequence"
  (if test-not
      (apply #'remove item sequence :test-not (complement test-not) keyword-args)
      (apply #'remove item sequence :test (complement test) keyword-args)))

(defun member-equal (item list)
  (member item list :test #'equal))

(defun starts-with (list x)
  "Is this a list whose first element is x?"
  (and (consp list) (eql (first list) x)))

;; [General Problem Solver]

;; [GPS][Operator Utils]

(defvar *ops* nil "A list of available operators.")

(defstruct op "An operation"
	   (action nil) (preconds nil) (add-list nil) (del-list nil))

(defun op (action &key preconds add-list del-list)
  "Make a new operator that obeys the (EXECUTING op) convention."
  (convert-op
   (make-op :action action :preconds preconds
	    :add-list add-list :del-list del-list)))

(defun executing-p (x)
  "Is x of the form: (executing ...) ?"
  (starts-with x 'executing))

(defun convert-op (op)
  "Make op conform to the (EXECUTING op) convention."
  (unless (some #'executing-p (op-add-list op))
    (push (list 'executing (op-action op)) (op-add-list op)))
  op)

(defun use (oplist)
  "Use oplist as the default list of operators."
  ;; Return something useful, but not too verbose:
  ;; the number of operators.
  (length (setf *ops* oplist)))

;; [GPS][Core]

(defun gps (state goals &optional (*ops* *ops*))
  "General Problem Solver: achieve all goals using *ops*."
  ;; cons :start to differentiate already reached (no action) and unreachable goal
  (find-all-if #'action-p (achieve-all (cons '(start) state) goals)))

(defun orderings (l)
  (if (< 1 (length l)) (list l (reverse l)) (list l)))

(defun action-p (x)
  "Is x something that is (start) or (executing ...)?"
  (or (equal x '(start)) (executing-p x)))

(defun achieve-each (state goals &optional (goal-stack nil))
  "Achieve each goal, tryng several orderings"
  (some #'(lambda (goals) (achieve-each state goals goal-stack)) (orderings goals)))

(defun achieve-all (state goals &optional (goal-stack nil))
  "Achieve each goal, and make sure they still hold at the end."
  (let ((current-state state))
    (if (and (every #'(lambda (g)
			(setf current-state
			      (achieve current-state g goal-stack)))
		    goals)
	     (subsetp goals current-state :test #'equal))
	current-state)))

(defun achieve (state goal goal-stack)
  "A goal is achieved if it already holds,
   or if there is an appropriate op for it that is applicable."
  (dbg-indent :gps (length goal-stack) "Goal: ~a" goal)
  (cond ((member-equal goal state) state)
	((member-equal goal goal-stack) nil) ; prevent looping
	(t (some #'(lambda (op) (apply-op state goal op goal-stack))
		 (appropriate-ops goal *ops*)))))

(defun appropriate-ops (goal state)
  "Return a list of appropriate operators.
   sorted by the number of unfulfilled preconditions."
  (sort (copy-list (find-all goal *ops* :test #'appropriate-p)) #'<
	:key #'(lambda (op)
		 (count-if #'(lambda (pr-cnd) (not (member-equal pr-cnd state)))
			   (op-preconds op)))))
  

(defun appropriate-p (goal op)
  "An op is appropriate to a goal if it is in its add-list."
  (member-equal goal (op-add-list op)))

(defun apply-op (state goal op goal-stack)
  "Return a new, transformed state if op is applicable."
  (dbg-indent :gps (length goal-stack) "Consider: ~a" (op-action op))
  (let ((state2 (achieve-all state (op-preconds op)
			     (cons goal goal-stack))))
    (unless (null state2)
      ;; Return an updated state
      (dbg-indent :gps (length goal-stack) "Action: ~a" (op-action op))
      (append (remove-if #'(lambda (x) (member-equal x (op-del-list op))) state2)
	      (op-add-list op)))))

;; Test

(make-op :action 'drive-son-to-school
	 :preconds '(son-at-home car-works)
	 :add-list '(son-at-school)
	 :del-list '(son-at-home))

(defparameter *school-ops*
  (list
   (make-op :action 'drive-son-to-school
	    :preconds '(son-at-home car-works)
	    :add-list '(son-at-school)
	    :del-list '(son-at-home))
   (make-op :action 'shop-installs-battery
	    :preconds '(car-needs-battery shop-knows-problem shop-has-money)
	    :add-list '(car-works))
   (make-op :action 'tell-shop-problem
	    :preconds '(in-communication-with-shop)
	    :add-list '(shop-knows-problem))
   (make-op :action 'telephone-shop
	    :preconds '(know-phone-number)
	    :add-list '(in-communication-with-shop))
   (make-op :action 'look-up-number
	    :preconds '(have-phone-book)
	    :add-list '(know-phone-number))
   (make-op :action 'give-shop-money
	    :preconds '(have-money)
	    :add-list '(shop-has-money)
	    :del-list '(have-money))))

(setf *school-ops* (mapc #'convert-op *school-ops*))

 ;;; Tests

(defun run-test1 ()
  (use *school-ops*)
  (gps '(son-at-home car-needs-battery have-money have-phone-book)
       '(son-at-school)))

(defun run-test2 ()
  (use *school-ops*)
  (gps '(son-at-home car-works)
       '(son-at-school)))

(defun run-test3 ()
  (use *school-ops*)
  (gps '(son-at-home car-needs-battery have-money have-phone-book)
       '(have-money son-at-school)))

(defun run-test4 ()
  (use *school-ops*)
  (gps '(son-at-home car-needs-battery have-money have-phone-book)
       '(son-at-school have-money)))

(defun run-test5 ()
  (use *school-ops*)
  (gps '(son-at-home car-needs-battery have-money)
       '(son-at-school)))

(defun run-test6 ()
  (use *school-ops*)
  (gps '(son-at-home) '(son-at-home)))


;; TODO: use macro or something
(defun run-tests()
  (print (run-test1))
  (print (run-test2))
  (print (run-test3))
  (print (run-test4))
  (print (run-test5))
  (print (run-test6)))

(run-tests)
