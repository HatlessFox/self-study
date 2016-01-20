(setf x 'a y 'b z '(c d))

(unless (equal `(,z ,x z) '((c d) a z))
  (error "(A) is wrong"))

(unless (equal `(x ,y ,@z) '(x b c d))
  (error "(B) is wrong"))

(unless (equal `((,@z ,x) z) '((c d a) z))
  (error "(C) is wrong"))
