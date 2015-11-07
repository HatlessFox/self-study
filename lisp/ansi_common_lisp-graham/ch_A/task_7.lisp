(defmacro wrong-push (obj lst)
  `(setf ,lst (cons ,obj ,lst)))

(defmacro test (testee)
  `(let ((l '(b c)))
     (,testee 'a (car (setf l (cons 'e l))))
     l))

(when (equal (test push) (test wrong-push))
  (error "Task 7, bad example"))

