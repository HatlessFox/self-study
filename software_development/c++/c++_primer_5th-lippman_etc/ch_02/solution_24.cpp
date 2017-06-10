/*
 * Exercise 2.24, p. 56
 *
 * Why is the initialization of p legal but that of pl illegal?
 *
 * int i = 42;
 * void *p = &i;
 * long *lp = &i;
 */

/*
 * Answer:
 * - we cannot read/modify the value of p (its type is void*),
 *    so type incompatibility doesn't matter.
 * - we can pontentially derefernce lp, but byte sequence of sizeof(long)
 *   will be accessed instead of oridinally set sizeof(int). This may lead to
 *   counterintuitive results.
 */
