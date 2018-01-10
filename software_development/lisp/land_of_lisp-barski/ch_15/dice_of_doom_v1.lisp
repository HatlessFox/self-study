(defparameter *num-players* 2)
(defparameter *max-dice* 3)
(defparameter *board-size* 3)
(defparameter *board-hexnum* (* *board-size* *board-size*))

;;; board representation

(defun board-array (lst)
  (make-array *board-hexnum* :initial-contents lst))

(defun gen-board ()
  (board-array (loop for n below *board-hexnum*
                     collect (list (random *num-players*)
                                   (1+ (random *max-dice*))))))

(defun board-dice (board pos)
  (second (aref board pos)))

(defun player-letter (id)
  (code-char (+ 97 id)))

(defun draw-board (board)
  (loop for y below *board-size*
        do (progn (fresh-line)
                  (loop repeat (- *board-size* y)
                        do (princ "  "))
                  (loop for x below *board-size*
                        for hex = (aref board (+ x (* *board-size* y)))
                        do (format t "~a-~a "
                                   (player-letter (first hex))
                                   (second hex))))))

;; game tree

(defun gt-player (gt) (first gt))
(defun gt-board (gt) (second gt))
(defun gt-moves (gt) (third gt))

(defun game-tree (board player spare-dice first-move)
  (list player ; the current player
        board  ; the current board state
        (let ((a-moves (attacking-moves board player spare-dice)))
          (if first-move a-moves
            (cons (passing-move board player spare-dice) a-moves)))))

(let ((plain-game-tree #'game-tree)
      (memo (make-hash-table :test #'equalp)))
  (defun game-tree (&rest args)
    (or (gethash args memo)
        (setf (gethash args memo) (apply plain-game-tree args)))))

(defun mv-description (move) (first move))
(defun mv-tree (move) (second move))

(defun passing-move (board player spare-dice)
  (list nil ; description
        (game-tree (add-new-dice board player (1- spare-dice))
                   (mod (1+ player) *num-players*) 0 t)))

(defun attacking-move (board player spare-dice from to)
  (list (list from to) ; description
        (game-tree (board-attack board player from to (board-dice board from))
                   player
                   (+ spare-dice (board-dice board to))
                   nil)))

(defun attacking-moves (board cur-player spare-dice)
  (labels ((player (pos) (first (aref board pos)))
           (dice (pos) (board-dice board pos)))
          (mapcan (lambda (src)
                    (when (eq (player src) cur-player)
                      (mapcan (lambda (dst)
                                (when (and (not (eq (player dst) cur-player))
                                           (> (dice src) (dice dst)))
                                  (list (attacking-move board cur-player
                                                        spare-dice src dst))))
                              (neighbors src))))
                  (loop for n below *board-hexnum* collect n))))

(defun neighbors (pos)
  (let ((up (- pos *board-size*))
        (down (+ pos *board-size*)))
    (loop for p in (append (list up down)
                           (unless (zerop (mod pos *board-size*))
                             (list (1- up) (1- pos)))
                           (unless (zerop (mod (1+ pos) *board-size*))
                             (list (1+ pos) (1+ down))))
          when (and (<= 0 p) (< p *board-hexnum*))
          collect p)))

(let ((plain-neighbors (symbol-function 'neighbors))
      (memo (make-hash-table)))
  (defun neighbors (pos)
    (or (gethash pos memo)
        (setf (gethash pos memo) (funcall plain-neighbors pos)))))

(defun board-attack (board player src dst dice)
  (board-array (loop for pos from 0
                     for hex across board
                     collect (cond ((eq pos src) (list player 1))
                                   ((eq pos dst) (list player (1- dice)))
                                   (t hex)))))

(defun add-new-dice (board player spare-dice)
  (labels ((f (lst n acc)
              (cond ((zerop n) (append (reverse acc) lst))
                    ((null lst) (reverse acc))
                    (t (let ((cur-player (first (car lst)))
                             (cur-dice (second (car lst))))
                         (if (and (eq cur-player player)
                                  (< cur-dice *max-dice*))
                             (f (cdr lst) (1- n)
                                (cons (list cur-player (1+ cur-dice)) acc))
                           (f (cdr lst) n
                              (cons (car lst) acc))))))))
          (board-array (f (coerce board 'list) spare-dice ()))))

;; Game loops

(defun play-vs-human (tree)
  (print-info tree)
  (if (gt-moves tree)
      (play-vs-human (handle-human tree))
    (announce-winner (gt-board tree))))

(defun print-info (tree)
  (fresh-line)
  (format t "current player = ~a" (player-letter (gt-player tree)))
  (draw-board (gt-board tree)))

(defun handle-human (tree)
  (fresh-line)
  (princ "choose your move: ")
  (let ((moves (gt-moves tree)))
    (loop for move in moves
          for n from 1
          do (let ((action (car move)))
               (fresh-line)
               (format t "~a. " n)
               (if action
                   (format t "~a -> ~a" (first action) (second action))
                 (princ "end turn"))))
    (fresh-line)
    (mv-tree (nth (1- (read)) moves))))

(defun winners (board)
  (let* ((tally (loop for hex across board collect (first hex)))
         (totals (mapcar (lambda (player) (cons player (count player tally)))
                         (remove-duplicates tally)))
         (best (apply #'max (mapcar #'cdr totals))))
    (mapcar #'car
            (remove-if (lambda (x) (not (eq (cdr x) best))) totals))))

(defun announce-winner (board)
  (fresh-line)
  (let ((w (winners board)))
    (if (< 1 (length w))
        (format t "The game is a tie between ~a" (mapcar #'player-letter w))
      (format t "The winner is ~a" (player-letter (car w))))))

;;; AI

(defun rate-position (tree player)
  (if (gt-moves tree)
      (apply (if (eq (gt-player tree) player) #'max #'min)
             (get-ratings tree player))
    (let ((w (winners (gt-board tree))))
      (if (member player w) (/ 1 (length w)) 0))))

(let ((plain-rate-position #'rate-position)
      (memo (make-hash-table)))
  (defun rate-position (tree player)
    (let ((tab (gethash player memo)))
      (unless tab
        (setf tab (setf (gethash player memo) (make-hash-table))))
      (or (gethash tree tab)
          (setf (gethash tree tab)
                (funcall plain-rate-position tree player))))))

(defun get-ratings (tree player)
  (mapcar (lambda (move) (rate-position (mv-tree move) player))
          (gt-moves tree)))

(defun handle-computer (tree)
  (let ((ratings (get-ratings tree (gt-player tree))))
    (mv-tree (nth (position (apply #'max ratings) ratings) (gt-moves tree)))))

(defun play-vs-computer (tree)
  (print-info tree)
  (cond ((null (gt-moves tree)) (announce-winner (gt-board tree)))
        ((zerop (gt-player tree)) (play-vs-computer (handle-human tree)))
        (t (play-vs-computer (handle-computer tree)))))

(defun run-vs-ai ()
  (play-vs-computer (game-tree (gen-board) 0 0 t)))
