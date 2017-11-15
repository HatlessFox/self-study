;;;; Common Graph Building Utils

(defun random-node (node-nm) (1+ (random node-nm)))
(defun edge-from (e) (car e))
(defun edge-to (e) (cdr e))
(defun link-nodes (a b)
  (unless (eql a b) (list (cons a b) (cons b a))))

(defun make-random-edges (node-nm edge-nm)
  (apply #'append (loop repeat edge-nm
                        collect (link-nodes (random-node node-nm)
                                            (random-node node-nm)))))

(defun incident-edges (node edges)
  (remove-if-not (lambda (e) (eql (edge-from e) node)) edges))

(defun connected-nodes (start-node edges)
  (let ((connected nil))
    (labels ((dfs (node)
                  (unless (member node connected)
                    (push node connected)
                    (mapc (lambda (e) (dfs (edge-to e)))
                          (incident-edges node edges)))))
            (dfs start-node))
    connected))

; finds connected components of a given graph (CCs)
(defun find-ccs (nodes edges)
  (let ((ccs nil))
    (labels ((find-cc (nodes)
                      (princ nodes)
                      (fresh-line)
                      (let* ((connected (connected-nodes (first nodes)
                                                         edges))
                             (unconnected (set-difference nodes connected)))
                        (push connected ccs)
                        (when unconnected
                          (find-cc unconnected)))))
            (find-cc nodes))
    ccs))

(defun to-connected (nodes edges)
  (labels ((cc-connections (ccs)
                           (let ((cc1 (first ccs)) (cc2 (second ccs)))
                             (when cc2
                               (append (link-nodes (first cc1) (first cc2))
                                       (cc-connections (rest ccs)))))))
          (append (cc-connections (find-ccs nodes edges)) edges)))
