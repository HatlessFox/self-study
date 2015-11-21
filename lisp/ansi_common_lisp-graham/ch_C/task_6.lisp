(defun member-c (obj clst)
  (do ((l (cdr clst) (cdr l))) ; ok to start with the 2nd el since clst is circle
      ((or (eql (car l) obj) (eq clst l)) (eql (car l) obj))))

;;(setf *print-circle* t)
;;(setf clst '(1 2 3 4 5))
;;(setf (cdr (last clst)) clst)

