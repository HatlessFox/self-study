(load "../ch_05/txt_game_engine.lisp")

(defun game-read()
  (let ((cmd (read-from-string (concatenate 'string "(" (read-line) ")"))))
    (cons (car cmd) (mapcar (lambda (x) (list 'quote x)) (cdr cmd)))))

(defun tweak-chars(chars cap-mode lit-mode)
  (when chars
  (let ((ch (car chars)) (rest (cdr chars)))
    (cond ((char-equal ch #\Space) ; is space
           (cons ch (tweak-chars rest cap-mode lit-mode)))
          ((member ch '(#\! #\. #\?)) ; is end of sentence
           (cons ch (tweak-chars rest t lit-mode)))
          ((char-equal ch #\") ; literal mode marker
           (tweak-chars rest cap-mode (not lit-mode)))
          (lit-mode ; literal mode enabled
           (cons ch (tweak-chars rest nil lit-mode)))
          (cap-mode ; capitalize mode enabled
           (cons (char-upcase ch) (tweak-chars rest nil lit-mode)))
          (t ; default
           (cons (char-downcase ch) (tweak-chars rest cap-mode lit-mode)))))))

(defun game-print(lst)
  (let ((text (string-trim "() " (prin1-to-string lst))))
    (princ (coerce (tweak-chars (coerce text 'list) t nil) 'string))
    (fresh-line)))

(defparameter *allowed-commands* '(look walk pickup inventory))

(defun game-eval(sexp)
  (if (member (car sexp) *allowed-commands*)
      (eval sexp)
      '(i don't know that command.)))

(defun game-repl()
  (let ((cmd (game-read)))
    (unless (eq (car cmd) 'quit)
      (game-print (game-eval cmd))
      (game-repl))))
