(defmacro my-if (x then else)
  `(cond (,x ,@then)
	 (t ,@else)))
