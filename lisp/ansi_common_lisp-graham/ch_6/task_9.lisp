(defun apply-base8 (f lst)
  (let ((*print-base* 8))
    (apply f lst)))

