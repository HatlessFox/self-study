(load "graph-visualization-util")
(load "graph-building-util")

;; game map graph customization

(defparameter *node-nm* 30)
(defparameter *edge-nm* 45)
(defparameter *worm-nm* 3)
(defparameter *cop-odds* 15)

;; map building utils

(defun edges-to-alist (edges)
  (mapcar (lambda (from-node)
            (cons from-node
                  (mapcar (lambda (e) (list (edge-to e)))
                          (remove-duplicates (incident-edges from-node edges)
                                             :test #'equal))))
   (remove-duplicates (mapcar #'edge-from edges))))

(defun add-cops (edges-al cop-edges)
  (mapcar (lambda (record)
            (let ((from-node (first record))
                  (from-node-edges (rest record)))
              (cons from-node
                    (mapcar (lambda (edge)
                              (let ((to-node (first edge)))
                                (if (intersection (link-nodes from-node to-node)
                                                  cop-edges :test #'equal)
                                    (list to-node 'cops)
                                    edge)))
                            from-node-edges))))
          edges-al))

(defun cops-p (edge) (member 'cops edge))

(defun make-city-edges ()
  (let* ((nodes (loop for i from 1 to *node-nm* collect i))
         (edges (to-connected nodes
                              (make-random-edges *node-nm* *edge-nm*)))
         (cops-edges (remove-if-not (lambda (_) (zerop (random *cop-odds*)))
                                    edges)))
    (add-cops (edges-to-alist edges) cops-edges)))

(defun adj-nodes (node edges-al)
  (mapcar #'first (rest (assoc node edges-al))))

(defun within-one-p (a b edges-al)
  (member b (adj-nodes a edges-al)))

(defun within-two-p (a b edges-al)
  (or (within-one-p a b edges-al)
      (some (lambda (x) (within-one-p x b edges-al))
            (adj-nodes a edges-al))))

(defun make-city-nodes (edges-al)
  (let ((wumpus-pos (random-node *node-nm*))
        (glow-worm-poses (loop for i below *worm-nm*
                               collect (random-node *node-nm*))))
    (labels ((city-node (n)
              (append (list n)
                      (cond ((eql n wumpus-pos) '(wumpus))
                            ((within-two-p n wumpus-pos edges-al) '(blood!)))
                      (cond ((member n glow-worm-poses) '(glow-worm))
                            ((some (lambda (worm-pos)
                                     (within-one-p n worm-pos edges-al))
                                   glow-worm-poses)
                             '(lights)))
                      (when (some #'cops-p (cdr (assoc n edges-al)))
                        '(sirens!)))))
            (loop for i from 1 to *node-nm*
                  collect (city-node i)))))

;; Game Logic

(defparameter *map-city-nodes* nil)
(defparameter *map-city-edges* nil)
(defparameter *player-pos* nil)
(defparameter *visited-nodes* nil)

(defun find-empty-node ()
  (let ((x (random-node *node-nm*)))
    (if (rest (assoc x *map-city-nodes*))
        (find-empty-node)
        x)))

(defun known-city-nodes ()
  (let ((unknown-frontier
         (mapcan (lambda (node-id)
                   (mapcar #'first (rest (assoc node-id *map-city-edges*))))
                 *visited-nodes*)))
    (mapcar (lambda (node-id)
              (if (member node-id *visited-nodes*)
                  (let ((node (assoc node-id *map-city-nodes*)))
                    (if (eql node-id *player-pos*)
                        (append node '(*))
                        node))
                  (list node-id '?)))
            (remove-duplicates (append *visited-nodes* unknown-frontier)))))
          

(defun known-city-edges ()
  (mapcar (lambda (node-id)
            (cons node-id (mapcar (lambda (node)
                                    (if (member (first node) *visited-nodes*)
                                        node
                                        (list (first node))))
                                  (rest (assoc node-id *map-city-edges*)))))
          *visited-nodes*))

(defun draw-known-city ()
  (ugraph->png "known-city" (known-city-nodes) (known-city-edges)))

(defun draw-city ()
  (ugraph->png "city" *map-city-nodes* *map-city-edges*))

(defun new-game ()
  (setf *map-city-edges* (make-city-edges))
  (setf *map-city-nodes* (make-city-nodes *map-city-edges*))
  (setf *player-pos* (find-empty-node))
  (setf *visited-nodes* (list *player-pos*))
  (draw-city)
  (draw-known-city))

(defun handle-direction (pos charging)
  (let ((edge (assoc pos
                     (rest (assoc *player-pos* *map-city-edges*)))))
    (if edge
        (handle-new-place edge pos charging)
        (princ "That location does not exist!"))))

(defun handle-new-place (edge pos charging)
  (let* ((node (assoc pos *map-city-nodes*))
         (has-worm (and (member 'glow-worm node)
                        (not (member pos *visited-nodes*)))))
    (pushnew pos *visited-nodes*)
    (setf *player-pos* pos)
    (cond ((member 'cops edge) (princ "You ran into the cops. Game over."))
          ((member 'wumpus node)
           (if charging
               (princ "You found the Wumpus!")
               (princ "You ran into the Wumpus. Game over.")))
          (charging (princ "You waster your last bullet. Game Over."))
          (has-worm (let ((new-pos (random-node *node-nm*)))
                      (princ "You ran into a Glow Worm Gand! You're now at ")
                      (princ new-pos)
                      (handle-new-place nil new-pos nil))))))

(defun charge (pos) (handle-direction pos t))
(defun walk (pos) (handle-direction pos nil))
