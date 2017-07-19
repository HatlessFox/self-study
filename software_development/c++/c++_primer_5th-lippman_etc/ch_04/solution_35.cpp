/*
 * Exercise 4.35, p.161.
 *
 * Give the following definitions identify the implicit type conversions,
 * if any, taking place.
 */

/*
 * {Definitions}
 *   char cval; int ival; unsigned int ui; float fval;
 *   double dval;
 * {a}
 *   cval = 'a' + 3; // 'a' -> int, sum -> char
 * {b}
 *   fval = ui - ival * 1.0; // ival -> double, prod -> double, ui -> double
 * {c}
 *   dval = ui * fval; // ui -> float, prod -> double
 * {d}
 *   cval = ival + fval + dval; // ival -> float, sum1 -> double, sum2 -> char
 */

#include <iostream>

int main(int, char**) {
  std::cout << "<look at source code>" << std::endl;
  return 0;
}
