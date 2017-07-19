/*
 * Exercise 4.5, p.141.
 *
 * Determine the result of the following expressions.
 */

#include <iostream>

int main(int, char**) {
  std::cout << "(a) " << -86 << " vs " << -30 * 3 + 21 / 5 << std::endl;
  std::cout << "(b) " << -18 << " vs " << -30 + 3 * 21 / 5 << std::endl;
  std::cout << "(c) " << 0 << " vs " << 30 / 3 * 21 % 5 << std::endl;
  std::cout << "(d) " << -2 << " vs " << -30 / 3 * 21 % 4 << std::endl;
  return 0;
}
