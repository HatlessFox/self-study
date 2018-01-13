(defmacro split (val yes-branch no-branch)
  (let ((g (gensym)))
    `(let ((,g ,val))
       (if ,g
           (let ((head (car ,val))
                 (tail (cdr ,val)))
             ,yes-branch)
         ,no-branch))))

(defun pairs (lst)
  (labels ((f (lst acc)
              (split lst
                     (if tail
                         (f (cdr tail) (cons (cons head (car tail)) acc))
                       (reverse acc))
                     (reverse acc))))
          (f lst nil)))

(defmacro recurse (vars &body body)
  (let ((p (pairs vars)))
    `(labels ((self ,(mapcar #'car p) ,@body))
             (self ,@(mapcar #'cdr p)))))

(defun my-length (lst)
  (recurse (lst lst acc 0)
           (split lst (self tail (1+ acc)) acc)))

