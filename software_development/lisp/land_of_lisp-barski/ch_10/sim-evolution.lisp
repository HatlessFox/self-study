; simulation parameters

(defparameter *width* 100)
(defparameter *height* 30)
(defparameter *jungle* '(45 10 10 10))
(defparameter *plant-energy* 80)
(defparameter *reproduction-energy* 200)

; simulation

;; plants handling

(defparameter *plants* (make-hash-table :test #'equal))
(defun random-plant (left bot width height) ; area
  (let ((x (+ left (random width)))
        (y (+ bot (random height))))
    (setf (gethash (cons x y) *plants*) t)))

(defun add-plants ()
  (apply #'random-plant *jungle*)
  (random-plant 0 0 *width* *height*))

;; animal

(defstruct animal x y energy dir genes)
(defparameter *animals*
  (list (make-animal :x (ash *width* -1)
                     :y (ash *height* -1)
                     :energy 1000
                     :dir 0
                     :genes (loop repeat 8 collecting (1+ (random 8))))))

(defun animal-move (animal)
  (let ((dir (animal-dir animal))
        (x (animal-x animal))
        (y (animal-y animal)))
    (setf (animal-x animal)
          (mod (+ *width* x
                  (cond ((and (<= 2 dir) (< dir 5)) 1)
                        ((or (= dir 1) (= dir 5)) 0)
                        (t - 1)))
               *width*))
    (setf (animal-y animal)
          (mod (+ *height* y
                  (cond ((and (<= 0 dir) (< dir 3)) -1)
                        ((and (<= 4 dir) (< dir 7)) 1)
                        (t 0)))
               *height*))
    (decf (animal-energy animal))))

(defun animal-turn (animal)
  (let ((x (random (apply #'+ (animal-genes animal)))))
    (labels ((angle (genes x)
                    (let ((new-x (- x (car genes))))
                      (if (< new-x 0) 0
                        (1+ (angle (cdr genes) new-x))))))
            (setf (animal-dir animal)
                  (mod (+ (animal-dir animal) (angle (animal-genes animal) x))
                       8)))))

(defun animal-eat (animal)
  (let ((pos (cons (animal-x animal) (animal-y animal))))
    (when (gethash pos *plants*)
      (incf (animal-energy animal) *plant-energy*)
      (remhash pos *plants*))))

(defun animal-reproduce (animal)
  (let ((e (animal-energy animal)))
    (when (<= *reproduction-energy* e)
      (setf (animal-energy animal) (ash e -1))
      (let ((new-animal   (copy-structure animal))
            (genes        (copy-list (animal-genes animal)))
            (mutated-i (random 8)))
        (setf (nth mutated-i genes)
              (max 1 (+ (nth mutated-i genes) (random 3) -1)))
        (setf (animal-genes new-animal) genes)
        (push new-animal *animals*)))))

;; simultation core

;;; iteration

(defun update-world ()
  (setf *animals*
        (remove-if (lambda (a) (<= (animal-energy a) 0)) *animals*))
  (mapc (lambda (a)
          (animal-turn a)
          (animal-move a)
          (animal-eat a)
          (animal-reproduce a))
        *animals*)
  (add-plants))

;;; view

(defun draw-world ()
  (loop for y below *height* doing
        (progn (fresh-line)
               (princ "|")
               (loop for x below *width* doing
                     (princ (cond ((some (lambda (a)
                                           (and (= (animal-x a) x)
                                                (= (animal-y a) y)))
                                         *animals*)
                                   #\M)
                                  ((gethash (cons x y) *plants*) #\*)
                                  (t #\space))))
               (princ "|"))))

;;; cli

(defun evolution ()
  (draw-world)
  (fresh-line)
  (let ((cmd (read-line)))
    (cond ((equal cmd "quit") ())
          (t (let ((iters (parse-integer cmd :junk-allowed t)))
               (if iters (loop for i below iters doing (update-world)
                               if (zerop (mod i 1000)) doing (princ #\.))
                 (update-world))
               (evolution))))))
