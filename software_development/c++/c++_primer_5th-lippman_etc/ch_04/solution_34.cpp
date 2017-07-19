/*
 * Exercise 4.34, p.161.
 *
 * Given the variable definitions in this section, eplain what conversions take
 * place in the following expressions. Remember that you may need to consider
 * the associativity of operators.
 */

/*
 * (a)
 *   if (fval) ...
 * fval is float, converted to bool.
 *
 * (b)
 *   dval = fval + ival;
 *   // ival to float, result of sum (float) to double.
 *
 * (c)
 *   dval + ival * cval;
 *   // cval to int, reuslt of prod (int) to double.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "<look at source code>" << std::endl;
  return 0;
}
