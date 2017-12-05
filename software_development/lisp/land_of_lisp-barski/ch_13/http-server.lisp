;; Doesn't work on OS X 10.13 (CLISP 2.49)

; HTTP Header handling

(defun http2char (c1 c2 &optional (default #\Space))
  (let ((ch-code (parse-integer (coerce (list c1 c2) 'string)
                                :radix 16 :junk-allowed t)))
    (if ch-code (code-char ch-code) default)))

(defun decode-param (s)
  (labels ((decode-char (l)
              (when l
                (case (first l)
                      (#\% (cons (http2char (second l) (third l))
                                 (decode-char (cdddr l))))
                      (#\+ (cons #\space (decode-char (rest l))))
                      (otherwise (cons (first l) (decode-char (rest l))))))))
          (coerce (decode-char (coerce s 'list)) 'string)))

(defun parse-params (s)
  (let* ((i1 (position #\= s))
         (i2 (position #\& s))
         (key (subseq s 0 i1))
         (val (subseq s (1+ i1) i2)))
    (cond (i1 (cons (cons (intern (string-upcase key))
                          (decode-param val))
                    (and i2 (parse-params (subseq s (1+ i2))))))
          ((equal s "") nil)
          (t s))))

(defun parse-url (s)
  (let* ((url (subseq s (+ 2 (position #\space s))
                      (position #\space s :from-end t)))
         (param-sep-i (position #\? url)))
    (if param-sep-i
        (cons (subseq url 0 param-sep-i)
              (parse-params (subseq url (1+ param-sep-i))))
      (cons url '()))))

(defun get-header (stream) ; reads http header "record" by "record"
  (let* ((s (read-line stream))
         (h (let ((i (position #\: s)))
              (when i (cons (intern (string-upcase (subseq s 0 i)))
                            (subseq s (+ i 2)))))))
    (when h (cons h (get-header stream)))))

(defun get-content-params (stream header)
  (let ((length (rest (assoc 'content-length header))))
    (when length
      (let ((content (make-string (parse-integer length))))
        (read-sequence content stream)
        (parse-params content)))))

; Server

(defun serve (request-handler)
  (let ((socket (socket-server 8082)))
    (unwind-protect
        (loop (with-open-stream (stream (socket-accept socket))
                (let* ((url (parse-url (read-line stream)))
                       (path (first url))
                       (header (get-header stream))
                       (params (append (rest url)
                                       (get-content-params stream header))))
                  (let ((*standard-output* stream))
                    (funcall request-handler path header params)))))
        (socket-server-close socket))))

; "Page"

(defun hello-request-handler (path header params)
  (if (equal path "greeting")
      (let ((name (assoc 'name params)))
        (if (not name)
            (princ "<html><form>What is your name?
                    <input name='name' /></form></html>")
          (format t "<html>Nice to meet you, ~a!</html>" (rest name))))
    (princ "Sorry... I don't know that page.")))
