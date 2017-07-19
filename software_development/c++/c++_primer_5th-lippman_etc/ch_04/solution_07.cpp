/*
 * Exercise 4.7, p.141.
 *
 * What does overflow mean? Show three expressions that will overflow.
 */

/*
 * overflow ~ value is "wrapping around" due to finite-precession of a type.
 */

#include <iostream>

int main(int, char**) {
  char i = static_cast<char>(255);
  std::cout << "255 + 1 = " << i + 1 << " (?!)" << std::endl;
  unsigned j = 0;
  std::cout << "0 - 1 = " << j - 1 << " (?!)" << std::endl;
  short n = 1287, m = 1297, k = n * m;
  std::cout << "1287 * 1297 = " << k << " (?!)" << std::endl;
  return 0;
}
