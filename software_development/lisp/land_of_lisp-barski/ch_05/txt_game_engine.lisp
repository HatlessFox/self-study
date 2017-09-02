;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Engine

;;;; World is stored as a directed graph
;;;;;; nodes - a list of (<name> (<description>))
;;;;;; enges - a list of (<src> (<dst> <dir> <obj>)+)

;;;;;; world description utils

(defun objects-at (loc objs obj-locs)
  (labels ((at-loc-p (obj)
            (eq (cadr (assoc obj obj-locs)) loc)))
    (remove-if-not #'at-loc-p objs)))

(defun describe-location (location world-nodes)
  (cadr (assoc location world-nodes)))


(defun describe-paths (loc world-edges)
  (labels ((describe-path (path)
             `(there is a ,(caddr path) going ,(cadr path) from here.)))
    (apply #'append (mapcar #'describe-path (cdr (assoc loc world-edges))))))

(defun describe-objects (loc objs obj-locs)
  (labels ((describe-obj (obj)
             `(you see a ,obj on the floor.)))
    (apply #'append (mapcar #'describe-obj (objects-at loc objs obj-locs)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Game implementation

;;;; World description

(defparameter *nodes*
  '((living-room (you are in the living-room.
                  a wizard is snoring loudly on the couch.))
    (garden      (you are in the beautiful garden.
                  there is a well in front of you.))
    (attic       (you are in the attic.
                  thire is a giant welding tourch in the corner.))))

(defparameter *edges*
  '((living-room (garden west door)
                 (attic upstairs ladder))
    (garden (living-room east door))
    (attic (living-room downstairs ladder))))

(defparameter *objects* '(whiskey bucket frog chain))

;;;; Object states

(defparameter *object-locations*
  ;; (<obj> <node>)
  '((whiskey living-room) (bucket living-room)
    (chain garden) (frog garden)))

(defparameter *location* 'living-room)

;; Game logic

;;;; Player actions

(defun look ()
  (append (describe-location *location* *nodes*)
          (describe-paths *location* *edges*)
          (describe-objects *location* *objects* *object-locations*)))

(defun walk (direction)
  (let ((next (find direction
                    (cdr (assoc *location* *edges*))
                    :key #'cadr)))
    (if next
        (progn (setf *location* (car next)) (look))
        '(you cannot go that way.))))

(defun pickup (obj)
  (cond ((member obj (objects-at *location* *objects* *object-locations*))
         (push (list obj 'body) *object-locations*)
         `(you are now carrying ,obj))
        (t '(you cannot get that.))))

(defun inventory ()
  (cons 'items- (objects-at 'body *objects* *object-locations*)))
