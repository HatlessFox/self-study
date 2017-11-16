(defparameter *monsters* nil)
(defparameter *monster-builders* nil)
(defparameter *monster-num* 12)

(defun init-monsters ()
  (setf *monsters*
        (map 'vector
             (lambda (_)
               (funcall (nth (random (length *monster-builders*))
                             *monster-builders*)))
             (make-array *monster-num*))))

(defun monster-dead (m)
  (<= (monster-health m) 0))

(defun monsters-dead ()
  (every #'monster-dead *monsters*))

(defun show-monsters ()
  (fresh-line)
  (princ "Your foes: ")
  (let ((i 0))
    (map 'list
         (lambda (m)
           (fresh-line)
           (princ "    ")
           (princ (incf i))
           (princ ".")
           (if (monster-dead m)
               (princ "***dead***")
               (progn (princ "(Health=")
                      (princ (monster-health m))
                      (princ ") ")
                      (monster-show m))))
         *monsters*)))

;; base Monster

(defstruct monster (health (randval 10)))

(defmethod monster-hit (m h)
  (decf (monster-health m) h)
  (if (monster-dead m)
      (progn (princ "You killed the ")
             (princ (type-of m))
             (princ "! "))
      (progn (princ "You hit the ")
             (princ (type-of m))
             (princ ", knocking off ")
             (princ h)
             (princ " health points! "))))

(defmethod monster-show (m)
  (princ "A fierce ")
  (princ (type-of m)))

(defmethod monster-attack (m))

;; Monsters
;;
;;;; Wicked Orc

(defstruct (orc (:include monster)) (club-level (randval 8)))
(push #'make-orc *monster-builders*)

(defmethod monster-show ((m orc))
  (princ "A wicked orc with a level ")
  (princ (orc-club-level m))
  (princ " club"))

(defmethod monster-attack ((m orc))
  (let ((h (randval (orc-club-level m))))
    (princ "An orc swings his club at you and knock off ")
    (princ h)
    (princ " of your health points. ")
    (decf *player-health* h)))

;;;; Hydra

(defstruct (hydra (:include monster)))
(push #'make-hydra *monster-builders*)

(defmethod monster-show ((m hydra))
  (princ "A malicious hydra with ")
  (princ (monster-health m))
  (princ " heads."))

(defmethod monster-hit ((m hydra) h)
  (decf (monster-health m) h)
  (if (monster-dead m)
      (progn (princ "The corpse of the fully decapitated and ")
             (princ "decapacitated hydra falls to the floor!"))
      (progn (princ "You lop off ")
             (princ h)
             (princ " of the hydra's heads! "))))

(defmethod monster-attack ((m hydra))
  (let ((h (randval (ash (monster-health m) -1))))
    (princ "A hydra attacks you with ")
    (princ h)
    (princ " of its heads! It also grows back one more head! ")
    (incf (monster-health m))
    (decf *player-health* h)))

;;;; Slime Mold

(defstruct (slime-mold (:include monster))
  (sliminess (randval 5)))
(push #'make-slime-mold *monster-builders*)

(defmethod monster-show ((m slime-mold))
  (princ "A slime mold with a sliminess of ")
  (princ (slime-mold-sliminess m)))

(defmethod monster-attack ((m slime-mold))
  (let ((agility-hit (randval (slime-mold-sliminess m))))
    (princ "A slime mold wraps around your legs and decreases your agility by ")
    (princ agility-hit)
    (princ "! ")
    (decf *player-agility* agility-hit)
    (when (zerop (random 2))
      (princ "It also squirts in your face, taking away a health point!")
      (decf *player-health*))))

;;;; Brigand

(defstruct (brigand (:include monster)))
(push #'make-brigand *monster-builders*)

(defmethod monster-attack ((m brigand))
  (let ((attack-driver (max *player-health* *player-agility*
                            *player-strength*)))
    (cond ((= attack-driver *player-health*)
           (princ "A brigand hits you with his slingshot, ")
           (princ "taking off 2 health points! ")
           (decf *player-health* 2))
          ((= attack-driver *player-agility*)
           (princ "A brigand catches your leg with his whip, ")
           (princ "taking off 2 agility points! ")
           (decf *player-agility* 2))
          ((= attack-driver *player-strength*)
           (princ "A brigand cuts your arm with his whip, ")
           (princ "taking off 2 strength points! ")
           (decf *player-strength* 2)))))

