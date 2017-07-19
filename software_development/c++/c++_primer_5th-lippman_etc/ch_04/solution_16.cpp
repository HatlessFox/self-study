/*
 * Exercise 4.16, p.147.
 *
 * Although the following are legal, they probably do not behave as
 * the programmer expects. Why? Rewrite the expressions as
 * you think they should be.
 */

/*
 * (a)
 * if (p = getPtr() != 0) <-- tries to assign to p the result of comparison
 * <Corrected>
 * if ((p = getPtr()) != 0)
 *
 * (b)
 * if (i = 1024) <-- assigns 1024 to i
 * <Corrected>
 * if (i == 1024)
 */
