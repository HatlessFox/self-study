;;; common queue code from the book

(defun make-queue () (cons nil nil))

(defmacro do-if-not-empty (queue obj body)
  (let ((q (gensym))
	(o (gensym)))
    `(let ((,q ,queue) (,o ,obj))
       (if (null (car ,q))
	   (setf (cdr ,q)
		 (setf (car ,q) (list ,o)))
	 ,body))))

(defun enqueue (obj q)
  (do-if-not-empty q obj
    (setf (cddr q) (list obj)
	  (cdr q) (cddr q)))
  (car q))

(defun dequeue (q) (pop (car q)))

;;; task 3

(defun copy-queue (q)
  (let ((data-cp (copy-list (car q))))
    (cons data-cp (last data-cp))))

;;(setf q (make-queue))
;;(enqueue 1 q)
;;(enqueue 2 q)
;;(setf p (copy-queue q))
;;(dequeue q)
;;(enqueue 3 p)

;;; task 4

(defun add-to-head (obj q)
  (do-if-not-empty q obj
    (setf (car q) (cons obj (car q))))
  (car q))
  
;;; task 5

(defun look-up-obj (obj lst)
  (do ((l lst (cdr l)))
      ((or (null l) (eql (cadr l) obj)) l)))

(defun move-to-head (obj q)
  (let ((obj-chunk (look-up-obj obj (car q))))
    (unless (null obj-chunk)
      (add-to-head (cadr obj-chunk) q)
      (setf (cdr obj-chunk) (cddr obj-chunk))
      (setf (cdr q) (last (car q))))
    (car q)))
;; remove is better	      
