/*
 * Exercise 4.19, p.150.
 *
 * Given that ptr points to an int, that vec in a vector<int>, and that ival
 * is an int, explain the behavior of each of these expressions. Which, if any,
 * are likely to be incorrect? Why? How might each be corrected?
 */

/*
 * (a)
 * ptr != 0 && *ptr++
 * Check if ptr is valid and has non-zero value; also increments ptr.
 * (b)
 * ival++ && ival
 * Checks whether ival and ival+1 are both non-zero.
 * (c)
 * vec[ival++] <= vec[ival]
 * Intention is to compare ival and ival + 1 vector elements.
 * The expression lead to undefined behavior
 * (order of reading and modification is undefined).
 */
