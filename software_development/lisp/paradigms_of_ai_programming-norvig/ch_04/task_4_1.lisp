;;;; == 4.1 ==
;;;;
;;;; It is possible to implement dbg using a single call to format.
;;;; Can you figure out the format directive to do this?
;;;;

(defvar *dbg-ids* nil "Identifiers used by dbg")

(defun dbg (id indent format-string &rest args)
  "Print debugging info if (DEBUF ID) has been specified."
  (when (member id *dbg-ids*)
    (format *debug-io* "~&~v@t~?" indent format-string args)))


(setf *dbg-ids* (list :test))

(defun test () (dbg :test 2 "Hello ~A" "Casper"))

