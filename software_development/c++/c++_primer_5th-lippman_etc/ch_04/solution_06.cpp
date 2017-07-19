/*
 * Exercise 4.6, p.141.
 *
 * Write an expression to determine whether an int value is even or odd.
 */

#include <iostream>

int main(int, char**) {
  int i;
  while (std::cin >> i) {
    std::cout << ((i & 1) ? "odd" : "even") << std::endl;
  }
  return 0;
}
