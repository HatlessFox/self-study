; Play the classic game of Robots! All robots move towards the player
; Robot collisions cause scrap that is deadly to other robots.
; Teleport as a last resort!

(defun robots ()
  (loop named main ; naming is used to return
        ; Setup directions mapping
        ;; directions are dvorak-friendly controls for 64 cells game board
        with directions = '((g . -65) (c . -64) (r . -63)
                            (h .  -1)           (n .   1)
                            (m .  63) (w .  64) (v .  65))
        ; Update pose on each iteration
        for pos = 544 ; 'linear' pose
        then (progn (format t "~%gcr/hn/mwv to move, (t)eleport, (l)leave:")
                    (force-output)
                    (let* ((c (read))
                           (d (assoc c directions)))
                      (cond (d (mod (+ pos (cdr d)) 1024))
                            ((eq 't c) (random 1024))
                            ((eq 'l c) (return-from main 'bb))
                            (t pos))))
        ; Update monsters state on each iteration
        for monsters = (loop repeat 10 collect (random 1024))
        then (loop for mpos in monsters
                   collect (if (< 1 (count mpos monsters))
                               mpos ; monster crush
                             (cdar (sort (loop for (k . d) in directions
                                               for nu-mp = (mod (+ mpos d) 1024)
                                               collecting
                                               (cons (+ (abs (- (mod nu-mp 64)
                                                                 (mod pos 64)))
                                                         (abs (- (ash nu-mp -6)
                                                                 (ash pos -6))))
                                                     nu-mp))
                                         '< :key #'car))))
        when (loop for mpos in monsters
                   always (< 1 (count mpos monsters)))
          return 'player-wins
        ; View
        do (format t "~%|~{~<|~%|~,65:;~A~>~}|"
                   (loop for p below 1024
                         collect (cond ((member p monsters)
                                        (cond ((= p pos)
                                               (return-from main 'player-loses))
                                              ((< 1 (count p monsters)) #\#)
                                              (t #\R)))
                                       ((= p pos) #\@)
                                       (t #\space))))))
