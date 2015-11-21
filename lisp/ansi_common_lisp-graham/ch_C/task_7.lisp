(defun circlep (lst)
  (do ((l (cdr lst) (cdr l)))
      ((or (null l) (eq l lst)) (eq l lst))))


       
