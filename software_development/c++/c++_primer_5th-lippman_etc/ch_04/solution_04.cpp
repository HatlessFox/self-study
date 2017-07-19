/*
 * Exercise 4.4, p.141.
 *
 * Parenthesize the following expression to show how it is evaluated.
 * Test your answer.
 */

#include <iostream>

int main(int, char**) {
  std::cout << ((((12 / 3) * 4) + (5 * 15)) + ((24 % 4) / 2)) << std::endl;
  std::cout << 12/3*4+5*15+24%4/2 << std::endl;
  return 0;
}
