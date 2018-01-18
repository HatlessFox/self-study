;; core

(defmacro lazy (&body body)
  (let ((forced (gensym))
        (value (gensym)))
    `(let ((,forced nil)
           (,value nil))
       (lambda ()
         (unless ,forced
           (setf ,value (progn ,@body))
           (setf ,forced t))
         ,value))))

(defun force (lazy-value)
  (funcall lazy-value))

;; lazy-lists

(defmacro lazy-cons (a b) `(lazy (cons ,a ,b)))
(defun lazy-car (x) (car (force x)))
(defun lazy-cdr (x) (cdr (force x)))
(defun lazy-nil () (lazy nil))
(defun lazy-null (x) (not (force x)))

(defun make-lazy (lst)
  (lazy (when lst
          (cons (car lst) (make-lazy (cdr lst))))))

(defun take (n lst)
  (unless (or (zerop n) (lazy-null lst))
    (cons (lazy-car lst) (take (1- n) (lazy-cdr lst)))))

(defun take-all (lst)
  (unless (lazy-null lst)
    (cons (lazy-car lst) (take-all (lazy-cdr lst)))))

(defun lazy-mapcar (f lst)
  (lazy (unless (lazy-null lst)
          (cons (funcall f (lazy-car lst))
                (lazy-mapcar f (lazy-cdr lst))))))

(defun lazy-mapcan (f lst)
  (labels ((g (lst-cur)
              (if (lazy-null lst-cur)
                  (force (lazy-mapcan f (lazy-cdr lst)))
                (cons (lazy-car lst-cur) (lazy (g (lazy-cdr lst-cur)))))))
          (lazy (unless (lazy-null lst)
                  (g (funcall f (lazy-car lst)))))))

(defun lazy-find-if (f lst)
  (unless (lazy-null lst)
    (let ((x (lazy-car lst)))
      (if (funcall f x)
          x
        (lazy-find-if f (lazy-cdr lst))))))

(defun lazy-nth (n lst)
  (if (zerop n)
      (lazy-car lst)
    (lazy-nth (1- n) (lazy-cdr lst))))

;; infinite list

(defparameter *integers*
  (labels ((f (n) (lazy-cons n (f (1+ n))))) (f 1)))
