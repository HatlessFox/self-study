/*
 * Exercise 4.33, p.158.
 *
 * Explain what the following expression does?
 */

#include <iostream>

// evaluates ? ++x, ++y : --x then --y

int main(int, char**) {
  int x = 0, y = 0;
  bool flag = true;
  std::cout << (flag ? ++x, ++y : --x, --y) << std::endl;
  std::cout << x << " " << y << std::endl;

  x = y = 0;
  flag = false;
  std::cout << (flag ? ++x, ++y : --x, --y) << std::endl;
  std::cout << x << " " << y << std::endl;
  return 0;
}
