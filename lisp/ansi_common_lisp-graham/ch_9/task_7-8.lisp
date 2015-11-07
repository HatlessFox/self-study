(format t "# of bits used by fixnum: ~A~%"
	(log (1+ most-positive-fixnum) 2)) ; 48 on 64-bit clisp

(format t "ShF: ~A;~% SF: ~A;~% DF: ~A;~% LF: ~A;~%"
	most-positive-short-float
	most-positive-single-float
	most-positive-double-float
	most-positive-long-float)
	
