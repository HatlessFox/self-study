/*
 * Exercise 2.25, p. 59
 *
 * Determine types and value of the following variables.
 */

/*
 * Answer
 * (a)
 * int* ip, i, &r = i;
 * // ip - pointer to int; if local - undefined
 * // i - int; if local - undefined
 * // r - reference to i; if local - undefinde
 *
 * (b)
 * int i, *ip = 0;
 * // i - int; if local - undefined
 * // ip - pointer to int; nullptr
 *
 * (c)
 * int* ip, ip2;
 * // ip - pointer to int; if local - undefined
 * // ip2 - int; if local - undefined.
 */
