;; performs graphviz-based graph visualization 

;;; Common names to text conversions

(defun dot-name (exp)
  (substitute-if #\_ (complement #'alphanumericp)
                 (prin1-to-string exp)))

(defparameter *max-label-length* 30)

(defun trim-string (s max-len)
  (if (< max-len (length s))
      (concatenate 'string (subseq s 0 (- max-len 3)) "...")
    s))

(defun dot-label (exp)
  (if exp
      (trim-string (write-to-string exp :pretty nil) *max-label-length*)
    ""))

(defun nodes->dot (nodes)
  (mapc (lambda (node)
          (fresh-line)
          (princ (dot-name (car node)))
          (princ "[label=\"")
          (princ (dot-label node))
          (princ "\"];"))
        nodes))

;;; Directed Graph -> PNG

(defun edges->dot (edges)
  (mapc (lambda (node)
          (mapc (lambda (edge)
                  (fresh-line)
                  (princ (dot-name (car node)))
                  (princ "->")
                  (princ (dot-name (car edge)))
                  (princ "[label=\"")
                  (princ (dot-label (cdr edge)))
                  (princ "\"];"))
                (cdr node)))
        edges))

(defun graph->dot (nodes edges)
  (princ "digraph {")
  (nodes->dot nodes)
  (edges->dot edges)
  (princ "}")
  'done)

(defun dot->png (fname thunk)
  (with-open-file (*standard-output* fname
                                     :direction :output
                                     :if-exists :supersede)
                  (funcall thunk))
  (ext::shell (concatenate 'string  "dot -Tpng -O " fname)))

(defun graph->png (fname nodes edges)
  (dot->png fname (lambda () (graph->dot nodes edges))))

;;; Undirected Graph -> PNG

(defun uedges->dot (edges)
  (maplist (lambda (lst)
             (mapc (lambda (edge)
                     (unless (assoc (car edge) (cdr lst))
                       (fresh-line)
                       (princ (dot-name (caar lst)))
                       (princ "--")
                       (princ (dot-name (car edge)))
                       (princ "[label=\"")
                       (princ (dot-label (cdr edge)))
                       (princ "\"];")))
                   (cdar lst)))
           edges))

(defun ugraph->dot (nodes edges)
  (princ "graph {")
  (nodes->dot nodes)
  (uedges->dot edges)
  (princ "}")
  'done)

(defun ugraph->png (fname nodes edges)
  (dot->png fname (lambda () (ugraph->dot nodes edges))))
